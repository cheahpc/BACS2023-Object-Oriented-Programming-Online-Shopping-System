
import java.util.Date;

public class Payment {

    // data properties
    int max = 999999, min = 100000;
    protected int referenceNo = (int) (Math.random() * (max - min + 1) + min);
    protected int codeTAC = (int) (Math.random() * (max - min + 1) + min);
    protected double totalFees = 1000.00, discountFees;
    protected Date dateCreated;
    protected String status, transactionType;

    // no arg constructor
    public Payment() {

    }

    // arg constructor
    public Payment(double totalFees, double discountFees, String status, String transactionType) {
        this.totalFees = totalFees;
        this.discountFees = discountFees;
        this.status = status;
        this.transactionType = transactionType;
        dateCreated = new java.util.Date();// system generated user can't modify
    }

    // setter
    public void setTotalFees(double totalFees) {
        this.totalFees = totalFees;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDiscountFees(double discountFees) {
        this.discountFees = discountFees;
    }

    // getter
    public double getTotalFees() {
        return totalFees;
    }

    public double getDiscountFees() {
        return discountFees;
    }

    public int getCodeTAC() {
        return codeTAC;
    }

    public int getReferenceNo() {
        return referenceNo;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getStatus() {
        return status;
    }

    // method
    public String output() {
        return String.format("------------------------------------------------------------------\n" +
                "                 Transaction of Ordering System                   \n" +
                "------------------------------------------------------------------\n" +
                ">> Payment Option/Method :                                        \n" +
                ">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                ">>    1.      E-Wallet                                            \n" +
                ">>    2.      Bank                                                \n" +
                ">>                                                                \n" +
                ">>    0.      Exit                                                \n" +
                ">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    public boolean verification(int noTAC) {
        if (codeTAC == noTAC) {
            status = "Successful";
            return true;
        } else {
            status = "Unsuccessful";
            return false;
        }
    }

    public double calTotalFees() {
        return (totalFees - discountFees);
    }

    public String receipt() {
        return String.format("------------------------------------------------------------------\n" +
                "                        Transaction Receipt                       \n" +
                "------------------------------------------------------------------\n" +
                ">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                ">> Transaction type    : %s                                       \n" +
                ">> Reference Number    : %d                                       \n" +
                ">> Status              : %s                                       \n" +
                ">> Date Created        : %s                                       \n", transactionType, referenceNo,
                status, dateCreated);
    }
}
