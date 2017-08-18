import java.text.DecimalFormat;

/**
 * Created by raphael on 8/16/17.
 */
public class Savings extends Account {

    public static final String ACCOUNT_NAME = "Savings";

    private double interestRate;
    private int withdrawalCount;

    /**
     * Constructor for Personal Savings Accounts
     * @param balance
     * @param interestRate
     */
    public Savings(double balance, String accountName, int interestRate) {
        super(balance, accountName);
        this.interestRate = interestRate;
        withdrawalCount = 0;
    }

    /**
     * Constructor for Business Savings Accounts
     * @param accountBalance
     */
    public Savings(double accountBalance, String accountName) {
        super(accountBalance, accountName);
        withdrawalCount = 0;
    }

    @Override
    public double withdraw(double amount) {
        int currentWithdrawalCount = getWithdrawalCount();
        double currentBalance = getAccountBalance();

        if(currentWithdrawalCount >= WITHDRAWAL_LIMIT){
            errors.withdrawalLimitMessage();
            return getAccountBalance();
        }

        if(currentBalance < amount){
            errors.insufficentFundsMessage(amount, currentBalance);
            return getAccountBalance();
        }

        currentBalance -= amount;
        setAccountBalance(currentBalance);
        currentWithdrawalCount++;
        setWithdrawalCount(currentWithdrawalCount);
        displayBalance();

        return amount;
    }

    @Override
    public void displayBalance() {
        DecimalFormat df = new DecimalFormat("#.##");

        System.out.println("Savings balance: $" + df.format(this.getAccountBalance()));
        System.out.println("Withdrawal Count: " + getWithdrawalCount());
    }

    public int getWithdrawalCount() {
        return withdrawalCount;
    }

    public void setWithdrawalCount(int withdrawalCount) {
        this.withdrawalCount = withdrawalCount;
    }
}
