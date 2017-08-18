/**
 * Created by raphael on 8/16/17.
 */
public class Business extends Account {

    public static final String ACCOUNT_NAME = "Business";

    private Account businessChecking;
    private Account businessSavings;
    private double interestCheckingRate;
    private double interestSavingsRate;

    public Business(double accountBalance, String accountName, Account businessChecking, Account businessSavings,
                    double interestCheckingRate, double interestSavingsRate) {
        super(accountBalance, accountName);
        this.businessChecking = businessChecking;
        this.businessSavings = businessSavings;
        this.interestCheckingRate = interestCheckingRate;
        this.interestSavingsRate = interestSavingsRate;
    }

    @Override
    public double withdraw(double amount) {

        return 0;
    }

    @Override
    public void displayBalance() {

    }
}
