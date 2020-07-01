package com.panupat.baseproject.repository;

import com.panupat.baseproject.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    UserEntity findByEmail(String email);
}
