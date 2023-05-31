package io.github.mrpanupt.baseproject.module.authentication.service;

import io.github.mrpanupt.baseproject.config.security.JwtTokenUtil;
import io.github.mrpanupt.baseproject.entity.GroupAuthorityEntity;
import io.github.mrpanupt.baseproject.entity.UserEntity;
import io.github.mrpanupt.baseproject.exception.AuthenticationFailException;
import io.github.mrpanupt.baseproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
                        .toList()
        );
    }
}
