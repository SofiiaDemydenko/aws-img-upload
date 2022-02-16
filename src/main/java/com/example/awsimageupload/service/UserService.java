package com.example.awsimageupload.service;

import com.example.awsimageupload.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final FakeUserProfileAccess fakeUserProfileAccess;
    @Autowired
    public UserService(FakeUserProfileAccess fakeUserProfileAccess) {
        this.fakeUserProfileAccess = fakeUserProfileAccess;
    }
    public List<UserProfile> getUserProfiles(){
        return fakeUserProfileAccess.getUserProfiles();
    }
}
