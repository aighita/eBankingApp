package org.poo.cb.Classes.Commands;

import org.poo.cb.Classes.eBank;
import org.poo.cb.Interfaces.CommandInterface;

public class AddFriendCommand implements CommandInterface {
    private final eBank ebank;
    private final String emailUser;
    private final String emailFriend;


    public AddFriendCommand(String[] args) {
        this.ebank = eBank.getInstance();
        this.emailUser = args[0];
        this.emailFriend = args[1];
    }

    @Override
    public void execute() {
        ebank.addFriends(emailUser, emailFriend);
    }
}
