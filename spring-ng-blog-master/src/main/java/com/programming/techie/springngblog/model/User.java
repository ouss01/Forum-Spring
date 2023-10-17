package com.programming.techie.springngblog.model;

import lombok.Builder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private String email;
    
    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'user'") // Set default value for role
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    @OneToMany(mappedBy = "sender")
    private List<ChatMessage> sentMessages;
    @OneToMany(mappedBy = "receiver")
    private List<ChatMessage> receivedMessages;

    @OneToMany
    @JoinColumn(name = "reaction_id")
    private List<Reaction> reactions;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}