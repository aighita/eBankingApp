package org.poo.cb.Interfaces;

import org.poo.cb.Classes.Account;

public interface UserInterface {
    void addFriend(String email);
    boolean alreadyFriend(String emailFriend);
    Account getAccountByCurrency(String currency);
    void deposit(String currency, double amount);
    boolean checkAccountExistence(Account account);
    void addAccount(Account account);
}
