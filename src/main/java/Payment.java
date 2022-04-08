
import java.util.Random;

public class Payment {

    // data properties
    protected int tacCode;
    private double totalFees, discountRate;
    private double serviceCharges;
    private String status, transactionType;

    // no arg constructor
    public Payment() {

    }

    // arg constructor
    public Payment(double totalFees, double discountRate, String status, String transactionType,
            double serviceCharges) {
        this.totalFees = totalFees;
        this.discountRate = discountRate;
        this.status = status;
        this.transactionType = transactionType;
        this.serviceCharges = serviceCharges;
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

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public void setServiceCharges(double serviceCharges) {
        this.serviceCharges = serviceCharges;
    }

    // getter
    public double getTotalFees() {
        return totalFees;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public int getTACcode() {
        Random randNum = new Random();
        return tacCode = (int) (randNum.nextInt(899999) + 100000);
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getStatus() {
        return status;
    }

    public double getServiceCharges() {
        return serviceCharges;
    }

    // method
    public void interfaceOutput() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("                 Transaction of Ordering System                   ");
        System.out.println("------------------------------------------------------------------");
        System.out.println(">> Payment Option/Method :                                        ");
        System.out.println(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(">>    1.      E-Wallet                                            ");
        System.out.println(">>    2.      Bank                                                ");
        System.out.println(">>                                                                ");
        System.out.println(">>    0.      Exit                                                ");
        System.out.println(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public double calTotalFees(double totalAmount) {
        totalFees = totalAmount - (totalAmount * discountRate) + serviceCharges;
        return totalFees;
    }

    public String toString() {
        return String.format(">> (-) Discount Rate (%s)       : %.2f     \n" +
                ">> (+) Services Charges        : RM %.2f  \n" +
                ">> Total Fees                  : RM %.2f  \n" +
                ">> Status                      : %s       \n" +
                ">> Transaction type            : %s       \n", "%", discountRate, serviceCharges,
                totalFees, status, transactionType);
    }
}