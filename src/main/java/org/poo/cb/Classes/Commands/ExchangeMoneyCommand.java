package org.poo.cb.Classes.Commands;

import org.poo.cb.Classes.eBank;
import org.poo.cb.Interfaces.CommandInterface;

public class ExchangeMoneyCommand implements CommandInterface {
    private final eBank ebank;
    private final String email;
    private final String sourceCurrency;
    private final String destinationCurrency;
    private final String amount;


    public ExchangeMoneyCommand(String[] args) {
        this.ebank = eBank.getInstance();
        this.email = args[0];
        this.sourceCurrency = args[1];
        this.destinationCurrency = args[2];
        this.amount = args[3];
    }

    @Override
    public void execute() {
        this.ebank.exchangeMoney(this.email, this.sourceCurrency, this.destinationCurrency, Double.parseDouble(this.amount));
    }
}
