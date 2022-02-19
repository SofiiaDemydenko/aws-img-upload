package com.example.awsimageupload.controller;

import com.example.awsimageupload.model.UserProfile;
import com.example.awsimageupload.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user-profile")
@CrossOrigin("http://localhost:3000")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserProfile> getUserProfiles(){
        return userService.getUserProfiles();
    }

    @PostMapping(
            path = "/{userId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadImage(@PathVariable("userId")UUID userId,
                            @RequestParam("file")MultipartFile file){
        userService.uploadImage(userId, file);
    }
}
