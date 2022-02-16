package com.example.awsimageupload.service;

import com.example.awsimageupload.model.UserProfile;
import com.example.awsimageupload.repo.FakeUserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FakeUserProfileAccess {

    //this DI let changes e.g. new db connection be a lot easier.
    private final FakeUserProfile fakeUserProfile;
    @Autowired
    public FakeUserProfileAccess(FakeUserProfile userProfile) {
        this.fakeUserProfile = userProfile;
    }
    List<UserProfile> getUserProfiles(){
        return fakeUserProfile.getUserProfiles();
    }
}
