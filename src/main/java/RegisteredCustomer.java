public class RegisteredCustomer extends Customer {
    // Data Properties
    private String userName;
    private String password;
    private String email;

    // region STANDARD
    // Contructor
    public RegisteredCustomer() {
    }

    public RegisteredCustomer(String fullName, String contactNo, String address, String userName, String password,
            String email) {
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
}
