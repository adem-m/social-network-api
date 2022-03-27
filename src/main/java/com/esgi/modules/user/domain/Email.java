package com.esgi.modules.user.domain;

public class Email {
    private String email;

    public Email(String email){
        if(!email.matches(".+@.+\\.[a-z]+"))
            throw new IllegalArgumentException("You must provide a valid email.");
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString(){
        return "Email{'" + email + "'}";
    }
}
