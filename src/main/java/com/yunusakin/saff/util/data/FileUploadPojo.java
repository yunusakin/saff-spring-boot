package com.yunusakin.saff.util.data;

public class FileUploadPojo {
    private String filePath;
    private String fileCode;

    public FileUploadPojo() {
    }

    public FileUploadPojo(String filePath, String fileCode) {
        this.filePath = filePath;
        this.fileCode = fileCode;
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
}
