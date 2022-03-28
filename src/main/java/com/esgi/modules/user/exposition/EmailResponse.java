package com.esgi.modules.user.exposition;

public class EmailResponse {
    public final String email;

    public EmailResponse(String email){
        if(!email.matches(".+@.+\\.[a-z]+"))
            throw new IllegalArgumentException("You must provide a valid email.");
        this.email = email;
    }

    @Override
    public String toString(){
        return "Email{'" + email + "'}";
    }
}
