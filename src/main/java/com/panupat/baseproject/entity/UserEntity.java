package com.panupat.baseproject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "user")
@NamedEntityGraph(
        name = "UserEntity.userGroup",
        attributeNodes = @NamedAttributeNode(value = "userGroupEntity", subgraph = "subgraph.authority"),
        subgraphs = @NamedSubgraph(name = "subgraph.authority",
                attributeNodes = @NamedAttributeNode(value = "groupAuthorityEntities")
        )
)
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;

    @Column(length = 60)
    private String password;

    @ManyToOne
    @JoinColumn(name = "user_group_id")
    private UserGroupEntity userGroupEntity;
}
