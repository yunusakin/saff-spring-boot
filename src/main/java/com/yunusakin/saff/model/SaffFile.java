package com.yunusakin.saff.model;

import javax.persistence.*;

@Entity
@Table(name = "SAFF_FILE")
@NamedQueries({
        @NamedQuery(name = "SaffFile.findByFileCode", query = "select sf from SaffFile sf where sf.fileCode=:fileCode")
})
public class SaffFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_ID")
    private int fileId;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "FILE_CODE")
    private String fileCode;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_EXTENSION")
    private String fileExtension;

    @Column(name = "FILE_SIZE")
    private long fileSize;

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

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
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

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
}
