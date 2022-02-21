package com.example.awsimageupload.model;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserProfile {
    private final UUID id;
    private final String username;
    private String imageLinkS3;

    public UserProfile(UUID id, String username, String imageLinkS3) {
        this.id = id;
        this.username = username;
        this.imageLinkS3 = imageLinkS3;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
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
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(imageLinkS3, that.imageLinkS3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, imageLinkS3);
    }
}
