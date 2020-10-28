package com.example.t3mb_decor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.io.File;

@Service
@Transactional
public class UploadPathServiceImpl implements UploadPathService{

    @Autowired
    ServletContext context;

    @Override
    public File getFilePath(String modifiedName, String path) {
        boolean exists = new File(context.getRealPath("/" + path + "/")).exists();
        if(!exists){
            new File(context.getRealPath("/" + path + "/")).mkdir();
        }
        String modifiedFilePath = context.getRealPath("/" + path + "/" + File.separator+modifiedName);
        File file = new File(modifiedFilePath);
        return file;
    }
}
