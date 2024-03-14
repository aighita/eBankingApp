package org.poo.cb.Exceptions;

public class InsufficientAmountPremium extends Exception {
    public InsufficientAmountPremium() {
        super(" Insufficient amount in account for buying premium option");
    }
}
