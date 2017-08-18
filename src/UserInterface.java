import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by raphael on 8/15/17.
 */
public class UserInterface {

    public static void main(String[] args){
        UserInterface ui = new UserInterface();
        Account checking = new Checking(500, "Checking");
        Account savings = new Savings(500, "Savings", 5);
        Account business = new Business(500, "Business Account",
                new Checking(0, "Business Checking"),
                new Savings(0, "Business Savings"),
                5, 5);

        int result = ui.uiMainScreen();
        int resResult = 0;
        switch (result){
            case 1:
                resResult = ui.transactionMenu(checking);
                break;
            case 2:
                resResult = ui.transactionMenu(savings);
                break;
            case 3:
                resResult = ui.transactionMenu(business);
                break;
            default:
                System.out.println("Invalid Selection, Logging off....");
        }

        System.out.println(resResult);

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
