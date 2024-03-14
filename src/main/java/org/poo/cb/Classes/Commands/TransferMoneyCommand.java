package org.poo.cb.Classes.Commands;

import org.poo.cb.Classes.eBank;
import org.poo.cb.Interfaces.CommandInterface;

public class TransferMoneyCommand implements CommandInterface {
    private final eBank ebank;
    private final String email;
    private final String emailFriend;
    private final String currency;
    private final String amount;


    public TransferMoneyCommand(String[] args) {
        this.ebank = eBank.getInstance();
        this.email = args[0];
        this.emailFriend = args[1];
        this.currency = args[2];
        this.amount = args[3];
    }

    @Override
    public void execute() {
        ebank.transferMoney(this.email, this.emailFriend, this.currency, Double.parseDouble(this.amount));
    }
}
