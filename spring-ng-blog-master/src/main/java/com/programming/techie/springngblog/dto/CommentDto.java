package com.programming.techie.springngblog.dto;

import java.time.Instant;
import java.util.Date;

public class CommentDto {

    private Long id;
    private String content;
    private Instant createdOn;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
