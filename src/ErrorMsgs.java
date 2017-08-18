import java.text.DecimalFormat;

/**
 * Created by raphael on 8/17/17.
 */
public class ErrorMsgs {

    DecimalFormat df = new DecimalFormat("#.##");


    public void insufficentFundsMessage(double amount, double currentBalance){
        System.out.println("Withdrawal Amount: $" + df.format(amount) + " exceeds current balance: $" +
                df.format(currentBalance));
    }

    public void rejectMessage(){
        System.out.println("Transaction Rejected");
    }

    public void withdrawalLimitMessage(){
        System.out.println("Withdrawal Limit Reached, unable to process request");
    }
}
