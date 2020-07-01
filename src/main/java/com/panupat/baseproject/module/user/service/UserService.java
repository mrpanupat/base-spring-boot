package com.panupat.baseproject.module.user.service;

import com.panupat.baseproject.entity.UserEntity;
import com.panupat.baseproject.exception.DataNotFoundException;
import com.panupat.baseproject.mapper.UserMapper;
import com.panupat.baseproject.module.user.model.UserRegisterRequest;
import com.panupat.baseproject.module.user.model.UserResponse;
import com.panupat.baseproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse register(UserRegisterRequest request) {
        UserEntity userEntity = userRepository.save(userMapper.toEntity(request));
        return userMapper.toRegisterResponse(userEntity);
    }

    public UserResponse getUserInfo(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        return userMapper.toRegisterResponse(userEntity);
    }
}
