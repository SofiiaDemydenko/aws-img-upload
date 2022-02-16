package com.example.awsimageupload.model;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserProfile {
    private UUID id;
    private String username;
    private String imageLinkS3;

    public UserProfile(UUID id, String username, String imageLinkS3) {
        this.id = id;
        this.username = username;
        this.imageLinkS3 = imageLinkS3;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Optional<String> getImageLinkS3() {
        return Optional.ofNullable(imageLinkS3);
    }

    public void setImageLinkS3(String imageLinkS3) {
        this.imageLinkS3 = imageLinkS3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return id.equals(that.id) && username.equals(that.username) && imageLinkS3.equals(that.imageLinkS3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, imageLinkS3);
    }
}
