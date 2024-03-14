package org.poo.cb.Classes.Commands;

import org.poo.cb.Classes.eBank;
import org.poo.cb.Interfaces.CommandInterface;

public class ListPortfolioCommand implements CommandInterface {
    private final eBank ebank;
    private final String email;


    public ListPortfolioCommand(String[] args) {
        this.ebank = eBank.getInstance();
        this.email = args[0];
    }

    @Override
    public void execute() {
        ebank.listPortfolio(email);
    }
}
