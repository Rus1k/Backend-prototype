package com.rasulov.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@Entity
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    @Column(name = "name")
    private String name;



}
