package com.yunusakin.saff.service;

import com.yunusakin.saff.model.File;
import com.yunusakin.saff.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    @Autowired
    FileRepository fileRepository;

    public List<File> getAllFiles(){
        List<File> fileList = new ArrayList<>();
        fileRepository.findAll().forEach(file -> fileList.add(file));
        return fileList;
    }
    public File getById(int id){
        return fileRepository.findById(id).get();
    }
    public void saveOrUpdate (File file){
        fileRepository.save(file);
    }
    public void delete (int id ){
        fileRepository.deleteById(id);
    }
}
