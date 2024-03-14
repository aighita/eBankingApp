package org.poo.cb.Exceptions;

public class AccountAlreadyExists extends Exception {
    public AccountAlreadyExists(String currency) {
        super("Account in currency " + currency + " already exists for user");
    }
}
