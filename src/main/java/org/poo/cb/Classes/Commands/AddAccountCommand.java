package org.poo.cb.Classes.Commands;

import org.poo.cb.Classes.eBank;
import org.poo.cb.Interfaces.CommandInterface;

public class AddAccountCommand implements CommandInterface {
    private final eBank ebank;
    private final String email;
    private final String currency;

    public AddAccountCommand(String[] args) {
        this.ebank = eBank.getInstance();
        this.email = args[0];
        this.currency = args[1];
    }

    @Override
    public void execute() {
        ebank.createAccount(email, currency);
    }
}
