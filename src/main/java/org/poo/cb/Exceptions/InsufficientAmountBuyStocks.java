package org.poo.cb.Exceptions;

public class InsufficientAmountBuyStocks extends Exception {
    public InsufficientAmountBuyStocks() {
        super("Insufficient amount in account for buying stock");
    }
}
