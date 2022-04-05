public class Order {
    // Data Properties
    private String orderID;
    private Customer customerDetails;
    private Payment payment;
    private String orderDate;
    private String orderStatus;

    // region STANDARD
    // Constructor
    public Order() {
    }

    public Order(String orderID, Customer customerDetails, Payment payment, String orderDate, String orderStatus) {
        this.orderID = orderID;
        this.customerDetails = customerDetails;
        this.payment = payment;
        this.orderDate = orderDate;
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

    public Payment ggetPayment() {
        return payment;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    // STANDARD

    // Method
    public String toString() {
        return String.format(
                "Order ID: %s\nOrder Date: %s\nOrder Status: %s\n\n-Customer Details- \n%s\n-Payment Details-\n%s\n",
                orderID, orderDate, orderStatus, customerDetails, payment);
    }

    public void reset() {
        orderID = null;
        orderDate = null;
        orderStatus = null;
        customerDetails = null;
        payment = null;
    }
}
