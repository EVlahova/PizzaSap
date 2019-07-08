package com.sap.pizza.entities;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 11, nullable = false)
    private final int id;

    @Column(name = "username", nullable = false)
    private final String username;

    @Column(name = "password", nullable = false)
    private final String password;

    @Column(name = "user_role", nullable = false)
    private final String role;

    public ApplicationUser(){
        //for JPA
        this(0,null,null,null);
    }

    public ApplicationUser(int id, String username, String password, String role){
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

    public String getRole() {
        return role;
    }
}
