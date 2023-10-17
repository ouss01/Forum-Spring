package com.programming.techie.springngblog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


@Entity
@Table
@Getter
@Setter
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Lob
    @Column
    @NotEmpty
    private String content;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "UTC")
    @Column
    private Date createdOn;
    @Column
    private Instant updatedOn;
    @Column
    private String username;
    @Column
    private Integer nbrreaction=0;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)

    private Reaction reaction;
    @OneToMany
    @JoinColumn(name = "image_file")
    private List<ImageFile> imageFiles;

}