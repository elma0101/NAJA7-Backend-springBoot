// src/main/java/com/naja7online/api/model/User.java

package com.naja7online.api.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails; // <-- IMPORT THIS

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users") // Your table name
public class User implements UserDetails { // <-- IMPLEMENT THE INTERFACE HERE

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String level;

    // Assuming you have a String or Enum for role
    private String role;

    // --- Getters and Setters for your existing fields ---
    // (You can use Lombok's @Data or generate them manually)
    // ... id, name, email, password, level, role ...


    // =================================================================
    //  METHODS REQUIRED BY THE UserDetails INTERFACE
    // =================================================================

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // This method must return a list of roles/permissions for the user.
        // We will create a SimpleGrantedAuthority from the 'role' string.
        return List.of(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getPassword() {
        // This must return the HASHED password.
        return this.password;
    }

    @Override
    public String getUsername() {
        // This is the unique identifier for the user. We will use the email.
        return this.email;
    }

    // --- The following methods are for account status ---
    // For now, we can just return 'true' to indicate that accounts are always active.

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLevel() {
        return level;
    }

    public String getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setRole(String role) {
        this.role = role;
    }
}