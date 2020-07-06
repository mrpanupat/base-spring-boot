package com.panupat.baseproject.repository;

import com.panupat.baseproject.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, Long> {
    @EntityGraph(value = "UserEntity.userGroup", type = EntityGraph.EntityGraphType.LOAD)
    UserEntity findByEmail(String email);
}
