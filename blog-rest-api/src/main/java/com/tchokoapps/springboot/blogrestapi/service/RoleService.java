package com.tchokoapps.springboot.blogrestapi.service;

import com.tchokoapps.springboot.blogrestapi.entity.Role;

public interface RoleService {
    public Role createRole(Role role);

    public Role findRoleById(long id);
}
