public class RegisteredCustomer extends Customer {
    // Data Properties
    private String userName;
    private String password;
    private String email;

    // region STANDARD
    // Contructor
    public RegisteredCustomer() {
    }

    public RegisteredCustomer(String fullName, String contactNo, String address,
            String userName, String password, String email) {
        super(fullName, contactNo, address);
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    // Setter
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    // endregion STANDARD

    // Method
    public String toString() {
        return (String.format(
                "User Name: %s\nPassword: %s\nFull Name: %s\nContact Number: %s\nEmail: %s\nAddress: %s\n", userName,
                password, getFullName(), getContactNo(), email, getAddress()));
    }

    public String getUserID() {
        return getUserName();
    }

    public void reset() {
        super.reset();
        this.userName = null;
        this.password = null;
        this.email = null;
    }
}
