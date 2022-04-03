
public class Bank extends Payment {

    // data properties
    private String accountNo;
    private static double transactionRate = 0.50; // 5%

    // no arg contsructor
    public Bank() {

    }

    // arg constructor
    public Bank(double totalFees, double amount, String status, String transactionType, String referenceID,
            String accountNo) {
        super(totalFees, amount, status, transactionType, referenceID); // superclass portion
        this.accountNo = accountNo;
    }

    // setter
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    // getter
    public String getAccountNo() {
        return accountNo;
    }

    // method
    public double totalFees() {
        return (totalFees * transactionRate);
    }

    public String Receipt() {
        return super.toString() + String.format("");
    }
}
