package org.poo.cb.Interfaces;

import org.poo.cb.Classes.User;

public interface eBankInterface {
    void createUser(String email, String firstName, String lastName, String address);
    void addFriends(String email, String emailFriend);
    void createAccount(String email, String currency);
    void listUser(String email);
    void listPortfolio(String email);
    void buyPremium(String email);
    void recommendStocks();
    void buyStocks(String email, String company, int noOfStocks);
    void transferMoney(String email, String friendEmail, String currency, double amount);
    void exchangeMoney(String email, String sourceCurrency, String destinationCurrency, double amount);
    void addMoney(String email, String currency, double amount);
}
