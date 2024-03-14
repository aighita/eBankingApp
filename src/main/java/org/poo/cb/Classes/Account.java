package org.poo.cb.Classes;

import org.poo.cb.Interfaces.AccountInterface;

public class Account implements AccountInterface {
    protected double balance;
    protected String currency;

    public Account(String currency) {
        this.balance = 0.00;
        this.currency = currency;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > this.balance) {
            return false;
        }
        this.balance -= amount;
        return true;
    }

    @Override
    public int hashCode() {
        return currency.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Account account = (Account) obj;
        return currency.equals(account.currency);
    }
}
