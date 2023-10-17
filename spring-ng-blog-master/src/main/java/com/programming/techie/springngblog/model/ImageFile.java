package com.programming.techie.springngblog.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
public class ImageFile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fileName;

    @Lob
    @Column
    private byte[] data;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
