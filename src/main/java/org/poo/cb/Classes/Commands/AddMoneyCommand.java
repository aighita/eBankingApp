package org.poo.cb.Classes.Commands;

import org.poo.cb.Classes.eBank;
import org.poo.cb.Interfaces.CommandInterface;

public class AddMoneyCommand implements CommandInterface {
    private final eBank ebank;
    private final String emailUser;
    private final String currency;
    private final double amount;


    public AddMoneyCommand(String[] args) {
        this.ebank = eBank.getInstance();
        this.emailUser = args[0];
        this.currency = args[1];
        this.amount = Double.parseDouble(args[2]);
    }

    @Override
    public void execute() {
        ebank.addMoney(emailUser, currency, amount);
    }
}
