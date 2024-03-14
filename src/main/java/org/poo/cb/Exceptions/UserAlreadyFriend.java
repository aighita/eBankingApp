package org.poo.cb.Exceptions;

public class UserAlreadyFriend extends Exception {
    public UserAlreadyFriend(String emailFriend) {
        super("User with " + emailFriend + " is already a friend");
    }
}
