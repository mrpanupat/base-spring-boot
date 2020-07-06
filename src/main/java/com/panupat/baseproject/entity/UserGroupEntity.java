package com.panupat.baseproject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "user_group")
public class UserGroupEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;
    private Boolean active;

    @OneToMany(mappedBy = "userGroupEntity")
    private List<GroupAuthorityEntity> groupAuthorityEntities;
}
