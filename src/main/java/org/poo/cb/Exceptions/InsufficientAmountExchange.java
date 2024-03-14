package org.poo.cb.Exceptions;

public class InsufficientAmountExchange extends Exception {
    public InsufficientAmountExchange(String currency) {
        super("Insufficient amount in account " + currency + " for exchange");
    }
}
