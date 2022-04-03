
public class E_Wallet extends Payment {

    // data properties
    private String walletID;
    private static double transactionRate = 0.30; // 3%

    // no arg contsructor
    public E_Wallet() {

    }

    // arg constructor
    public E_Wallet(double totalFees, double amount, String status, String transactionType, String referenceID,
            String wallectID) {
        super(totalFees, amount, status, transactionType, referenceID); // superclass portion
        this.walletID = wallectID;
    }

    // setter
    public void setWalletID(String walletID) {
        this.walletID = walletID;
    }

    // getter
    public String getWalletID() {
        return walletID;
    }

    public double totalFees() {
        return (totalFees * transactionRate);
    }

    // method
    public String Receipt() {
        return super.toString() + String.format("");
    }
}
