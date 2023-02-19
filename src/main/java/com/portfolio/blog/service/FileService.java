package com.portfolio.blog.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;

@Service
@Log
public class FileService {

    public  String uploadFile(
            String uploadPath,
            String fileName,
            byte[] fileDate) throws Exception{
        String time = LocalDateTime.now().toString();

        String ext = fileName.substring(fileName.lastIndexOf("."));
        String saveFileName = fileName+"_"+time;
        String fileUploadFulUrl = uploadPath+ File.pathSeparator+saveFileName;

        FileOutputStream fos = new FileOutputStream(fileUploadFulUrl);
        fos.write(fileDate);
        fos.close();

        return saveFileName;
    }
    public void deleteFile(String filePath) throws  Exception{
        File deleteFile = new File(filePath);
        if(deleteFile.exists()){
            deleteFile.delete();
            log.info("파일이 삭제되었습니다.");
        }else{
            log.info("파일일 존재하지 않습니다.");
        }
    }
}
