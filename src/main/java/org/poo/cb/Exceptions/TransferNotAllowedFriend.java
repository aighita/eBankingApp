package org.poo.cb.Exceptions;

public class TransferNotAllowedFriend extends Exception {
    public TransferNotAllowedFriend(String emailFriend) {
        super("You are not allowed to transfer money to " + emailFriend);
    }
}
