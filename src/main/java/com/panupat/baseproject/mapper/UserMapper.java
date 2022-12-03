package com.panupat.baseproject.mapper;

import com.panupat.baseproject.entity.UserEntity;
import com.panupat.baseproject.module.user.model.UserRegisterRequest;
import com.panupat.baseproject.module.user.model.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserRegisterRequest request);

    UserResponse toRegisterResponse(UserEntity entity);
}
