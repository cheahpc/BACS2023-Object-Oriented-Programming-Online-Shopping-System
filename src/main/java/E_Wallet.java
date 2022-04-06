
public class E_Wallet extends Payment {

    // data properties
    private String walletName;
    private String walletID;

    // no arg contsructor
    public E_Wallet() {

    }

    // arg constructor
    public E_Wallet(double totalFees, double discountRate, String status, String transactionType, double serviceCharges,
            String walletName, String walletID) {
        super(totalFees, discountRate, status, transactionType, serviceCharges); // superclass portion
        this.walletName = walletName;
        this.walletID = walletID;
    }

    // setter
    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public void setWalletID(String walletID) {
        this.walletID = walletID;
    }

    // getter
    public String getWalletName() {
        return walletName;
    }

    public String getWalletID() {
        return walletID;
    }

    // method

    public void determineWallet(int option) {
        switch (option) {
            case 1:
                setWalletName("Touch 'N Go");
                break;

            case 2:
                setWalletName("GrabPay");
                break;

            case 3:
                setWalletName("Boost");
                break;

            case 4:
                setWalletName("WeChat Pay");
                break;

            case 5:
                setWalletName("iPay88");
                break;

            case 6:
                setWalletName("vcash");
                break;
        }
    }

    public double calTotalFees(double totalAmount) {
        return (super.calTotalFees(totalAmount) + serviceCharges);
    }

    public String toString() {
        return super.toString() + String.format(">> E-Wallet Apps Name  : %s                                       \n" +
                ">> E-Wallet ID         : %s                                       \n" +
                ">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n", walletName, walletID);
    }
}
