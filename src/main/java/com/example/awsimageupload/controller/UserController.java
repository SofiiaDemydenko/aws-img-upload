package com.example.awsimageupload.controller;

import com.example.awsimageupload.model.UserProfile;
import com.example.awsimageupload.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user-profile")
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
}
