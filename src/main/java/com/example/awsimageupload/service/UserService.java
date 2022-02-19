package com.example.awsimageupload.service;

import com.example.awsimageupload.aws.BucketName;
import com.example.awsimageupload.aws.FileStorage;
import com.example.awsimageupload.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UserService {

    private final FakeUserProfileAccess fakeUserProfileAccess;
    private final FileStorage fileStorage;
    @Autowired
    public UserService(FakeUserProfileAccess fakeUserProfileAccess, FileStorage fileStorage) {
        this.fakeUserProfileAccess = fakeUserProfileAccess;
        this.fileStorage = fileStorage;
    }
    public List<UserProfile> getUserProfiles(){
        return fakeUserProfileAccess.getUserProfiles();
    }

    public void uploadImage(UUID userId, MultipartFile file) {
        //File is not empty and it is an image.
        isCorrectFile(file);
        //User exists in database (fake-data-storage for now).
        UserProfile user = getUser(userId);
        //Get data from the file if any.
        Map<String, String> metadata = getMetadata(file);

        //Store the image in s3 and update the image link in user profile.
        String path = String.format("%s/%s", BucketName.PROFILE_IMG.getBucketName(), user.getId());
        String filename = String.format("%s-%s", file.getName(), UUID.randomUUID());
        try {
            fileStorage.save(path, filename, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    private Map<String, String> getMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private UserProfile getUser(UUID userId) {
        return fakeUserProfileAccess
                .getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User %s is not found", userId)));
    }

    private void isCorrectFile(MultipartFile file) {
        if(file == null || file.getContentType() == null || !file.getContentType().toLowerCase().startsWith("image/")){
            throw new MultipartException("Couldn't store the file. File must be an image.");
        }
    }
}
