package org.poo.cb;

import org.poo.cb.Classes.eBank;

public class Main {
    public static void main(String[] args) {
        if(args == null) {
            System.out.println("Running Main");
            return;
        }

        if (eBank.getInstance().getUsers() != null)
            eBank.getInstance().getUsers().clear();

        eBank.getInstance().run(args);
    }
}