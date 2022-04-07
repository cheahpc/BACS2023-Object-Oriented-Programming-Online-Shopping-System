
public class Bank extends Payment {

    // data properties
    private String bankName, accountNo, expireDate;
    private int cvCode;

    // no arg contsructor
    public Bank() {

    }

    // arg constructor
    public Bank(double totalFees, double discountRate, String status, String transactionType, double serviceCharges,
            String bankName, String accountNo, String expireDate, int cvCode) {
        super(totalFees, discountRate, status, transactionType, serviceCharges); // superclass portion
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
    public void interfaceOutput() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("                        Bank Transaction                          ");
        System.out.println("------------------------------------------------------------------");
        System.out.println(">> Select Your Bank Name To Make The Payment:                     ");
        System.out.println(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(">>    1.      Hong Leong Bank                                     ");
        System.out.println(">>    2.      RHB Bank                                            ");
        System.out.println(">>    3.      MAY Bank                                            ");
        System.out.println(">>    4.      Public Bank                                         ");
        System.out.println(">>    5.      CIMB Bank                                           ");
        System.out.println(">>    6.      AM Bank                                             ");
        System.out.println(">>                                                                ");
        System.out.println(">>    0.      Exit                                                ");
        System.out.println(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void transactionMethod(int option) {
        switch (option) {
            case 1:
                // Hong Leong Bank
                setBankName("Hong Leong Bank");
                break;

            case 2:
                // RHB Bank
                setBankName("RHB Bank");
                break;

            case 3:
                // MAY Bank
                setBankName("MAY Bank");
                break;

            case 4:
                // Public Bank
                setBankName("Public Bank");
                break;

            case 5:
                // CIMB Bank
                setBankName("CIMB Bank");
                break;

            case 6:
                // AM Bank
                setBankName("AM Bank");
                break;
        }
    }

    public String toString() {
        return super.toString() + String.format(">> Bank Name           : %s       \n" +
                ">> From Account Number : %s                                       \n" +
                ">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n", bankName, accountNo);
    }
}
