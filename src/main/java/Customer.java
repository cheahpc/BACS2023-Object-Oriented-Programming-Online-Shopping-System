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
        return String.format("Full name: %s\nContact No: %s\nAddress: %s\n", getFullName(), getContactNo(),
                getAddress());
    }

    public void reset() {
        this.fullName = null;
        this.contactNo = null;
        this.address = null;
    }

    public String getUserID() {
        return "";
    }
}