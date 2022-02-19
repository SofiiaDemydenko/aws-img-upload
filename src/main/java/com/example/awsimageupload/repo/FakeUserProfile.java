package com.example.awsimageupload.repo;

import com.example.awsimageupload.model.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfile {
    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();
    static {
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "hannah lane", null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "jeremy clark", null));
    }
    public List<UserProfile> getUserProfiles(){
        return USER_PROFILES;
    }
}
