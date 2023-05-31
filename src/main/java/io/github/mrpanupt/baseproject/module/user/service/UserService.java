package io.github.mrpanupt.baseproject.module.user.service;

import io.github.mrpanupt.baseproject.entity.UserEntity;
import io.github.mrpanupt.baseproject.exception.DataNotFoundException;
import io.github.mrpanupt.baseproject.mapper.UserMapper;
import io.github.mrpanupt.baseproject.module.user.model.UserRegisterRequest;
import io.github.mrpanupt.baseproject.module.user.model.UserResponse;
import io.github.mrpanupt.baseproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(UserRegisterRequest request) {
        UserEntity userEntity = userMapper.toEntity(request);
        log.info("=== encode password ===");
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        log.info("=== save to database ===");
        userEntity = userRepository.save(userEntity);
        return userMapper.toRegisterResponse(userEntity);
    }

    public UserResponse getUserInfo(Long id) {
        log.info("=== get user id: {} from database ===", id);
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        return userMapper.toRegisterResponse(userEntity);
    }
}
