package com.example.awsimageupload.storage;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStorage {
    private final AmazonS3 amazonS3;

    @Autowired
    public FileStorage(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public void save(String path,
                     String filename,
                     Optional<Map<String, String>> optionalMetadata,
                     InputStream inputStream){
        /*
        Represents the object metadata that is stored with Amazon S3. This includes custom user-supplied metadata,
        as well as the standard HTTP headers that Amazon S3 sends and receives (Content-Length, ETag, Content-MD5,
        etc.).
         */
        ObjectMetadata objectMetadata = new ObjectMetadata();
        optionalMetadata.ifPresent(map -> {
            if(!map.isEmpty()){
                map.forEach(objectMetadata::addUserMetadata);
            }
        });
        /*
        addUserMetadata(String key, String value)
        Adds the key value pair of custom user-metadata for the associated object.
         */

        try{
            amazonS3.putObject(path, filename, inputStream, objectMetadata);
        }catch (AmazonServiceException e){
            throw new IllegalStateException("Failed to store file to s3", e);
        }
    }
}
