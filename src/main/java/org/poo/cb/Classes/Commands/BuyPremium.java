package org.poo.cb.Classes.Commands;

import org.poo.cb.Classes.eBank;
import org.poo.cb.Interfaces.CommandInterface;

public class BuyPremium implements CommandInterface {
    private final eBank ebank;
    private final String email;

    public BuyPremium(String[] args) {
        this.ebank = eBank.getInstance();
        this.email = args[0];
    }

    @Override
    public void execute() {
        ebank.buyPremium(this.email);
    }
}
