package com.example.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileTools {


    public static String writeFile(String root,String path, MultipartFile file){

        String originName = file.getOriginalFilename();
        String newName = rename(originName);
        try {
            file.transferTo(new File(root+path + newName));
        }catch(IOException e){
            e.printStackTrace();
        }
        return path+newName;
    }

    public static int deleteFile(String root,String fileName){
        File file = new File(root+fileName);
        if(file.exists()){
            file.delete();
            return 0;
        }
        else
            return 1;

    }
    public static String rename(String originName){
        int index = originName.lastIndexOf(".");
        String fileName = UUID.randomUUID().toString();

        String fullName = index>0?fileName+originName.substring(index):fileName;
        return fullName;
    }
}
