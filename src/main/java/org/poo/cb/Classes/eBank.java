package org.poo.cb.Classes;

import org.poo.cb.Classes.Commands.*;
import org.poo.cb.Exceptions.*;
import org.poo.cb.Interfaces.eBankInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;

public class eBank implements eBankInterface {
    private static eBank instance;
    private String STOCK_VALUES_CSV;
    private String EXCHANGE_RATE;
    private String COMMANDS;
    CommandExecutor opExec;
    HashMap<String, User> users;

    public HashMap<String, User> getUsers() {
        return users;
    }

    private eBank() {
        this.users = new HashMap<>();
    }

    public static eBank getInstance() {
        if (instance == null) {
            instance = new eBank();
        }
        return instance;
    }

    void buildPaths(String[] args) {
        String rPath = this.getResourcesPath();
        this.EXCHANGE_RATE = rPath + "/" + args[0];
        this.STOCK_VALUES_CSV = rPath + "/" + args[1];
        this.COMMANDS = rPath + "/" + args[2];
        this.opExec = new CommandExecutor();
    }

    String getResourcesPath() {
        File file = new File("src/main/resources/");
        if (file.exists()) return file.getPath();
        file = new File("../resources/");
        return file.getPath();
    }

    ArrayList<String> readFile(String file) {
        ArrayList<String> lines = new ArrayList<>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException ignored) {
        }
        return lines;
    }

    public void run(String[] args) {
        this.buildPaths(args);
        this.run(this.readFile(this.COMMANDS));
    }

    private void run(ArrayList<String> args) {
        for (String a : args) {
            String[] arg = a.split(" ");
            String command = arg[0] + " " + arg[1];
            System.arraycopy(arg, 2, arg, 0, arg.length - 2);

            switch (command) {
                case "CREATE USER":
                    this.opExec.addOperation(new CreateUserCommand(arg));
                    break;

                case "ADD FRIEND":
                    this.opExec.addOperation(new AddFriendCommand(arg));
                    break;

                case "ADD ACCOUNT":
                    this.opExec.addOperation(new AddAccountCommand(arg));
                    break;

                case "ADD MONEY":
                    this.opExec.addOperation(new AddMoneyCommand(arg));
                    break;

                case "EXCHANGE MONEY":
                    this.opExec.addOperation(new ExchangeMoneyCommand(arg));
                    break;

                case "TRANSFER MONEY":
                    this.opExec.addOperation(new TransferMoneyCommand(arg));
                    break;

                case "BUY STOCKS":
                    this.opExec.addOperation(new BuyStocksCommand(arg));
                    break;

                case "RECOMMEND STOCKS":
                    this.opExec.addOperation(new RecommendStocks());
                    break;

                case "LIST USER":
                    this.opExec.addOperation(new ListUserCommand(arg));
                    break;

                case "LIST PORTFOLIO":
                    this.opExec.addOperation(new ListPortfolioCommand(arg));
                    break;

                case "BUY PREMIUM":
                    this.opExec.addOperation(new BuyPremium(arg));
                    break;

                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
        this.opExec.executeOperations();
    }

    public void buyPremium(String email) {
        User user = getUserByEmail(email);
        try {
            if (user == null) throw new UserDoesNotExist(email);
            Account account = user.getAccountByCurrency("USD");
            if (account.withdraw(100)) {
                user.premium = true;
            } else throw new InsufficientAmountPremium();
        } catch (UserDoesNotExist | InsufficientAmountPremium e) {
            System.out.println(e.getMessage());
        }
    }

    public void recommendStocks() {
        ArrayList<String> lines = readFile(this.STOCK_VALUES_CSV);
        lines.remove(0);

        StringBuilder str = new StringBuilder("{\"stocksToBuy\":[");
        for (String line : lines) {
            String[] values = line.split(",");
            double sum5 = 0;
            double sum10 = 0;
            for (int i = 1; i < values.length; i++) {
                if (i >= 6) sum5 += Double.parseDouble(values[i]);
                sum10 += Double.parseDouble(values[i]);
            }
            if ((sum5 / 5) > (sum10 / 10)) {
                str.append("\"").append(values[0]).append("\"").append(",");
            }
        }
        str.deleteCharAt(str.length() - 1);
        str.append("]}");
        System.out.println(str);
    }

    public double getStockValue(String company) {
        ArrayList<String> lines = readFile(this.STOCK_VALUES_CSV);
        String line = "";
        for (String l : lines) {
            if (l.split(",")[0].equals(company)) {
                line = l;
            }
        }
        return Double.parseDouble(line.split(",")[line.split(",").length - 1]);
    }

    public void buyStocks(String email, String company, int noOfStocks) {
        User user = this.getUserByEmail(email);
        Account account = user.getAccountByCurrency("USD");
        double value = getStockValue(company);

        try {
            if (account.withdraw(value * noOfStocks)) {
                user.portfolio.stocks.add(new Stock(company, noOfStocks));
            } else {
                throw new InsufficientAmountBuyStocks();
            }
        } catch (InsufficientAmountBuyStocks e) {
            System.out.println(e.getMessage());
        }
    }

    public void transferMoney(String email, String friendEmail, String currency, double amount) {
        User user = this.getUserByEmail(email);
        User friend = this.getUserByEmail(friendEmail);
        Account userAcc = user.getAccountByCurrency(currency);
        Account friendAcc = friend.getAccountByCurrency(currency);

        try {
            if (user.alreadyFriend(friendEmail)) {
                throw new TransferNotAllowed(friendEmail);
            } else {
                if (userAcc.withdraw(amount)) {
                    friendAcc.deposit(amount);
                } else {
                    throw new InsufficientAmountTransfer(currency);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    double getExchangeRate(String sourceCurrency, String destinationCurrency) {
        ArrayList<String> lines = this.readFile(this.EXCHANGE_RATE);
        double rate = 1.0;

        String[] base = lines.get(0).split(",");
        int idx = 0;
        for (int i = 1; i < base.length; i++) {
            if (base[i].equals(sourceCurrency)) {
                idx = i;
                break;
            }
        }

        for (String line : lines) {
            if (line.split(",")[0].equals(destinationCurrency)) {
                rate = Double.parseDouble(line.split(",")[idx]);
            }
        }

        return rate;
    }

    public void exchangeMoney(String email, String sourceCurrency, String destinationCurrency, double amount) {
        User user = this.getUserByEmail(email);
        Account srcAcc = user.getAccountByCurrency(sourceCurrency);
        Account destAcc = user.getAccountByCurrency(destinationCurrency);
        double rate = getExchangeRate(sourceCurrency, destinationCurrency);

        double comision = 0;
        try {
            if ((amount * rate > srcAcc.balance / 2) && !user.premium) comision = 0.01;
            if (srcAcc.withdraw((amount * rate) + (amount * rate) * comision)) {
                destAcc.deposit(amount);
            } else {
                throw new InsufficientAmountExchange(sourceCurrency);
            }
        } catch (InsufficientAmountExchange e) {
            System.out.println(e.getMessage());
        }
    }

    User getUserByEmail(String email) {
        return (this.users != null) ? this.users.get(email) : null;
    }

    private boolean checkUserExistence(String email) {
        return this.users != null && this.users.containsKey(email);
    }

    public void createUser(String email, String firstName, String lastName, String address) {
        try {
            if (checkUserExistence(email)) {
                throw new UserAlreadyExists(email);
            } else {
                if (this.users == null) this.users = new HashMap<>();
                this.users.put(email, new User(email, firstName, lastName, address));
            }
        } catch (UserAlreadyExists e) {
            System.out.println(e.getMessage());
        }
    }

    public void addFriends(String emailUser, String emailFriend) {
        try {
            User user = getUserByEmail(emailUser);
            User friend = getUserByEmail(emailFriend);
            if (user == null) {
                throw new UserDoesNotExist(emailUser);
            }
            if (friend == null) {
                throw new UserDoesNotExist(emailFriend);
            }
            if (user.alreadyFriend(emailFriend)) {
                throw new UserAlreadyFriend(emailFriend);
            }
            user.addFriend(emailFriend);
            friend.addFriend(emailUser);
        } catch (UserDoesNotExist | UserAlreadyFriend e) {
            System.out.println(e.getMessage());
        }
    }

    public void createAccount(String email, String currency) {
        User user = getUserByEmail(email);
        Account account = new Account(currency);
        try {
            if (user.checkAccountExistence(account)) {
                throw new AccountAlreadyExists(currency);
            }
            user.addAccount(account);
        } catch (AccountAlreadyExists e) {
            System.out.println(e.getMessage());
        }
    }

    public void addMoney(String email, String currency, double amount) {
        User user = getUserByEmail(email);
        user.deposit(currency, amount);
    }

    public void listPortfolio(String email) {
        User user = getUserByEmail(email);
        System.out.println(user.portfolio.toString());
    }

    public void listUser(String email) {
        try {
            User u = getUserByEmail(email);
            if (u == null) throw new UserDoesNotExist(email);
            else System.out.println(u.toString());
        } catch (UserDoesNotExist e) {
            System.out.println(e.getMessage());
        }
    }
}