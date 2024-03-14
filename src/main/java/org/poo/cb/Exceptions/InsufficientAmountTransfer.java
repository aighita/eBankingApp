package org.poo.cb.Exceptions;

public class InsufficientAmountTransfer extends Exception {
    public InsufficientAmountTransfer(String currency) {
        super("Insufficient amount in account " + currency + " for transfer");
    }
}
