package com.esgi.modules.user.exposition;

public class UserResponse {
    public String id;
    public String lastname;
    public String firstname;
    public String email;
    public String image;
    public boolean isFollowed;

    public UserResponse(String id, String lastname, String firstname, String email, String image) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.image = image;
        this.isFollowed = false;
    }

    public UserResponse(String id, String lastname, String firstname, String email, String image, boolean isFollowed) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.image = image;
        this.isFollowed = isFollowed;
    }
}
