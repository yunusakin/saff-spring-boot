package com.yunusakin.saff.mapper;

import com.yunusakin.saff.controller.data.FileInfoDTO;
import com.yunusakin.saff.model.SaffFile;

import java.util.ArrayList;
import java.util.List;

public class SaffMapper {

   public static List<FileInfoDTO> convertList(List<SaffFile> saffFileList) {
        List<FileInfoDTO> list = new ArrayList<>(saffFileList.size());
        for (SaffFile saffFile : saffFileList) {
            list.add(convertToPojo(saffFile));
        }
        return list;
    }

    private static FileInfoDTO convertToPojo(SaffFile saffFile) {
        FileInfoDTO fileInfoDTO = new FileInfoDTO();
        fileInfoDTO.setFileName(saffFile.getFileName());
        fileInfoDTO.setFileCode(saffFile.getFileCode());
        fileInfoDTO.setFileSize(saffFile.getFileSize() + " byte");
        fileInfoDTO.setFilePath(saffFile.getFilePath());
        fileInfoDTO.setDownloadUri("/saff/fetch/" + saffFile.getFileCode().trim());
        return fileInfoDTO;
    }
}
