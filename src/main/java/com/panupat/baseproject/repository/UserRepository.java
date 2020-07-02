package com.panupat.baseproject.repository;

import com.panupat.baseproject.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
