package com.programming.techie.springngblog.dto;

import com.programming.techie.springngblog.model.ImageFile;
import com.programming.techie.springngblog.model.Reaction;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

public class PostDto {
    private Long id;
    private String content;
    private String title;
    private String username;
    private Instant createdOn;
    private Integer nbrreaction;

    private ImageFile imageFile;

    public ImageFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(ImageFile imageFile) {
        this.imageFile = imageFile;
    }

    @OneToMany
    @JoinColumn(name = "image_files")
    private List<ImageFile> imageFiles;

    public Integer getNbrreaction() {
        return nbrreaction;
    }

    public void setNbrreaction(Integer nbrreaction) {
        this.nbrreaction = nbrreaction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }
}