package com.yunusakin.saff.controller;

import com.yunusakin.saff.constants.SaffConstants;
import com.yunusakin.saff.controller.data.AllFilesResponse;
import com.yunusakin.saff.controller.data.FileByFileCodeResponse;
import com.yunusakin.saff.controller.data.FileInfoDTO;
import com.yunusakin.saff.controller.data.FileUploadResponse;
import com.yunusakin.saff.handler.ResponseHandler;
import com.yunusakin.saff.mapper.SaffMapper;
import com.yunusakin.saff.model.SaffFile;
import com.yunusakin.saff.service.SaffFileService;
import com.yunusakin.saff.util.SaffFileUtil;
import com.yunusakin.saff.util.data.FileUploadPojo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RestController
public class SaffFileController {

    @Autowired
    SaffFileService saffFileService;

    @GetMapping(value = "/saff/fetch")
    private ResponseEntity<Object> getAllFiles()  {
        try {
            List<SaffFile> saffFileList = saffFileService.getAllFiles();
            List<FileInfoDTO> list = SaffMapper.convertList(saffFileList);
            return ResponseHandler.generateResponse(true,null, HttpStatus.OK, new AllFilesResponse(list));
        } catch (Exception e) {
            return this.returnErrorResponse(e);
        }
    }

    @PostMapping("/saff/uploadFile")
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        try {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            String fileExtension = FilenameUtils.getExtension(fileName);
            if(!SaffConstants.validExtensions.contains(fileExtension)){
                return ResponseHandler.generateResponse(false, SaffConstants.INVALID_EXTENSION, HttpStatus.FORBIDDEN, null);
            }
            long size = multipartFile.getSize();
            if (size > SaffConstants.VALID_FILE_SIZE) {
                return ResponseHandler.generateResponse(false, SaffConstants.INVALID_FILE_SIZE, HttpStatus.FORBIDDEN, null);
            }

            FileUploadPojo fileUploadPojo = SaffFileUtil.saveFile(fileName, multipartFile);
            SaffFile newSaffFile = new SaffFile();
            newSaffFile.setFileName(fileName);
            newSaffFile.setFileExtension(fileExtension);
            newSaffFile.setFileSize(size);
            newSaffFile.setFileCode(fileUploadPojo.getFileCode());
            newSaffFile.setFilePath(fileUploadPojo.getFilePath());
            saffFileService.saveOrUpdate(newSaffFile);

            FileUploadResponse response = new FileUploadResponse();
            response.setFileName(fileName);
            response.setSize(size);
            response.setFileCode(fileUploadPojo.getFileCode());
            return ResponseHandler.generateResponse(true, null, HttpStatus.OK, response);
        } catch (Exception e) {
            return this.returnErrorResponse(e);
        }
    }

    @GetMapping("/saff/fetch/{fileCode}")
    public ResponseEntity<Object> getFileByFileCode(@PathVariable("fileCode") String fileCode)  {
        try {
            Resource resource = SaffFileUtil.getFileAsResource(fileCode);
            if (resource == null) {
                return ResponseHandler.generateResponse(false, "File not found", HttpStatus.NOT_FOUND, null);
            }
            SaffFile saffFile = saffFileService.getByFileCode(fileCode);
            if (saffFile == null) {
                return ResponseHandler.generateResponse(false, "File not found", HttpStatus.NOT_FOUND, null);
            }
            byte[] fileData = FileUtils.readFileToByteArray(resource.getFile());

            return ResponseHandler.generateResponse(true, null, HttpStatus.OK, new FileByFileCodeResponse(saffFile.getFileName(), saffFile.getFileSize() +" byte",fileData));
        } catch (Exception e) {
            return this.returnErrorResponse(e);
        }
    }

    @DeleteMapping("/saff/file/{fileId}")
    private ResponseEntity<Object> deleteFile(@PathVariable("fileId") int fileId) {
        try {
            saffFileService.delete(fileId);
            return ResponseHandler.successResponse();
        } catch (EmptyResultDataAccessException e) {
            return ResponseHandler.generateResponse(true, "No Records Found", HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return this.returnErrorResponse(e);
        }
    }
    @DeleteMapping("/saff/delete/{fileCode}")
    private ResponseEntity<Object> deleteFile(@PathVariable("fileCode") String fileCode) {
        try {
            Resource resource = SaffFileUtil.getFileAsResource(fileCode);
            if (resource == null) {
                return ResponseHandler.generateResponse(false, "File not found", HttpStatus.NOT_FOUND, null);
            }
            SaffFile saffFile = saffFileService.getByFileCode(fileCode);
            if (saffFile == null) {
                return ResponseHandler.generateResponse(false, "File not found", HttpStatus.NOT_FOUND, null);
            }
            FileUtils.deleteQuietly(resource.getFile());
            saffFileService.delete(saffFile.getFileId());
            return ResponseHandler.successResponse();
        } catch (EmptyResultDataAccessException e) {
            return ResponseHandler.generateResponse(true, "No Records Found", HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return this.returnErrorResponse(e);
        }
    }

    private ResponseEntity<Object> returnErrorResponse(Exception e){
        return ResponseHandler.generateResponse(false,e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
}
