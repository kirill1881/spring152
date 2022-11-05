package com.example.spring152.services;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

@Service
public class FirebaseImageService {
    private Storage storage = StorageOptions.getDefaultInstance().getService();

    public String save(MultipartFile multipartFile){
        try {
            String imageName = String.valueOf(System.currentTimeMillis());
            BlobId blobId = BlobId.of("spring152-8e2ac.appspot.com", imageName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(multipartFile.getContentType())
                    .build();
            storage = StorageOptions.newBuilder()
                    .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream("spring152-8e2ac-firebase-adminsdk-1bd5c-1783d79ec0.json")))
                    .build()
                    .getService();
            storage.create(blobInfo, multipartFile.getInputStream());
            return imageName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //https://firebasestorage.googleapis.com/v0/b/spring152-8e2ac.appspot.com/o/1667132988564?alt=media&token=41dd4a87-905b-464b-98fd-7ed35357d8f2
    public String getImgUrl(String fileName){
        return "https://firebasestorage.googleapis.com/v0/b/spring152-8e2ac.appspot.com/o/" + fileName + "?alt=media&token=41dd4a87-905b-464b-98fd-7ed35357d8f2";
    }
}
