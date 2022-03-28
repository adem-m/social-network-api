package com.esgi.modules.user.exposition;

public class UserResponse {
    public String id;
    public String lastname;
    public String firstname;
    public EmailResponse email;
    public String password;

    public UserResponse(String id, String lastname, String firstname, EmailResponse email, String password) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email=" + email + '\'' +
                ", password=" + password + '\'' +
                '}';
    }
}