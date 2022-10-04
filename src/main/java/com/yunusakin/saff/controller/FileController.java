package com.yunusakin.saff.controller;

import com.yunusakin.saff.controller.data.AllFilesResponse;
import com.yunusakin.saff.controller.data.FileResponse;
import com.yunusakin.saff.handler.ResponseHandler;
import com.yunusakin.saff.model.File;
import com.yunusakin.saff.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping(value = "/saff/allFiles")
    private ResponseEntity<Object> getAllFiles()  {
        try {
            List<File> fileList = fileService.getAllFiles();
            return ResponseHandler.generateResponse(true,null, HttpStatus.OK, new AllFilesResponse(fileList));
        } catch (Exception e) {
            return this.returnErrorResponse(e);
        }
    }

    @GetMapping("/saff/file/{fileId}")
    private ResponseEntity<Object> getFile(@PathVariable("fileId") int fileId) {
        try {
            File file = fileService.getById(fileId);
            return ResponseHandler.generateResponse(true,null, HttpStatus.OK, new FileResponse(file));
        } catch (NoSuchElementException e) {
            return ResponseHandler.generateResponse(true, e.getMessage(), HttpStatus.NOT_FOUND,null);
        } catch (Exception e) {
            return this.returnErrorResponse(e);
        }
    }

    @PostMapping("/saff/saveFile")
    private ResponseEntity<Object> saveFile(@RequestBody File file){
        try {
            fileService.saveOrUpdate(file);
            return ResponseHandler.successResponse();
        } catch (Exception e) {
            return this.returnErrorResponse(e);
        }
    }

    @DeleteMapping("/saff/file/{fileId}")
    private ResponseEntity<Object> deleteFile(@PathVariable("fileId") int fileId) {
        try {
            fileService.delete(fileId);
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
