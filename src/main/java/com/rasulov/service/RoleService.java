package com.rasulov.service;

import com.rasulov.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles(String search);

    Boolean add(Role role);

    void delete(Role role);

    void update(Role role);

}
