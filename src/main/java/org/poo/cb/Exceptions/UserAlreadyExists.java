package org.poo.cb.Exceptions;

public class UserAlreadyExists extends Exception {
    public UserAlreadyExists(String email) {
        super("User with " + email + " already exists");
    }
}
