package com.panupat.baseproject.module.authentication.service;

import com.panupat.baseproject.config.security.JwtTokenUtil;
import com.panupat.baseproject.entity.GroupAuthorityEntity;
import com.panupat.baseproject.entity.UserEntity;
import com.panupat.baseproject.entity.UserGroupEntity;
import com.panupat.baseproject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtTokenUtil jwtTokenUtil;
    @Mock
    private PasswordEncoder passwordEncoder;

    private AuthenticationService authenticationService;

    @BeforeEach
    public void init() {
        authenticationService = new AuthenticationService(userRepository, jwtTokenUtil, passwordEncoder);
    }

    @Test
    void authenticate_Success() {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("1234");
        userEntity.setEmail("a@a.a");
        UserGroupEntity userGroupEntity = new UserGroupEntity();
        GroupAuthorityEntity groupAuthorityEntity = new GroupAuthorityEntity();
        groupAuthorityEntity.setAuthorityName("test");
        userGroupEntity.setGroupAuthorityEntities(List.of(groupAuthorityEntity));
        userEntity.setUserGroupEntity(userGroupEntity);
        when(userRepository.findByEmail(anyString()))
                .thenReturn(userEntity);
        when(passwordEncoder.matches(any(CharSequence.class), anyString()))
                .thenReturn(true);
        when(jwtTokenUtil.generateToken(anyString(), any()))
                .thenReturn("super_token");

        String token = authenticationService.authenticate("a@a.a", "1234");
        assertThat(token, is(notNullValue()));
    }
}
