
public class E_Wallet extends Payment {

    // data properties
    private String walletName;
    private static double servicesCharges = 0.30;

    // no arg contsructor
    public E_Wallet() {

    }

    // arg constructor
    public E_Wallet(double totalFees, double discountFees, int codeTAC, String status, String transactionType,String walletName) {
        super(totalFees, discountFees, status, transactionType); // superclass portion
        this.walletName = walletName;
    }

    // setter
    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    // getter
    public String getWalletName() {
        return walletName;
    }

    // method
    public String output() {
        return String.format("------------------------------------------------------------------\n" +
                "                       E-Wallet Transaction                       \n" +
                "------------------------------------------------------------------\n" +
                ">> Select Your E-Wallet Name To Make The Payment:                 \n" +
                ">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                ">>    1.      Touch 'N Go                                         \n" +
                ">>    2.      GrabPay                                             \n" +
                ">>    3.      Boost                                               \n" +
                ">>    4.      WeChat Pay                                          \n" +
                ">>    5.      iPay88                                              \n" +
                ">>    6.      vcash                                               \n" +
                ">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    public boolean determineWallet(int option) {
        switch (option) {
            case 1:
                setWalletName("Touch 'N Go");
                return true;

            case 2:
                setWalletName("GrabPay");
                return true;

            case 3:
                setWalletName("Boost");
                return true;

            case 4:
                setWalletName("WeChat Pay");
                return true;

            case 5:
                setWalletName("iPay88");
                return true;

            case 6:
                setWalletName("vcash");
                return true;

            default:
                System.out.println(">> ERROR! Unable to process input.");
                System.out.println(">> Please Try again.!!!");
                return false;
        }
    }

    public String QRcode() {
        return String.format("------------------------------------------------------------------\n" +
                "                       E-Wallet Transaction                       \n" +
                "------------------------------------------------------------------\n" +
                ">> Scan The QR Code Given To Make The Payment   :                 \n" +
                ">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                ">>                                                                \n" +
                ">>         +++++                                 +++++            \n" +
                ">>         +                                         +            \n" +
                ">>         +    ++++++++++     +++++   ++++++++++    +            \n" +
                ">>              + ++++++ +   ++   ++   + ++++++ +                 \n" +
                ">>              + ++++++ +     ++ ++   + ++++++ +                 \n" +
                ">>              + ++++++ +   ++++  +   + ++++++ +                 \n" +
                ">>              ++++++++++   ++        ++++++++++                 \n" +
                ">>                           +++++++                              \n" +
                ">>               ++  ++++   +++   ++++    +++++                   \n" +
                ">>              ++  ++  ++  +++++   ++++  +  ++++                 \n" +
                ">>              +++++++++ +++  ++++++  + + ++++ +                 \n" +
                ">>                        ++ +++++ ++++++  + + ++                 \n" +
                ">>              ++++++++++  +++   +++    + + +++                  \n" +
                ">>              + ++++++ +   +  ++++++    + + +++                 \n" +
                ">>              + ++++++ +  +++  ++  +++    + + +                 \n" +
                ">>              + ++++++ +  +  ++++++    + + +++                  \n" +
                ">>         +    ++++++++++  +++ ++++ + + ++ ++++     +            \n" +
                ">>         +                                         +            \n" +
                ">>         +++++                                 +++++            \n" +
                ">>                                                                \n" +
                ">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    public double calTotalFees() {
        return (super.calTotalFees() + servicesCharges);
    }

    public String receipt() {
        return super.receipt() + String.format(">> Services Charges    : RM %.2f                                  \n" +
                                               ">> Total Fees          : RM %.2f                                  \n" +
                                               ">> E-Wallet Apps Name  : %s                                       \n" +
                                               ">> Recipient Reference : %s                                       \n" +
                                               ">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                                                , calTotalFees(), servicesCharges, walletName,walletName); // , customerName);
    }
}
