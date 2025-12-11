package com.athletiquest.athletiquest_api.dto.entity;

import com.athletiquest.athletiquest_api.enums.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleType roleType;

    @Column(nullable = false)
    private String name;

    public Role(RoleType roleType) {
        this.roleType = roleType;
        this.name = roleType.name();
    }
}
