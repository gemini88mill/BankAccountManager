import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by raphael on 8/15/17.
 */
public class Checking extends Account {


    public Checking(double accountBalance, String accountName) {
        super(accountBalance, accountName);
    }

    @Override
    public double withdraw(double amount) {
        double currentBalance = this.getAccountBalance();
        double prevBal = currentBalance;
        List<Transaction> withdrawal = getTransactionList();

        if(currentBalance < amount){
            errors.insufficentFundsMessage(amount, currentBalance);
            return this.getAccountBalance();
        }

        withdrawal.add(new Transaction("Withdrawal", "Checking", amount, prevBal,
                currentBalance, new Date()));

        currentBalance -= amount;
        this.setAccountBalance(currentBalance);
        setTransactionList(withdrawal);
        displayBalance();


        return amount;
    }


    @Override
    public void displayBalance() {
        DecimalFormat df = new DecimalFormat("#.##");

        System.out.println("Checking balance: $" + df.format(this.getAccountBalance()));
    }


}
