package com.athletiquest.athletiquest_api.dto.repository;

import com.athletiquest.athletiquest_api.dto.entity.Role;
import com.athletiquest.athletiquest_api.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    Role findByRoleType(RoleType roleType);
}
