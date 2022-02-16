package com.example.awsimageupload.aws;

public enum BucketName {
    PROFILE_IMG("aws-upload-imgs-spring-project");
    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }
    public String getBucketName() {
        return bucketName;
    }
}
