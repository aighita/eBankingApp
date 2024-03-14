package org.poo.cb.Classes.Commands;

import org.poo.cb.Classes.eBank;
import org.poo.cb.Interfaces.CommandInterface;

public class BuyStocksCommand implements CommandInterface {
    private final eBank ebank;
    private final String email;
    private final String company;
    private final String noOfStocks;


    public BuyStocksCommand(String[] args) {
        this.ebank = eBank.getInstance();
        this.email = args[0];
        this.company = args[1];
        this.noOfStocks = args[2];
    }

    @Override
    public void execute() {
        ebank.buyStocks(this.email, this.company, Integer.parseInt(this.noOfStocks));
    }
}
