public class Order {
    // Data Properties
    private String orderID;
    private Customer customerDetails;
    private Payment payment;
    private String orderDate;
    private double totalAmount;
    private String orderStatus;

    // region STANDARD
    // Constructor
    public Order() {
    }

    public Order(String orderID, Customer customerDetails, Payment payment, String orderDate, double totalAmount,
            String orderStatus) {
        this.orderID = orderID;
        this.customerDetails = customerDetails;
        this.payment = payment;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
    }

    // Setter
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setCustomerDetails(Customer customerDetails) {
        this.customerDetails = customerDetails;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setTotalAmounnt(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    // Getter
    public String getOrderID() {
        return orderID;
    }

    public Customer getCustomerDetails() {
        return customerDetails;
    }

    public Payment getPayment() {
        return payment;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    // STANDARD

    // Method
    public String toString() {
        return String.format(
                "Order ID: %s\nOrder Date: %s\nTotal Amount: %.2f\nOrder Status: %s\n\n-Customer Details- \n%s\n-Payment Details-\n%s\n",
                orderID, orderDate, totalAmount, orderStatus, customerDetails, payment.toString());
    }

    public void reset() {
        orderID = null;
        orderDate = null;
        totalAmount = 0;
        orderStatus = null;
        customerDetails = null;
        payment = null;
    }
}
