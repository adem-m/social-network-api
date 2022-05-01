package com.esgi.modules.user.exposition;

public class UserResponse {
    public String id;
    public String lastname;
    public String firstname;
    public String email;

    public UserResponse(String id, String lastname, String firstname, String email) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email=" + email + '\'' +
                '}';
    }
}
