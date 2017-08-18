import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by raphael on 8/15/17.
 */
abstract public class Account {

    //account constants
    public static final double INSUFFICENT_FUNDS_FEE = 34.00;
    public static final int WITHDRAWAL_LIMIT = 6;

    //account variables
    private double accountBalance;
    private List<Transaction> transactionList;
    private String accountName;
    public ErrorMsgs errors = new ErrorMsgs();


    //abstract methods
    public abstract double withdraw(double amount);
    public abstract void displayBalance();

    //constructor
    public Account(double accountBalance, String accountName) {
        this.accountBalance = accountBalance;
        this.accountName = accountName;
        transactionList = new ArrayList<>();
    }


    public void transferFunds(Account tranferredFrom, Account transferredTo, double amount){
        //withdrawal from and deposit to.
        double transitAmount = tranferredFrom.withdraw(amount);
        transferredTo.deposit(transitAmount);
        List<Transaction> transferTransaction = getTransactionList();
        transferTransaction.add(new Transaction("Transfer", transferredTo.getAccountName(),
                tranferredFrom.getAccountName(), amount, transferredTo.getAccountBalance(),
                tranferredFrom.getAccountBalance(), new Date()));
    }

    public double deposit(double amount){
        double currentBalance = this.getAccountBalance();
        double prevBal = currentBalance;
        List<Transaction> deposits = getTransactionList();

        currentBalance += amount;
        setAccountBalance(currentBalance);
        displayBalance();

        deposits.add(new Transaction("Deposit", getAccountName(),  amount, prevBal, currentBalance, new Date()));
        setTransactionList(deposits);
        return getAccountBalance();
    }


    //----Getters and Setters-----------------------------
    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public String getAccountName() {
        return accountName;
    }
}
