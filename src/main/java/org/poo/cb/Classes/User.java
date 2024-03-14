package org.poo.cb.Classes;

import org.poo.cb.Interfaces.UserInterface;

import java.util.*;

public class User implements UserInterface {
    private final String email;
    private final String address;
    String firstName;
    String lastName;
    List<String> friends;
    Portfolio portfolio;
    boolean premium;

    public String getEmail() {
        return this.email;
    }

    public User(String email, String address, String firstName, String lastName) {
        this.email = email;
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.friends = new ArrayList<>();
        this.portfolio = new Portfolio();
        this.premium = false;
    }

    public Account getAccountByCurrency(String currency) {
        for (Account acc : this.portfolio.accounts) {
            if (currency.equals(acc.currency)) {
                return acc;
            }
        }
        return null;
    }

    public boolean alreadyFriend(String emailFriend) {
        if (this.friends == null) this.friends = new ArrayList<>();
        return this.friends.contains(emailFriend);
    }

    public void addFriend(String email) {
        if (this.friends == null) this.friends = new ArrayList<>();
        this.friends.add("\"" + email + "\"");
    }

    public void deposit(String currency, double amount) {
        for (Account acc : this.portfolio.accounts) {
            if (currency.equals(acc.currency)) {
                acc.deposit(amount);
            }
        }
    }

    public boolean checkAccountExistence(Account account) {
        if (this.portfolio.accounts == null) this.portfolio.accounts = new HashSet<>();
        return this.portfolio.accounts.contains(account);
    }

    public void addAccount(Account account) {
        if (this.portfolio.accounts == null) this.portfolio.accounts = new HashSet<>();
        this.portfolio.accounts.add(account);
    }

    @Override
    public String toString() {
        String str = "{\"email\":\"" + this.email + "\"," +
                "\"firstname\":\"" + this.firstName + "\"," +
                "\"lastname\":\"" + this.lastName + "\"," +
                "\"address\":\"" + this.address + "\"," +
                "\"friends\":";
        String friends = "";
        if (this.friends != null) {
            friends = this.friends.toString();
        } else {
            friends = "[]";
        }
        str = str + friends + "}";
        return str;
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return email.equals(user.email);
    }
}