
import java.util.Date;

public class Payment {

    // data properties
    protected double totalFees, amount;
    protected Date dateCreated;
    protected String referenceID, status, transactionType;

    // no arg constructor
    public Payment() {

    }

    // arg constructor
    public Payment(double totalFees, double amount, String status, String transactionType, String referenceID) {
        this.totalFees = totalFees;
        this.amount = amount;
        this.status = status;
        this.transactionType = transactionType;
        this.referenceID = referenceID;
        dateCreated = new java.util.Date();// system generated user can't modify
    }

    // setter
    public void setTotalFees(double totalFees) {
        this.totalFees = totalFees;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReferenceID(String referenceID) {
        this.referenceID = referenceID;
    }

    // getter
    public double getTotalFees() {
        return totalFees;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getStatus() {
        return status;
    }

    public String getReferenceID() {
        return referenceID;
    }

    // method
    public boolean transaction() {
        if (amount == totalFees) {
            totalFees -= amount;
            return true;
        } else
            return false;
    }

    public String receipt() {
        return String.format("Status : %s\n"
                + "Date Created: %s\n"
                + "Transaction type: %s"
                + "\n Total Fees: RM%.2f"
                + "\n Amount Fees: RM%.2f", status, dateCreated, transactionType, totalFees, amount);
    }
}
