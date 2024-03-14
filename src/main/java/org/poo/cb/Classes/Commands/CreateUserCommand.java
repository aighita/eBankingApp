package org.poo.cb.Classes.Commands;

import org.poo.cb.Classes.eBank;
import org.poo.cb.Interfaces.CommandInterface;

import java.util.Arrays;

public class CreateUserCommand implements CommandInterface {
    private final eBank ebank;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String address;

    public CreateUserCommand(String[] args) {

        this.ebank = eBank.getInstance();
        this.email = args[0];
        this.firstName = args[1];
        this.lastName = args[2];
        StringBuilder address1 = new StringBuilder();
        for (int i = 3; i < args.length - 2; i++) {
            address1.append(args[i]);
            if (i < args.length - 3) {
                address1.append(" ");
            }
        }
        this.address = address1.toString();
    }

    @Override
    public void execute() {
        ebank.createUser(email, address, firstName, lastName);
    }
}
