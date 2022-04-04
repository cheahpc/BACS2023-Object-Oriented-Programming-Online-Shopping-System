
public class Guest extends Customer {
    // Data Properties
    private String guestID;

    // region STANDARD
    // Contructor
    public Guest() {
    }

    public Guest(String fullName, String contactNo, String address, String guestID) {
        super(fullName, contactNo, address);
        this.guestID = guestID;
    }

    // Setter
    public void setGuestID(String guestID) {
        this.guestID = guestID;
    }

    // Getter
    public String getGuestID() {
        return guestID;
    }
    // endregion STANDARD

    // Method
    public String toString() {
        return String.format("Guest ID: %s\nFull name: %s\nContact Number: %s\nAddress: %s\n", guestID, getFullName(),
                getContactNo(), getAddress());
    }

    public String getUserID() {
        return getGuestID();
    }

    public void reset() {
        super.reset();
        this.guestID = null;
    }

}
