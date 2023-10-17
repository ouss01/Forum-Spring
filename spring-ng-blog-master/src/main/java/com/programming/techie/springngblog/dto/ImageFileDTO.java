package com.programming.techie.springngblog.dto;

import javax.persistence.Column;
import javax.persistence.Lob;

public class ImageFileDTO {

    private Long id;


    private String fileName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    private byte[] data;
}
