package com.panupat.baseproject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "group_authority")
public class GroupAuthorityEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authorityName;

    @ManyToOne
    @JoinColumn(name = "user_group_id")
    private UserGroupEntity userGroupEntity;
}
