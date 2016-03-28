package com.rasulov.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@Entity
@Table(name = "role")
public class Role {
    @ManyToMany(mappedBy = "userRoles")
    private Set<User> users = new HashSet<>();

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ListRole listRole;



}
