package com.programming.techie.springngblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Reaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReactionType type;
    @JsonIgnore
    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'FALSE'")
    private String status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}