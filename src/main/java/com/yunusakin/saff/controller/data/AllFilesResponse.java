package com.yunusakin.saff.controller.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yunusakin.saff.model.File;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AllFilesResponse {
    @JsonProperty("info")
    private List<File> fileList;

    public AllFilesResponse() {
    }

    public AllFilesResponse( List<File> fileList) {
        this.fileList = fileList;
    }

    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }
}
