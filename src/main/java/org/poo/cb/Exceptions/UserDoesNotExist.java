package org.poo.cb.Exceptions;

public class UserDoesNotExist extends Exception {
    public UserDoesNotExist(String emailUser) {
        super("User with " + emailUser + " doesn't exist");
    }
}
