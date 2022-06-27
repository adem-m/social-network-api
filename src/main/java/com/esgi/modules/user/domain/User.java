package com.esgi.modules.user.domain;

import com.esgi.kernel.Entity;

import java.util.Objects;

public final class User implements Entity<UserId> {
    private final UserId id;
    private final String lastname;
    private final String firstname;
    private Email email;
    private String password;
    private String image;

    private boolean isFollowed;

    public User(UserId id, String lastname, String firstname, Email email, String password) {
        this.id = Objects.requireNonNull(id);
        this.lastname = Objects.requireNonNull(lastname);
        this.firstname = Objects.requireNonNull(firstname);
        this.email = Objects.requireNonNull(email);
        this.password = Objects.requireNonNull(password);
        this.isFollowed = false;
        this.image = null;
    }

    public User(UserId id, String lastname, String firstname, Email email, String password, boolean isFollowed) {
        this.id = Objects.requireNonNull(id);
        this.lastname = Objects.requireNonNull(lastname);
        this.firstname = Objects.requireNonNull(firstname);
        this.email = Objects.requireNonNull(email);
        this.password = Objects.requireNonNull(password);
        this.isFollowed = isFollowed;
    }

    public User(UserId id, String lastname, String firstname, Email email, String password, String image, boolean isFollowed) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.image = image;
        this.isFollowed = isFollowed;
    }

    @Override
    public UserId id() {
        return id;
    }

    public UserId getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public Email getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String newPassword) {
        this.password = Objects.requireNonNull(newPassword);
    }

    public void setFollowed(boolean followed) {
        isFollowed = followed;
    }

    public void changeEmail(Email newEmail) {
        this.email = Objects.requireNonNull(newEmail);
    }

    public void changeImage(String newImage) {
        this.image = newImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(lastname, user.lastname) && Objects.equals(firstname, user.firstname) && Objects.equals(password, user.password) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastname, firstname, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email=" + email +
                ", password='" + password + '\'' +
                '}';
    }

    public boolean isFollowed() {
        return isFollowed;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
