package com.yunusakin.saff.model;

import javax.persistence.*;

@Entity
@Table
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_ID")
    private int fileId;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_EXTENSION")
    private String fileExtension;

    @Column(name = "FILE_SIZE")
    private Integer fileSize;

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }
}
