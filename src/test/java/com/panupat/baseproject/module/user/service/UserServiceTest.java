package com.panupat.baseproject.module.user.service;

import com.panupat.baseproject.entity.UserEntity;
import com.panupat.baseproject.mapper.UserMapper;
import com.panupat.baseproject.mapper.UserMapperImpl;
import com.panupat.baseproject.module.user.model.UserRegisterRequest;
import com.panupat.baseproject.module.user.model.UserResponse;
import com.panupat.baseproject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(classes = {UserMapperImpl.class})
class UserServiceTest {

    @Autowired
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @BeforeEach
    public void init() {
        userService = new UserService(userRepository, userMapper, passwordEncoder);
    }

    @Test
    void register_Success() {
        UserRegisterRequest request = new UserRegisterRequest();
        request.setEmail("a@a.a");
        request.setPassword("12345");
        request.setFirstName("a");
        request.setLastName("a");

        userService.register(request);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void getUserInfo_Success() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(userEntity));

        UserResponse response = userService.getUserInfo(1L);
        assertThat(response, is(notNullValue()));
    }
}
