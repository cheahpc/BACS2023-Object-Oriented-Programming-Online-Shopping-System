public class Customer {
    // Data Properties
    private String fullName;
    private String contactNo;
    private String address;

    // region STANDARD
    // Constructor
    public Customer() {
    }

    public Customer(String fullName, String contactNo, String address) {
        this.fullName = fullName;
        this.contactNo = contactNo;
        this.address = address;
    }

    // Setter
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getter
    public String getFullName() {
        return fullName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getAddress() {
        return address;
    }
    // endregion STANDARD

    // Method
    public String toString() {
        // TODO To String Methods for Customer Class and subclasses for customer
        return "";
    }
}