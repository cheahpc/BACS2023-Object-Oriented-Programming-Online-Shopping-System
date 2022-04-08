
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
    public void interfaceOutput() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("                       E-Wallet Transaction                       ");
        System.out.println("------------------------------------------------------------------");
        System.out.println(">> Select Your E-Wallet Name To Make The Payment:                 ");
        System.out.println(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(">>    1.      Touch 'N Go                                         ");
        System.out.println(">>    2.      GrabPay                                             ");
        System.out.println(">>    3.      Boost                                               ");
        System.out.println(">>    4.      WeChat Pay                                          ");
        System.out.println(">>    5.      iPay88                                              ");
        System.out.println(">>    6.      vcash                                               ");
        System.out.println(">>                                                                ");
        System.out.println(">>    0.      Exit                                                ");
        System.out.println(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void transactionMethod(int option) {
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

    public String toString() {
        return super.toString() + String.format(">> E-Wallet Apps Name          : %s       \n" +
                ">> E-Wallet ID                 : %s                                       \n" +
                ">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n", walletName, walletID);
    }
}