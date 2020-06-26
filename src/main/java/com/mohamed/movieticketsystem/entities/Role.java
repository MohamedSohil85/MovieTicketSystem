package com.mohamed.movieticketsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;
    private String role;
    @ManyToOne
    @JsonIgnore
    private RegistretedUser registretedUser;
    public Role() {
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public RegistretedUser getRegistretedUser() {
        return registretedUser;
    }

    public void setRegistretedUser(RegistretedUser registretedUser) {
        this.registretedUser = registretedUser;
    }
}
