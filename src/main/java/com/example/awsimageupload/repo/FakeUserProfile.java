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
        USER_PROFILES.add(new UserProfile(UUID.fromString("2657eddb-b418-4163-8559-6e804e596720"),
                "hannah lane", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("dc74154c-dfe9-48ca-814f-de04db6eec71"),
                "jeremy clark", null));
    }
    public List<UserProfile> getUserProfiles(){
        return USER_PROFILES;
    }
}
