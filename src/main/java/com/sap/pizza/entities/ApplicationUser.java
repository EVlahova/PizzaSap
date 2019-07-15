package com.sap.pizza.entities;

import com.sap.pizza.converters.UserRoleConverter;
import com.sap.pizza.enums.UserRole;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 11, nullable = false)
    private final int id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_role", length = 32, columnDefinition = "varchar(32) default 'USER'", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @Convert(converter = UserRoleConverter.class)
    private final UserRole role;

    public ApplicationUser(){
        //for JPA
        this(0,null,null,null);
    }

    public ApplicationUser(String username, String password, UserRole role){
        this(0, username, password, role);
    }

    public ApplicationUser(int id, String username, String password, UserRole role){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }
}
