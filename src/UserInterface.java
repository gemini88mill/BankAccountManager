import java.io.*;
import java.util.Scanner;

/**
 * Created by raphael on 8/15/17.
 *
 * Public class User Interface -
 * runs the user interface
 *
 */
public class UserInterface {

    private static final int INTEREST_RATE = 5;

    public static void main(String[] args){

        /*todo: create text doc to save transactions and to save account names and lists. */
        //args will contain the text file specified or the account name that is to be accessed

        String fileName = args[0];

        System.out.println(args[0]);
        FileReader fr;
        BufferedReader br;

        readFile(args[0]);


    }

    private static void readFile(String arg) {
        try (BufferedReader br = new BufferedReader(new FileReader(arg))) {

            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                readFunction(currentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFunction(String line) {
        String[] token = line.split("\\s+");
        switch (token[0]) {
            case "create":
                createFunction(token);
                break;
            case "deposit":
                break;
            case "withdraw":
                break;
            case "transfer":
                break;
            default:
                break;
        }
    }

    private static Account createFunction(String[] token) {
        String accountName = token[1];
        double amount = Double.parseDouble(token[3]);

        Account createAccount;

        if (token[2].equals("checking")) {
            createAccount = new Checking(amount, accountName);
        } else if (token[2].equals("savings")) {
            createAccount = new Savings(amount, accountName, INTEREST_RATE);
        } else {
            System.err.println("Incorrect format, please review argument structure");
            createAccount = null;
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
