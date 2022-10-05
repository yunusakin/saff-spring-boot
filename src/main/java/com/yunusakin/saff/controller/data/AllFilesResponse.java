package com.yunusakin.saff.controller.data;

import java.util.List;


public class AllFilesResponse {
    private List<FileInfoDTO> infoList;

    public AllFilesResponse(List<FileInfoDTO> infoList) {
        this.infoList = infoList;
    }

    public AllFilesResponse() {
    }

    public List<FileInfoDTO> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<FileInfoDTO> infoList) {
        this.infoList = infoList;
    }
}
