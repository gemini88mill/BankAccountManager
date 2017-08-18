import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by raphael on 8/15/17.
 *
 * Public class User Interface -
 * runs the user interface. This user interface reads in a text file from the main method and based off of the commands
 * in the text file, the program initiates program functions to resemble an ATM account machine. manual methods are left
 * in to create more accounts manually.
 *
 */
public class UserInterface {

    //globals
    private static final int INTEREST_RATE = 5;
    public List<Account> bankAccounts = new ArrayList<>();

    /**
     * main method, reads in a text file from the command line.
     *
     * @param args cl arguments
     */
    public static void main(String[] args){

        /*todo: create text doc to save transactions and to save account names and lists. */
        //args will contain the text file specified or the account name that is to be accessed

        String fileName = args[0];

        //System.out.println(args[0]);
        FileReader fr;
        BufferedReader br;
        List<Account> bankAccounts = new ArrayList<>();


        readFile(args[0], bankAccounts);


    }

    /**
     * readfile method - method allows the reading of a file in order to parse through the file. then sends the lines
     * of the files to the read function method.
     *
     * @param arg          filename
     * @param bankAccounts List to store accounts
     */
    private static void readFile(String arg, List<Account> bankAccounts) {
        try (BufferedReader br = new BufferedReader(new FileReader(arg))) {

            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                readFunction(currentLine, bankAccounts);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * readFunction - method that parses the file lines and creates accounts, deposits, withdrawals, and transfers for
     * the banking application.
     * @param line String
     * @param bankAccounts List
     */
    private static void readFunction(String line, List<Account> bankAccounts) {
        String[] token = line.split("\\s+");
        int result;

        switch (token[0]) {
            case "create":
                bankAccounts.add(createFunction(token));
                System.out.println("Account created: " + bankAccounts.get(bankAccounts.size() - 1).toString());
                break;
            case "deposit":
                result = initDeposit(token[1], Double.parseDouble(token[2]), bankAccounts);
                if (result == 0)
                    System.out.println("Account not found");
                break;
            case "withdraw":
                result = initWithdrawal(token[1], Double.parseDouble(token[3]), bankAccounts);
                if (result == 0)
                    System.out.println("Account not found");
                break;
            case "transfer":
                result = initTransfer(token[1], token[2], Double.parseDouble(token[3]), bankAccounts);
                if (result == 0)
                    System.out.println("transfer unsuccessful");
                break;
            default:
                break;
        }
    }

    /**
     * initTransfer - connects the file line read to the Account program allowing to create a transfer on the account.
     * program accepts 4 arguments, 2 Account Objects, one double and one List of accounts. The methos will search
     * through the available list of accounts, looking for an account to receive the transfer and an account to give
     * the transfer. If found, the method will set up a Transaction and transfer the funds from one account to another.
     * returns 1 on success and 0 on failure.
     * @param accountTo String
     * @param accountFrom String
     * @param amount double
     * @param bankAccounts List
     * @return int
     */
    private static int initTransfer(String accountTo, String accountFrom, double amount, List<Account> bankAccounts) {
        Account accountReceive = null;
        Account accountSend = null;

        System.out.println("Looking for accounts: " + accountTo + ", " + accountFrom);
        for (Account bankAccount : bankAccounts) {
            if (bankAccount.getAccountName().equals(accountTo)) {
                System.out.println("found account" + accountTo);
                accountReceive = bankAccount;
            }
            if (bankAccount.getAccountName().equals(accountFrom)) {
                System.out.println("found account" + accountFrom);
                accountSend = bankAccount;
            }
        }

        if (accountSend != null && accountReceive != null) {
            Transaction transaction = accountSend.transferFunds(accountSend, accountReceive, amount);
            System.out.println(transaction.toString());
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * initWithdrawal() - initiates a withdrawal from the Account class, if successful return 1 if not 0
     *
     * @param accountName String
     * @param amount double
     * @param bankAccounts List<Account>
     * @return int
     */
    private static int initWithdrawal(String accountName, double amount, List<Account> bankAccounts) {
        System.out.println("Looking for account: " + accountName);
        for (Account bankAccount : bankAccounts) {
            if (bankAccount.getAccountName().equals(accountName)) {
                System.out.println("Found account...");
                double prevBalance = bankAccount.getAccountBalance();
                bankAccount.withdraw(amount);
                double currentBalance = bankAccount.getAccountBalance();
                System.out.println("Deposit successful, Previous Balance:" + prevBalance);
                System.out.println("New Balance: " + currentBalance);
                return 1;
            }
        }
        return 0;
    }

    /**
     * initDeposit() - initiates a deposit on the account given by a string. returns 1 on success if not 0
     *
     * @param accountName String
     * @param amount double
     * @param bankAccounts List<Account>
     * @return int
     */
    private static int initDeposit(String accountName, double amount, List<Account> bankAccounts) {
        System.out.println("Looking for account: " + accountName);
        for (Account bankAccount : bankAccounts) {
            System.out.println(bankAccount.getAccountName());
            System.out.println("here");
            if (bankAccount.getAccountName().equals(accountName)) {
                System.out.println("Found account...");
                double previousBalance = bankAccount.getAccountBalance();
                bankAccount.deposit(amount);
                double currentBalance = bankAccount.getAccountBalance();
                System.out.println("Deposit successful, Previous Balance:" + previousBalance);
                System.out.println("New Balance: " + currentBalance);
                return 1;
            }
        }
        return 0;
    }

    /**
     * createFunction() - creates the account based on the perams from the textfile. returns Account, if unsuccessful,
     * a null account will be created and posted to the error log.
     * @param token String array
     * @return Account
     */
    private static Account createFunction(String[] token) {
        String accountName = token[1];
        double amount = Double.parseDouble(token[3]);

        Account createAccount;

        switch (token[2]) {
            case "checking":
                createAccount = new Checking(amount, accountName);
                break;
            case "savings":
                createAccount = new Savings(amount, accountName, INTEREST_RATE);
                break;
            default:
                System.err.println("Incorrect format, please review argument structure");
                createAccount = null;
                break;
        }

        return createAccount;
    }

    private int uiMainScreen(){
        System.out.println("Welcome to BAM ATM Services");
        System.out.println("Please select an account");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        System.out.println("3. Business");

        return getUserInput();
    }

    private int getUserInput(){

        Scanner scan = new Scanner(new InputStreamReader(System.in));
        String line = scan.nextLine();


        return Integer.parseInt(line);
    }

    private int transactionMenu(Account account){
        System.out.println("What would you like to do?");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Transfer");

        int res = getUserInput();

        System.out.println("How much?");

        int amount = getUserInput();

        switch (res){
            case 1:
                account.withdraw(amount);
                break;
            case 2:
                account.deposit(amount);
                break;
            default:
                break;

        }

        return 0;
    }


}
