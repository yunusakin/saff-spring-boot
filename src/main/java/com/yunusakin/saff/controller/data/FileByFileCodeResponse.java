package com.yunusakin.saff.controller.data;

public class FileByFileCodeResponse {

    private String fileName;
    private String fileSize;

    private byte[] fileData;

    public FileByFileCodeResponse() {
    }

    public FileByFileCodeResponse(String fileName, String fileSize, byte[] fileData) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileData = fileData;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
}
