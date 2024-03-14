package org.poo.cb.Classes.Commands;

import org.poo.cb.Classes.eBank;
import org.poo.cb.Interfaces.CommandInterface;

public class RecommendStocks implements CommandInterface {
    private final eBank ebank;


    public RecommendStocks() {
        this.ebank = eBank.getInstance();
    }

    @Override
    public void execute() {
        ebank.recommendStocks();
    }
}
