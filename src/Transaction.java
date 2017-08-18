import java.util.Date;

/**
 * Created by raphael on 8/16/17.
 */
public class Transaction {

    private String tranactionType;
    private String accountType;
    private double tranactionAmount;
    private double previousBalance;
    private double newBalance;
    private Date timeStamp;
    private double balanceTo;
    private double balanceFrom;

    public Transaction(String tranactionType, String accountType, double tranactionAmount, double previousBalance,
                       double newBalance, Date timeStamp) {
        this.tranactionType = tranactionType;
        this.accountType = accountType;
        this.tranactionAmount = tranactionAmount;
        this.previousBalance = previousBalance;
        this.newBalance = newBalance;
        this.timeStamp = timeStamp;
    }

    public Transaction(String transactionType, String accountTypeTo, String accountTypeFrom, double transactionAmount,
                       double balanceTo, double balanceFrom, Date timeStamp){
        this.tranactionType = transactionType;
        this.accountType = accountTypeFrom + " to " + accountTypeTo;
        this.tranactionAmount = transactionAmount;
        this.balanceTo = balanceTo;
        this.balanceFrom = balanceFrom;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "tranactionType='" + tranactionType + '\'' +
                ", accountType='" + accountType + '\'' +
                ", tranactionAmount=" + tranactionAmount +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
