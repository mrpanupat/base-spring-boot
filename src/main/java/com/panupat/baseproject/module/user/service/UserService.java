package com.panupat.baseproject.module.user.service;

import com.panupat.baseproject.entity.UserEntity;
import com.panupat.baseproject.exception.DataNotFoundException;
import com.panupat.baseproject.mapper.UserMapper;
import com.panupat.baseproject.module.user.model.UserRegisterRequest;
import com.panupat.baseproject.module.user.model.UserResponse;
import com.panupat.baseproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
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
