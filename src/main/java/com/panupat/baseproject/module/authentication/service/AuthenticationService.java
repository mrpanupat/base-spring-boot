package com.panupat.baseproject.module.authentication.service;

import com.panupat.baseproject.config.security.JwtTokenUtil;
import com.panupat.baseproject.entity.UserEntity;
import com.panupat.baseproject.exception.AuthenticationFailException;
import com.panupat.baseproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    public String authenticate(String email, String password) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null || !passwordEncoder.matches(password, userEntity.getPassword()))
            throw new AuthenticationFailException();

        return jwtTokenUtil.generateToken(new User(userEntity.getEmail(), userEntity.getPassword(), Collections.emptyList()));
    }

    public User getUserDetail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        return userEntity != null ?
                new User(userEntity.getEmail(), userEntity.getPassword(), Collections.emptyList()) : null;
    }
}
