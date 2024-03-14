package org.poo.cb.Exceptions;

public class TransferNotAllowed extends Exception {
    public TransferNotAllowed(String email) {
        super("You are not allowed to transfer money to " + email);
    }
}
