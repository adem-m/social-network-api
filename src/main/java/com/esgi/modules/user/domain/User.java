package com.esgi.modules.user.domain;

import com.esgi.kernel.*;

import java.util.Objects;

public final class User implements Entity<UserId> {
    private final UserId id;
    private final String lastname;
    private final String firstname;
    private Email email;
    private String password;

    public User(UserId id, String lastname, String firstname, Email email, String password) {
        this.id = Objects.requireNonNull(id);
        this.lastname = Objects.requireNonNull(lastname);
        this.firstname = Objects.requireNonNull(firstname);
        this.email = Objects.requireNonNull(email);
        this.password = Objects.requireNonNull(password);
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

    public void changeEmail(Email newEmail) {
        this.email = Objects.requireNonNull(newEmail);
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
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
