package com.tchokoapps.springboot.blogrestapi.service;

import com.tchokoapps.springboot.blogrestapi.entity.Role;

public interface RoleService {

    Role createRole(Role role);

    Role findRoleById(long id);

    Role findByName(String name);
}
