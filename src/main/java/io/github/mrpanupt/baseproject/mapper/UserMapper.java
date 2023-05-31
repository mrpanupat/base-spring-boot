package io.github.mrpanupt.baseproject.mapper;

import io.github.mrpanupt.baseproject.entity.UserEntity;
import io.github.mrpanupt.baseproject.module.user.model.UserRegisterRequest;
import io.github.mrpanupt.baseproject.module.user.model.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserRegisterRequest request);

    UserResponse toRegisterResponse(UserEntity entity);
}
