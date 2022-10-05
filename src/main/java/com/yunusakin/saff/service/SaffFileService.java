package com.yunusakin.saff.service;

import com.yunusakin.saff.model.SaffFile;
import com.yunusakin.saff.repository.CustomSAFFRepository;
import com.yunusakin.saff.repository.SaffFileRepository;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaffFileService {

    @Autowired
    SaffFileRepository saffFileRepository;
    @Autowired
    CustomSAFFRepository customSAFFRepository;

    public List<SaffFile> getAllFiles(){
        List<SaffFile> saffFileList = new ArrayList<>();
        saffFileRepository.findAll().forEach(saffFileList::add);
        return saffFileList;
    }
    public SaffFile getById(int id){
        return saffFileRepository.findById(id).get();
    }
    public void saveOrUpdate (SaffFile saffFile){
        saffFileRepository.save(saffFile);
    }
    public void delete (int id ){
        saffFileRepository.deleteById(id);
    }
    public SaffFile getByFileCode(String fileCode){
        return customSAFFRepository.getByFileCode(fileCode);
    }
}
