package org.poo.cb.Classes;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import java.text.DecimalFormat;

public class Portfolio {
    HashSet<Account> accounts;
    HashSet<Stock> stocks;

    public Portfolio() {
        this.accounts = new HashSet<>();
        this.stocks = new HashSet<>();
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        StringBuilder str = new StringBuilder("{\"stocks\":[");
        List<String> stocksToString = new ArrayList<>();
        for (Stock stock : this.stocks) {
            String stockToString = "{\"stockName\":\""
                    + stock.company + "\",\"amount\":"
                    + stock.noOfStocks + "}";
            stocksToString.add(stockToString);
        }
        for (int i = 0; i < stocksToString.toArray().length; i++) {
            str.append(stocksToString.get(i));
            if (i != stocksToString.toArray().length - 1) str.append(",");
        }
//        for (int i = stocksToString.size() - 1; i >= 0; i--) {
//            str.append(stocksToString.get(i));
//            if (i != 0) str.append(",");
//        }
        str.append("],\"accounts\":[");
        List<String> accountsToString = new ArrayList<>();
        for (Account account : this.accounts) {
            String accountToString = "{\"currencyName\":\""
                    + account.currency + "\",\"amount\":\""
                    + df.format(account.balance) + "\"}";
            accountsToString.add(accountToString);
        }
        for (int i = accountsToString.size() - 1; i >= 0; i--) {
            str.append(accountsToString.get(i));
            if (i != 0) str.append(",");
        }
        str.append("]}");
        return str.toString();
    }
}
