
public class Bank extends Payment {

    // data properties
    private String bankName, accountNo, expireDate;
    private int cvCode;
    private static double servicesCharges = 0.50;

    // no arg contsructor
    public Bank() {

    }

    // arg constructor
    public Bank(double totalFees, double discountFees, int codeTAC, String status, String transactionType,
            String bankName, String accountNo, String expireDate, int cvCode) {
        super(totalFees, discountFees, status, transactionType); // superclass portion
        this.accountNo = accountNo;
        this.bankName = bankName;
        this.expireDate = expireDate;
        this.cvCode = cvCode;
    }

    // setter
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public void setCvCode(int cvCode) {
        this.cvCode = cvCode;
    }

    // getter
    public String getAccountNo() {
        return accountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public int getCvCode() {
        return cvCode;
    }

    // method
    public String output() {
        return String.format("------------------------------------------------------------------\n" +
                "                         Bank Transaction                         \n" +
                "------------------------------------------------------------------\n" +
                ">> Select Your Bank Name To Make The Payment:                     \n" +
                ">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                ">>    1.      Hong Leong Bank                                     \n" +
                ">>    2.      RHB Bank                                            \n" +
                ">>    3.      MAY Bank                                            \n" +
                ">>    4.      Public Bank                                         \n" +
                ">>    5.      CIMB Bank                                           \n" +
                ">>    6.      AM Bank                                             \n" +
                ">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    public boolean determineBank(int option) {
        switch (option) {
            case 1:
                // Hong Leong Bank
                setBankName("Hong Leong Bank");
                return true;

            case 2:
                // RHB Bank
                setBankName("RHB Bank");
                return true;

            case 3:
                // MAY Bank
                setBankName("MAY Bank");
                return true;
            case 4:
                // Public Bank
                setBankName("Public Bank");
                return true;
            case 5:
                // CIMB Bank
                setBankName("CIMB Bank");
                return true;

            case 6:
                // AM Bank
                setBankName("AM Bank");
                return true;

            default:
                System.out.println(">> ERROR! Unable to process input.");
                System.out.println(">> Please Try again.!!!");
                return false;
        }
    }

    public double calTotalFees() {
        return (super.calTotalFees() + servicesCharges);
    }

    public String Receipt() {
        return super.receipt() + String.format(">> Bank Name           : %s                                       \n" +
                ">> Services Charges    : RM%.2f                                   \n" +
                ">> Total Fees          : RM%.2f                                   \n" +
                ">> From Account Number : %s                                       \n" +
                ">> Recipient Reference : %s                                       \n" +
                ">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n", bankName, servicesCharges,
                calTotalFees(), accountNo, accountNo); // , customerName);
    }
}
