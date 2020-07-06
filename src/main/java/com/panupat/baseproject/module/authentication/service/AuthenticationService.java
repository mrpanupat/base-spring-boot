package com.panupat.baseproject.module.authentication.service;

import com.panupat.baseproject.config.security.JwtTokenUtil;
import com.panupat.baseproject.entity.GroupAuthorityEntity;
import com.panupat.baseproject.entity.UserEntity;
import com.panupat.baseproject.exception.AuthenticationFailException;
import com.panupat.baseproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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

        return jwtTokenUtil.generateToken(
                userEntity.getEmail(),
                userEntity.getUserGroupEntity().getGroupAuthorityEntities()
                        .stream().map(GroupAuthorityEntity::getAuthorityName)
                        .collect(Collectors.toList())
        );
    }
}
