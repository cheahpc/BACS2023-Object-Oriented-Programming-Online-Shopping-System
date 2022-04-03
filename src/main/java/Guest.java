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
        // TODO Set automatic Guest ID Generator
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

}
