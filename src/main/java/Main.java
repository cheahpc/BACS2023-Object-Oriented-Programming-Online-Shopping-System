import java.io.File; // Import the File class
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle errors
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner; // Import the Scanner class to read text files
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import java.util.regex.*;

public class Main {
    public static final String DELIMITER = ":";
    public static final String ACCOUNT_FILE = "account.txt";
    public static final String GUEST_FILE = "guest.txt";
    public static final String ITEM_FILE = "item.txt";
    public static final String ORDER_FILE = "order.txt";
    public static String orderFileName = "";
    public static int option;
    public static int itemCounter = 0;
    public static String userInput;
    public static String[] tempStr = new String[10];
    public static boolean windowShopping = false;
    public static boolean loopVal = true;
    public static Scanner sc = new Scanner(System.in);
    static Customer theCust;
    static Order theOrder;
    static Product product;
    static Payment payment;
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    static Date date = new Date();

    public static void main(String[] args) {
        // region DEBUG
        // boolean Start = false;
        boolean Start = true;

        if (!Start) {
            // tempStrings[0] = "Ironm4n@toronto";
            // setLine("something new", ACCOUNT_FILE);
            // currentCustomer = new Guest("Full name", "0109939994", "random 123, penang",
            // "2");
            // currentCustomer = new RegisteredCustomer("Username", "PASSWORD", "fULLnaME",
            // "0192929100", "MR.CHEAH@LIVE.COM", "random 123, penang");
            // currentOrder = new Order("101", currentCustomer, payment,
            // dateFormat.format(date), "Pass");

            for (int i = 1; i < getFileSize(GUEST_FILE); i++) {
                echo(getAllLine(GUEST_FILE)[i], true);
            }
        }
        // endregion DEBUG

        LevelA: while (Start) {

            // region Level 1 - Login Menus
            while (loopVal) {
                switch (option = getOption(1, 0)) { // Login Interface
                    case 1: // User Login
                        loopVal = runA_UserLogin();
                        break;
                    case 2: // Create User Account
                        loopVal = runA_CreateAccount();
                        break;
                    case 3: // Login as Guest
                        loopVal = runA_GuestLogin();
                        break;
                    case 4: // Register New Guest
                        loopVal = runA_GuestSignUp();
                        break;
                    case 5:
                        loopVal = false;
                        windowShopping = true;
                        break;
                    case 6:
                        runA_ExitProgram();
                        break;
                    default:
                        break;
                }
            }
            // endregion Level 1 - Login Menu

            // region Level 2 - Shop Now / Show Order
            if (!windowShopping) {
                loopVal = true; // Reset Entry Key
            }

            LevelB: while (loopVal) {
                switch (option = getOption(2, 0)) {
                    case 1: // Track Order
                        runB_TrackOrder();
                        continue LevelB;
                    case 2: // Shop Now
                        loopVal = false; // Proceed to Shopping Item
                        break;

                    case 3: // USer Choose Sign Out
                        theCust.reset();
                        continue LevelA; /// Back to top layer
                    default:
                        break;
                }
            }
            // endregion Level 2 - Shop Now / Show Order

            // region Level 3 - Shopping
            if (!windowShopping) {
                theOrder = new Order(String.valueOf(getFileSize(ORDER_FILE)), theCust, payment, todayDate(),
                        "Pending Payment");

                echo(theOrder.toString(), true); // Debug?
                orderFileName = getOrderFileName(); // Set order file name for the customer
                fileCreate(orderFileName); // Create the order file for the customer
            }
            loopVal = true;

            LevelC: while (loopVal) {
                switch (option = getOption(3, 0)) {

                    case 101: // Check Order
                        if (windowShopping) {
                            continue LevelA;
                        } else {
                            // TODO Level C Algorithm for check order
                        }
                        break;
                    case 102: // USer Choose Track Order
                        // TODO Algorithm for Track Order

                        break;
                    case 103: // User Choose back
                        if (windowShopping) {
                            msgBox("Invalid option", "Input Denied", 0);
                            break;
                        } else {
                            continue LevelC;
                        }
                    default:
                        if (!windowShopping) {
                            runC_SetProductQty();
                            break;
                        }
                        msgBox("Unable to select a product.\nPlease login as a user or guest to select a product.",
                                "Windows Shopping Only", 1);
                        break;
                }
            }
            // endregion Level 3 - Shopping

            // region level 4 - Payment

            // endregion Level 4 - Payment
        }

    }

    // region MENU FUNCTION A
    public static boolean runA_UserLogin() {
        UserLogin: do {
            switch (getOption(1, 1)) {
                case 1: // User Choose 1 - User Name
                    tempStr[0] = getInput(1, 1, 1); // Enter UserName
                    // If user name is not found
                    if (getLine(tempStr[0], 0, ACCOUNT_FILE) == "") {
                        // Print user not found message
                        msgBox("Account not found with username: " + tempStr[0],
                                "User Does Not Exist", 1);
                        tempStr[0] = null; // Reset the temp var
                    }
                    break;
                case 2: // User Choose 2 - Password
                    if (tempStr[0] == null) { // If no user name is empty
                        // Prompt user to input user name first
                        msgBox("Please input user name first.", "User Name Is Empty", 1);
                        continue UserLogin;
                    } else {
                        tempStr[1] = getInput(1, 1, 2); // Enter Password
                        tempStr[2] = splitData(getLine(tempStr[0], 0, ACCOUNT_FILE))[1];
                        // If password is worng
                        if (!tempStr[1].equals(tempStr[2])) {
                            // Print wrong password message
                            msgBox("Wrong password", "Password Incorrect", 0);
                            tempStr[1] = null; // Reset password field
                        }
                    }
                    break;
                case 3: // User Choose 3 - Back
                    Arrays.fill(tempStr, null); // Reset Array
                    return true; // Back to Main Menu
            }
        } while (tempStr[0] == null || tempStr[1] == null);
        displayInterface(1, 1);
        echo("", true);
        echo(">> Login Successfull", true);
        echo(">> Welcome Back", true);
        echo("", true);
        tempStr[9] = getLine(tempStr[0], 0, ACCOUNT_FILE);
        theCust = new RegisteredCustomer(
                splitData(tempStr[9])[2],
                splitData(tempStr[9])[3],
                splitData(tempStr[9])[5],
                splitData(tempStr[9])[0],
                splitData(tempStr[9])[1],
                splitData(tempStr[9])[4]);
        echo(theCust.toString(), true);
        echo("", true);
        Arrays.fill(tempStr, null); // Reset Array
        windowShopping = false; // Set windows shopping to false
        contin1uePrompt();
        return false; // Continue to next section
    }

    public static boolean runA_CreateAccount() {
        CreateAccount: do {
            switch (getOption(1, 2)) {
                case 1: // Create New User Account - Set User name
                    tempStr[0] = getInput(1, 2, 1);
                    // If user name is found
                    if (getLine(tempStr[0], 0, ACCOUNT_FILE) != "") {
                        msgBox("User name " + tempStr[0] + " already taken: ", "User Name Already Taken", 1);
                        tempStr[0] = null; // Reset the temp var
                    }
                    break;
                case 2: // Create New User Account - Set Password
                    tempStr[1] = getInput(1, 2, 2);
                    break;
                case 3: // Create New User Account - Set Full name
                    tempStr[2] = getInput(1, 2, 3);
                    break;
                case 4: // Create New User Account - Set Contact number
                    tempStr[3] = getInput(1, 2, 4);
                    break;
                case 5: // Create New User Account - Set Email
                    tempStr[4] = getInput(1, 2, 5);
                    break;
                case 6: // Create New User Account - Set Address
                    tempStr[5] = getInput(1, 2, 6);
                    break;
                case 7: // back to previous menu
                    Arrays.fill(tempStr, null); // Reset Array
                    return true; // Back to main menu
            }
            // Check for empty string,
            for (int i = 0; i < 6; i++) {
                if (tempStr[i] == null) {
                    // Continue with this menu if there are empty field
                    continue CreateAccount;
                }
            }
            tempStr[9] = String.format("%s:%s:%s:%s:%s:%s",
                    tempStr[0], tempStr[1], tempStr[2], tempStr[3], tempStr[4], tempStr[5]);
            setLine(tempStr[9], ACCOUNT_FILE); // Write new account info to the file
            displayInterface(1, 2);
            echo("", true);
            echo(">> User Account Created", true);
            echo(">> Welcome to Number 3 Boba", true);
            echo("", true);
            theCust = new RegisteredCustomer(
                    splitData(tempStr[9])[2],
                    splitData(tempStr[9])[3],
                    splitData(tempStr[9])[5],
                    splitData(tempStr[9])[0],
                    splitData(tempStr[9])[1],
                    splitData(tempStr[9])[4]);
            echo(theCust.toString(), true);
            echo("", true);
            Arrays.fill(tempStr, null); // Reset Array
            windowShopping = false; // Set windows shopping to false
            contin1uePrompt();
            return false; // Continue to next section
        } while (true);
    }

    public static boolean runA_GuestLogin() {
        GuestLogin: do {
            switch (getOption(1, 3)) {
                case 1: // Guest Loin - Set Guest ID
                    tempStr[0] = getInput(1, 3, 1); // Get the ID from use
                    if (getLine(tempStr[0], 0, GUEST_FILE) == "") { // If ID not found
                        msgBox("Guest ID not registered in System.", "Invalid Guest ID", 1);
                        tempStr[0] = null; // Reset the temp var
                        continue GuestLogin;
                    }
                    break;
                case 2: // back to previous menu
                    tempStr[0] = null; // Reset temporary var
                    return true; // Back to main menu
            }
            displayInterface(1, 3);
            echo("", true);
            echo(">> Login Successfull", true);
            echo(">> Welcome Back", true);
            echo("", true);
            tempStr[9] = getLine(tempStr[0], 0, GUEST_FILE);
            theCust = new Guest(
                    splitData(tempStr[9])[1],
                    splitData(tempStr[9])[2],
                    splitData(tempStr[9])[3],
                    splitData(tempStr[9])[0]);
            echo(theCust.toString(), true);
            echo("", true);
            Arrays.fill(tempStr, null); // Reset Array
            windowShopping = false; // Set windows shopping to false
            contin1uePrompt();
            return false; // Continue to next section
        } while (true);
    }

    public static boolean runA_GuestSignUp() {
        tempStr[3] = String.valueOf(getFileSize(GUEST_FILE)); // Set the next Guest ID base on file
        GuestSignUp: do {
            switch (getOption(1, 4)) {
                case 1: // Guest Sign Up - Set Full Name
                    tempStr[0] = getInput(1, 4, 1);
                    break;
                case 2: // Guest Sign Up - Set Contact Number
                    tempStr[1] = getInput(1, 4, 2);
                    break;
                case 3: // Guest Sign Up - Set Address
                    tempStr[2] = getInput(1, 4, 3);
                    break;
                case 4: // Guest Sign Up - Go Back to previous menu
                    Arrays.fill(tempStr, null); // Reset Array
                    return true; // Back to main menu
            }

            // Check for empty string,
            for (int i = 0; i < 3; i++) {
                if (tempStr[i] == null) {
                    // Continue with this menu if there are empty field
                    echo("testing 123 abc debug mode", true);
                    continue GuestSignUp;
                }
            }
            tempStr[9] = String.format("%s:%s:%s:%s",
                    tempStr[3], tempStr[0], tempStr[1], tempStr[2]);
            setLine(tempStr[9], GUEST_FILE); // Write new account info to the file
            displayInterface(1, 3);
            echo("", true);
            echo(">> Sign Up Successful", true);
            echo(">> Welcome to Number 3 Boba", true);
            echo("", true);
            theCust = new Guest(
                    splitData(tempStr[9])[1],
                    splitData(tempStr[9])[2],
                    splitData(tempStr[9])[3],
                    splitData(tempStr[9])[0]);
            echo(theCust.toString(), true);
            echo("", true);
            Arrays.fill(tempStr, null); // Reset Array
            windowShopping = false; // Set windows shopping to false
            contin1uePrompt();
            return false; // Continue to next section
        } while (true);
    }

    public static void runA_ExitProgram() {
        msgBox("Thank you for you support!", "See You Again!", 1);
        System.exit(0);
    }
    // endregion MENU FUNCTION A

    // region MENU Function B
    public static void runB_TrackOrder() {
        do {
            switch (getOption(2, 1)) {
                case 1: // User Choose 1 - Set Order ID
                    tempStr[0] = getInput(2, 1, 1); // Get Order ID
                    if (getLine(tempStr[0], 0, ORDER_FILE) == "") {
                        // When Order ID Not found
                        tempStr[0] = null;
                        msgBox("Order ID does not exist.", "Invalid Order ID", 0);
                    } else if (!(theCust.getUserID()
                            .equals(splitData(getLine(tempStr[0], 0, ORDER_FILE))[2]))) {
                        // When order id found but ID does not belong to this user;
                        msgBox("This Order ID does not exist in your order history.", "No Such Order", 0);
                        tempStr[0] = null;
                    }
                    break;
                case 2: // User Choose 3 - Back
                    return;
            }
        } while (tempStr[0] == null);

        // Set order object
        theOrder = new Order(tempStr[0], theCust, payment,
                splitData(getLine(tempStr[0], 0, ORDER_FILE))[1],
                splitData(getLine(tempStr[0], 0, ORDER_FILE))[2]);

        // Set file name to retrieve order file
        if (theCust instanceof Guest) {
            orderFileName = String.format("Guest_%s_%s_%s.txt", theCust.getUserID(), theOrder.getOrderDate(),
                    theOrder.getOrderID());
        } else if (theCust instanceof RegisteredCustomer) {
            orderFileName = String.format("User_%s_%s_%s.txt", theCust.getUserID(), theOrder.getOrderDate(),
                    theOrder.getOrderID());
        }

        // Open file and read the file
        // TODO Level B Show Order Details-----------------------------------------
        displayInterface(2, 12);
        contin1uePrompt(); // Pause for the user to check order details
        tempStr[0] = null; // Reset Order ID for temp string
        return;
    }

    // endregion MENU Function B

    // region MENU FUNCTION C
    public static void runC_SetProductQty() {
        do {

            if (getLine(String.valueOf(option), 0, ITEM_FILE) == "") {
                // If user enter product id that does not exist
                msgBox("Item Not available", "Invalid Item Choice", 0); // Error message
            } else {
                // TODO LEVEL C check getInput
                int quantity = Integer.parseInt(getInput(3, 3, 0)); // Prompt user for quantity input
                if (quantity == 0) { // If user enter 0 quantity
                    msgBox("Quantity cannot be 0", "Invalid Quantity", 0);
                } else {

                }
            }

            // No : Order ID : Item ID : Item Name : Item Price : Qty
            tempStr[9] = getLine(String.valueOf(option), 0, ITEM_FILE); // Get detail of item from file
            product = new Product(splitData(tempStr[9])[1], splitData(tempStr[9])[2],
                    Double.parseDouble(splitData(tempStr[9])[3])); // Set product product object

            tempStr[4] = todayDate(); // Set today date
            itemCounter += 1;
            setLine(tempStr[8], orderFileName);
        } while (true);

    }
    // endregion MENU FUNCTION C

    // region DISPLAY
    public static void clearScreen() {
        // System.out.print("\033[H\033[2J");
        // System.out.flush();
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void displayInterface(int levelA, int levelB) {
        if (levelB < 10) {
            clearScreen();
        }
        switch (levelA) {
            case 1: // Login Module
                switch (levelB) {
                    case 0: // Main Menu
                        echo("------------------------------------------------------------------", true);
                        echo("                     Welcome To Number 3 Boba!                    ", true);
                        echo("------------------------------------------------------------------", true);
                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        echo(">>    1.      User Login", true);
                        echo(">>    2.      Create New User Account", true);
                        echo(">>    3.      Guest Login", true);
                        echo(">>    4.      Guest Sign Up", true);
                        echo(">>    5.      Window Shopping", true);
                        echo(">>", true);
                        echo(">>    6.      Exit", true);
                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        echo(">>", true);
                        echo(">> Choose an option: ", false);
                        break;
                    case 1: // User Login
                        echo("------------------------------------------------------------------", true);
                        echo("                            User Login                            ", true);
                        echo("------------------------------------------------------------------", true);
                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        echo(">>    1.      User Name   : " + ((tempStr[0] == null) ? "" : tempStr[0]), true);
                        echo(">>    2.      Password    : " + ((tempStr[1] == null) ? "" : tempStr[1]), true);
                        echo(">>", true);
                        echo(">>    3.      Back", true);
                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        echo(">>", true);
                        break;
                    case 10:
                        echo(">> Choose an option: ", false);
                        break;
                    case 11: // User Login - User Name input
                        echo(">> Please enter 1. USER NAME: ", false);
                        break;
                    case 12: // User Login - User Password input
                        echo(">> Please enter 2. PASSWORD: ", false);
                        break;
                    case 2: // Create New User Account
                        echo("------------------------------------------------------------------", true);
                        echo("                     Create New User Account                      ", true);
                        echo("------------------------------------------------------------------", true);
                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        echo(">>    1.      User Name       : " + ((tempStr[0] == null) ? "" : tempStr[0]),
                                true);
                        echo(">>    2.      Password        : " + ((tempStr[1] == null) ? "" : tempStr[1]),
                                true);
                        echo(">>    3.      Full Name       : " + ((tempStr[2] == null) ? "" : tempStr[2]),
                                true);
                        echo(">>    4.      Contact Number  : " + ((tempStr[3] == null) ? "" : tempStr[3]),
                                true);
                        echo(">>    5.      Email           : " + ((tempStr[4] == null) ? "" : tempStr[4]),
                                true);
                        echo(">>    6.      Address         : " + ((tempStr[5] == null) ? "" : tempStr[5]),
                                true);
                        echo(">>", true);
                        echo(">>    7.      Back", true);
                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        echo(">>", true);
                        break;
                    case 20: // Create New User Account
                        echo(">> Choose an option: ", false);
                        break;
                    case 21: // Create New User Account - Set User name
                        echo(">> Please enter 1. USER NAME: ", false);
                        break;
                    case 22: // Create New User Account - Set Password
                        echo(">> Please enter 2. PASSWORD: ", false);
                        break;
                    case 23: // Create New User Account - Set Full name
                        echo(">> Please enter 3. FULL NAME: ", false);
                        break;
                    case 24: // Create New User Account - Set Contact number
                        echo(">> Please enter 4. CONTACT NUMBER: ", false);
                        break;
                    case 25: // Create New User Account - Set Email
                        echo(">> Please enter 5. EMAIL: ", false);
                        break;
                    case 26: // Create New User Account - Set Address
                        echo(">> Please enter 6. ADDRESS: ", false);
                        break;
                    case 3: // Guest Login
                        echo("------------------------------------------------------------------", true);
                        echo("                            Guest Login                           ", true);
                        echo("------------------------------------------------------------------", true);
                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        echo(">>    1.      Guest ID         : " + ((tempStr[0] == null) ? "" : tempStr[0]),
                                true);
                        echo(">>", true);
                        echo(">>    2.      Back", true);
                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        echo(">>", true);
                        break;
                    case 30: // Guest Login
                        echo(">> Choose an option: ", false);
                        break;
                    case 31: // Guest Login - Set Guest ID
                        echo(">> Please enter 1. GUEST ID: ", false);
                        break;
                    case 4: // Guest Sign Up
                        echo("------------------------------------------------------------------", true);
                        echo("                           Guest Sign Up                          ", true);
                        echo("------------------------------------------------------------------", true);
                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        echo(">>    ..      Guest ID        : " + tempStr[3], true);
                        echo(">>    1.      Full Name       : " + ((tempStr[0] == null) ? "" : tempStr[0]),
                                true);
                        echo(">>    2.      Contact Number  : " + ((tempStr[1] == null) ? "" : tempStr[1]),
                                true);
                        echo(">>    3.      Address         : " + ((tempStr[2] == null) ? "" : tempStr[2]),
                                true);
                        echo(">>", true);
                        echo(">>    4.      Back", true);
                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        echo(">>", true);
                        break;
                    case 40: // Guest Sign Up
                        echo(">> Choose an option: ", false);
                        break;
                    case 41:// Guest Sign Up - Set Full Name
                        echo(">> Please enter 1. FULL NAME: ", false);
                        break;
                    case 42: // Guest Sign Up - Set Full Name
                        echo(">> Please enter 2. CONTACT NUBMER: ", false);
                        break;
                    case 43: // Guest Sign Up - Set Address
                        echo(">> Please enter 3. ADDRESS: ", false);
                        break;
                }
                break;
            case 2: // Shopping / Track Order Module
                switch (levelB) {
                    case 0: // Account Page
                        echo("------------------------------------------------------------------", true);
                        echo("                 What would you like to do today?                 ", true);
                        echo("------------------------------------------------------------------", true);
                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        echo(">>    1.      Track Order", true);
                        echo(">>    2.      Go Shopping", true);
                        echo(">>", true);
                        echo(">>    3.      Sign Out", true);
                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        echo(">>", true);
                        echo(">> Choose an option: ", false);
                        break;
                    case 1: // Order Track Page
                        echo("------------------------------------------------------------------", true);
                        echo("                            Track Order                           ", true);
                        echo("------------------------------------------------------------------", true);
                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        echo(">>    1.      Order ID    : " + ((tempStr[0] == null) ? "" : tempStr[0]), true);
                        echo(">>", true);
                        echo(">>    2.      Back", true);
                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        echo(">>", true);
                        break;
                    case 10:
                        echo(">> Choose an option: ", false);
                        break;
                    case 11:
                        echo(">> Please enter 1. ORDER ID: ", false);
                        break;
                    case 12:
                        echo("------------------------------------------------------------------", true);
                        echo("                           Order Detail                           ", true);
                        echo("------------------------------------------------------------------", true);
                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        echo(">> Order ID: " + theOrder.getOrderID(), false);
                        echo(">> Order Date: " + theCust.getUserID(), false);
                        echo(">> Order Total: " + theCust.getUserID(), false);
                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        echo(">>", true);
                        echo(">>    No      Item ID     Item Name   Item Price      Qty ", true);
                        for (int i = 0; i < getFileSize(orderFileName); i++) {
                            tempStr[9] = splitData(getAllLine(orderFileName)[i])[0]; // Set List No
                            if (tempStr[9] == "ORDER") {
                                tempStr[8] = splitData(getAllLine(orderFileName)[i])[1]; // Set Order Status
                                tempStr[7] = splitData(getAllLine(orderFileName)[i])[2]; // Set Order Status Date
                            }
                            // 5:2:1:ProductE:11.20:3
                            // No : Item ID : Item Name : Item Price : Qty
                            tempStr[8] = splitData(getAllLine(orderFileName)[i])[1]; // Set Item ID
                            tempStr[7] = splitData(getAllLine(orderFileName)[i])[2]; // Set Item Name
                            tempStr[6] = splitData(getAllLine(orderFileName)[i])[3]; // Set Item Price
                            tempStr[5] = splitData(getAllLine(orderFileName)[i])[4]; // Set Item Qty

                        }
                        // No : Order ID : Item ID : Item Name : Item Price : Qty
                        // ORDER : Status : Status Date
                        // TODO Design ORDER DETAIL INTERFACE -------------------------------------
                        break;
                }
                break;
            case 3: // Shop Item Module
                switch (levelB) {
                    case 0:
                        echo("------------------------------------------------------------------", true);
                        echo("                        Number 3 Bubble Tea                       ", true);
                        echo("------------------------------------------------------------------", true);
                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        if (theCust instanceof RegisteredCustomer) {
                            echo(">> Current User: " + theCust.getUserID(), false);
                            // TODO How to calculate the amount ?!!!!!!!!!!!!!!!!!!!
                        } else if (theCust instanceof Guest) {
                            echo(">> Guest ID    : " + theCust.getUserID(), false);
                        }
                        echo(">> Cart Ammount: ", false);
                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        echo(">>    No        Product                 Price", true);
                        for (int i = 0; i < getFileSize(ITEM_FILE); i++) {
                            if (i < 9) {
                                echo(">>     ", false);
                            } else {
                                echo(">>    ", false);
                            }
                            echo(splitData(getAllLine(ITEM_FILE)[i])[0] + "\t" + splitData(
                                    getAllLine(ITEM_FILE)[i])[1] + "\t\t"
                                    + splitData(
                                            getAllLine(ITEM_FILE)[i])[2],
                                    true);
                        }
                        echo(">>", true);
                        if (windowShopping) {
                            echo(">>   101.      Back", true);
                        } else {
                            echo(">>   101.      View Cart", true);
                            echo(">>   102.      Proceed to payment", true);
                            echo(">>   103.      Back", true);
                        }
                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        echo(">>", true);
                        echo(">> Choose an option: ", false);
                        break;
                    case 1: // View Cart
                        // TODO INTERFACE DESIGN - View Cart page
                        break;
                    case 2: // Checkout Page
                        // TODO INTERFACE DESIGN - Checkout page
                        break;
                    case 30: // Ask user for quantity
                        echo("------------------------------------------------------------------", true);
                        echo("                        Number 3 Bubble Tea                       ", true);
                        echo("------------------------------------------------------------------", true);
                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        if (theCust instanceof RegisteredCustomer) {
                            echo(">> Current User: " + theCust.getUserID(), false);
                        } else if (theCust instanceof Guest) {
                            echo(">> Guest ID    : " + theCust.getUserID(), false);
                        }
                        // TODO How to calculate the amount ?!!!!!!!!!!!!!!!!!!!
                        echo(">> Cart Ammount: ", false);
                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        echo(">>    No        Product                 Price", true);
                        for (int i = 0; i < getFileSize(ITEM_FILE); i++) {
                            if (i < 9) {
                                echo(">>     ", false);
                            } else {
                                echo(">>    ", false);
                            }
                            echo(splitData(getAllLine(ITEM_FILE)[i])[0] + "\t" + splitData(
                                    getAllLine(ITEM_FILE)[i])[1] + "\t\t"
                                    + splitData(
                                            getAllLine(ITEM_FILE)[i])[2],
                                    true);
                        }
                        echo(">>", true);
                        if (windowShopping) {

                        } else {
                            echo(">>   101.      View Cart", true);
                            echo(">>   102.      Proceed to payment", true);
                            echo(">>   103.      Back", true);
                        }

                        echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                        echo(">>", true);
                        echo(">> Enter a quantity: ", false);
                        ;

                    default:
                        break;
                }
            default:
                break;
        }

    }

    // endregion DISPLAY

    // region GET OPTION INPUT
    public static int getOption(int levelA, int levelB) {
        do {
            displayInterface(levelA, levelB);
            displayInterface(levelA, levelB * 10);
            if (inputPatternCheck(levelA, levelB)) {
                break; // Break if input match
            } else {
                // Else show error messages
                msgBox("Input option denied.", "Invalid Input Option", 0);
            }
        } while (true);
        return Integer.parseInt(userInput); // Return user choice
    }

    public static String getInput(int level1, int level2, int level3) {
        String message = "", title = "";
        switch (level1) {
            case 1:
                switch (level2) {
                    case 1: // User Login
                        switch (level3) {
                            case 1: // User Login - User Name input
                                message = "Username must contain: .\n - Alphanumeric Only\n - At least 8 characters\n - No whitespace.";
                                title = "Invalid User Name";
                                break;
                            case 2: // User Login - User Password input
                                message = "Password must contain:\n - At least 1 lower case character\n - At least 1 Upper Case character\n - At least 1 special character\n - At least 8 character";
                                title = "Invalid Password";
                                break;
                            default:
                                break;
                        }
                        break;
                    case 2: // Create User Account
                        switch (level3) {
                            case 1: // Create New User Account - Set User name
                                message = "Username must contain: .\n - Alphanumeric Only\n - 8 characters\n - No whitespace.";
                                title = "Invalid User Name";
                                break;
                            case 2: // Create New User Account - Set Password
                                message = "Password must contain:\n - At least 1 lower case character\n - At least 1 Upper Case character\n - At least 1 special character\n - At least 8 character";
                                title = "Invalid Password";
                                break;
                            case 3: // Create New User Account - Set Full name
                                message = "Full Name Must be alphabets only.";
                                title = "Invalid Full Name";
                                break;
                            case 4: // Create New User Account - Set Contact number
                                message = "Contact number format must follow\n - 0101234567";
                                title = "Invalid Contact Number";
                                break;
                            case 5: // Create New User Account - Set Email
                                message = "Email format must follow\n - abc@xyz.com";
                                title = "Invalid Email";
                                break;
                            case 6: // Create New User Account - Set Address
                                message = "Address must conatin: \n - At least 1 number\n - At least 1 alphabet";
                                title = "Invalid Address";
                                break;
                        }
                        break;
                    case 3: // Guest Login
                        switch (level3) {
                            case 1: // Guest Login - Set Guest ID
                                message = "Only digits accepted and Maximum of 5 digits allowed.\n eg. 02919";
                                title = "Invalid Guest ID";
                                break;
                        }
                        break;
                    case 4: // Guest Sign Up
                        switch (level3) {
                            case 1: // Guest Sign Up - Set Full Name
                                message = "Full Name Must be alphabets only.";
                                title = "Invalid Full Name";
                                break;
                            case 2: // Guest Sign Up - Set Contact Number
                                message = "Contact number format must follow\n - 0101234567";
                                title = "Invalid Contact Number";
                                break;
                            case 3: // Guest Sign Up - Set Address
                                message = "Address must conatin: \n - At least 1 number\n - At least 1 alphabet";
                                title = "Invalid Address";
                                break;
                        }
                        break;
                }
                break;
            case 2:
                switch (level2) {
                    case 1: // Track Order
                        switch (level3) {
                            case 1: // Track Order - Set Order ID
                                message = "Username must contain: .\n - Alphanumeric Only\n - At least 8 characters\n - No whitespace.";
                                title = "Invalid Order ID";
                                break;
                            case 2: // Track Oder - Set Order Dates
                                message = "Date must follow the following format:\n - dd-mm-yyyy\n eg. 22/05/2000";
                                title = "Invalid Order Date";
                                break;
                        }
                        break;
                }
                break;
            case 3:
                // TODO LEVEL C ERROR MESSAGE
                switch (level2) {
                    case 3:
                        switch (level3) {
                            case 0:
                                message = "Input option denied";
                                title = "Invalid Input";
                                break;

                        }

                        break;

                    default:
                        break;
                }
                break;
        }

        // Check input with pattern
        do {
            displayInterface(level1, level2);
            displayInterface(level1, (level2 * 10) + level3);
            if (inputPatternCheck(level1, (level2 * 10) + level3)) {
                return userInput; // Return input string if it passed input check
            } else {
                msgBox(message, title, 0);
            }
        } while (true);
    }
    // endregion GET OPTION

    // region INPUT
    public static void contin1uePrompt() {
        System.out.println("Press {Enter} key to continue!"); // Print prompt message
        try {
            sc.nextLine(); // Read input
        } catch (Exception e) {
            msgBox("Unable to process Input", "An Error Occured", 0);
            e.printStackTrace();
        }
    }

    public static String readIn() {
        do {
            try {
                String temp = sc.nextLine(); // Read next line
                return temp; // return the line if no error occured
            } catch (Exception e) {
                msgBox("Input process error.", "Error Occured", 0); // Display the error message
                e.printStackTrace();
            }
        } while (true);
    }

    public static boolean inputPatternCheck(int level, int inputID) {
        Pattern regExPat = Pattern.compile("");// Create object for Regex Pattern
        Matcher matchVar; // Create object for Matching Regex
        userInput = ""; // Reset Input
        userInput = readIn(); // Read user input
        // Check Input ID
        switch (level) {
            case 1:
                switch (inputID) {
                    case 0: // Main Menu
                        regExPat = Pattern.compile("[1-5]{1}");
                        break;
                    case 1: // User Login
                        regExPat = Pattern.compile("[1-3]{1}");
                        break;
                    case 11: // User Login - User Name
                        regExPat = Pattern.compile("[a-zA-Z0-9]{8,}");
                        break;
                    case 12: // User Login - Password
                        regExPat = Pattern.compile(
                                "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[~`!@#$%^&*;\':\"<>?,./\\+=|-])[A-Za-z0-9~`!@#$%^&*;\':\"<>?,./\\+=|-]{8,}");
                        break;
                    case 2: // Create Account
                        regExPat = Pattern.compile("[1-7]{1}");
                        break;
                    case 21: // Create Account - Set User Name
                        regExPat = Pattern.compile("[a-zA-Z0-9]{8,}");
                        break;
                    case 22: // Create Account - Set Password
                        regExPat = Pattern.compile(
                                "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[~`!@#$%^&*;\':\"<>?,./\\+=|-])[A-Za-z0-9~`!@#$%^&*;\':\"<>?,./\\+=|-]{8,}");
                        break;
                    case 23: // Create Account - Set Full name
                        regExPat = Pattern.compile("[^.!@#$%^&*+:;/|?0-9\"]*");
                        break;
                    case 24: // Create Account - Set Contact number
                        regExPat = Pattern.compile("^01[0-9]{8,9}$");
                        break;
                    case 25: // Create Account - Set Email
                        regExPat = Pattern.compile(
                                "^[a-zA-Z_0-9]+([.-]?[a-zA-Z_0-9]+)*@[a-zA-Z_0-9]+([.-]?[a-zA-Z_0-9]+)*(.[a-zA-Z_0-9]{2,3})+$");
                        break;
                    case 26: // Create Account - Set Address
                        regExPat = Pattern.compile("[^.!@#$%^&*+:;/|?\"]*");
                        break;
                    case 3: // Guest Login
                        regExPat = Pattern.compile("[12]{1}");
                        break;
                    case 31: // Guest Login - Set Guest ID
                        regExPat = Pattern.compile("[0-9]{1,5}");
                        break;
                    case 4: // Guest Sign Up
                        regExPat = Pattern.compile("[0-4]{1}");
                        break;
                    case 41: // Guest Sign Up - Set Full Name
                        regExPat = Pattern.compile("[^.!@#$%^&*+:;/|?0-9\"]*");
                        break;
                    case 42: // Guest Sign Up - Set Contact Number
                        regExPat = Pattern.compile("^01[0-9]{8,9}$");
                        break;
                    case 43: // Guest Sign Up - Set Address
                        regExPat = Pattern.compile("[^.!@#$%^&*+:;/|?\"]*");
                        break;
                    default:
                        return true; // Default return value when no ID detected
                }
                break;
            case 2:
                switch (inputID) {
                    case 0: // Main Menu
                        regExPat = Pattern.compile("[1-3]{1}");
                        break;
                    case 1: // Track Order
                        regExPat = Pattern.compile("[12]{1}");
                        break;
                    case 11: // Track Order - Set Order ID
                        regExPat = Pattern.compile("[0-9]{1,5}");
                        break;
                    default:
                        return true; // Default return value when no ID detected
                }
                break;
            case 3:
                switch (inputID) {
                    case 1:
                        regExPat = Pattern.compile("[0-9]{1,3}");
                        break;

                    default:
                        break;
                }
            default:
                return true; // Default return value when no ID detected
        }

        matchVar = regExPat.matcher(userInput);
        // Return true or false if matched or not matched
        return matchVar.matches();
    }
    // endregion INPUT

    // region OUTPUT
    public static String todayDate() {
        return dateFormat.format(date);
    }

    public static void msgBox(String message, String title, int optionType) {
        // PLAIN_MESSAGE = -1
        // ERROR_MESSAGE = 0
        // INFORMATION_MESSAGE = 1
        // WARNING_MESSAGE = 2
        // QUESTION_MESSAGE = 3
        JOptionPane.showMessageDialog(null, message, title, optionType);
    }

    public static void echo(String message, Boolean newLine) {
        if (newLine) {
            System.out.println(message);
        } else {
            System.out.print(message);
        }
    }
    // endregion OUTPUT

    // region FILE HANDLING

    public static String getOrderFileName() {
        if (theCust instanceof Guest) {
            return (String.format("Guest_%s_%s_%s", theCust.getUserID(), todayDate(), theOrder.getOrderID()));
        } else if (theCust instanceof RegisteredCustomer) {
            return (String.format("User_%s_%s_%s", theCust.getUserID(), todayDate(), theOrder.getOrderID()));
        } else {
            return "ERROR";
        }
    }

    public static String getLine(String keyWord, int index, String file) {
        try {
            File dataFile = new File(file); // Open the file
            try {
                Scanner dataFileLine = new Scanner(dataFile); // Create object to read the file
                String data; // Create a variable to store the data read
                while (dataFileLine.hasNextLine()) { // While there are lines to read
                    data = dataFileLine.nextLine(); // Else read the next line
                    String[] dummy = data.split(":"); // Split each line into arrays with delimiter
                    if (dummy[index].equalsIgnoreCase(keyWord)) { // If the first element of the line match with the
                                                                  // keyword
                        dataFileLine.close(); // close the object to read the file
                        return data; // Return the line that has matching keyword
                    }
                }
                dataFileLine.close(); // Close the object to read the file
                // In case of line exhaustion and no matching record found
                return "";// Return empty line
            } catch (NoSuchElementException e) {
                // Catch the empty file error and print the message
                msgBox("The file has no data inside.", "File Is Empty", -1);
                // Print the details to the terminal
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // Catch the file not found error and print the message
            msgBox("The data file is missing!", "File Not Found", 0);
            // Print the details to the terminal
            e.printStackTrace();
        }
        return "";// Return empty line
    }

    public static String[] getAllLine(String file) {
        int counter = 0;
        String[] dummy = new String[getFileSize(file)];
        try {
            File dataFile = new File(file); // Open the file
            try {
                Scanner dataFileLine = new Scanner(dataFile); // Create object to read the file

                while (dataFileLine.hasNextLine()) { // While there are lines to read
                    dummy[counter] = dataFileLine.nextLine(); // Store each line int an array
                    counter++;
                }
                dataFileLine.close(); // Close the object to read the file

                return dummy; // Return the array
            } catch (NoSuchElementException e) {
                // Catch the empty file error and print the message
                msgBox("The file has no data inside.", "File Is Empty", -1);
                // Print the details to the terminal
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // Catch the file not found error and print the message
            msgBox("The data file is missing!", "File Not Found", 0);
            // Print the details to the terminal
            e.printStackTrace();
        }
        return dummy;
    }

    public static int getFileSize(String file) {
        int counter = 0;
        try {
            File dataFile = new File(file); // Open the file
            try {
                Scanner dataFileLine = new Scanner(dataFile); // Create object to read the file
                counter = 0;
                while (dataFileLine.hasNextLine()) { // While there are lines to read
                    dataFileLine.nextLine(); // Else read the next line
                    counter++; // Increase Counter
                }
                dataFileLine.close(); // Close the object to read the file
                return counter; // Return the counter
            } catch (NoSuchElementException e) {
                // Catch the empty file error and print the message
                msgBox("The file has no data inside.", "File Is Empty", -1);
                // Print the details to the terminal
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // Catch the file not found error and print the message
            msgBox("The data file is missing!", "File Not Found", 0);
            // Print the details to the terminal
            e.printStackTrace();
        }
        return counter; // Return the counter
    }

    public static String[] splitData(String inputLine) {
        String[] dataArr = inputLine.split(DELIMITER); // Split each line into arrays with delimiter
        return dataArr;
    }

    public static void setLine(String data, String file) {
        try {
            Files.write(Paths.get(file), data.getBytes(), StandardOpenOption.APPEND); // Write new line to the file
            msgBox("Successfully wrote to the file.", "File Write Succesful", 1); // Display succesful message
            fileClean(file);// Clean up the file after writing to remove any empty lines
        } catch (IOException e) {
            msgBox("Unable to write to the file.", "An Error Occured", 0); // Display error message
            // Print the details to the terminal
            e.printStackTrace();
        }

    }

    public static void fileClean(String file) {
        try {
            File dataFile = new File(file); // Open the file
            FileWriter write = new FileWriter("dummy.txt"); // Open file

            Scanner dataFileLine = new Scanner(dataFile); // Create object to read the file
            // String data = dataFileLine.nextLine(); // Read the file line into the object
            while (dataFileLine.hasNext()) { // While there are lines to read
                String line = dataFileLine.nextLine(); // Read the next linie
                if (!line.isEmpty()) {
                    write.write(line); // Write the line to the dummy
                    if (dataFileLine.hasNextLine()) { // Check if this is the last line
                        write.write("\n"); // Write CRLF to the dummy if it is not the last line
                    }
                }
            }
            write.write("\n"); // Create new empty line at the EOF
            write.close(); // Close the file
            dataFileLine.close(); // Close the object to read the file

            File file1 = new File(file); // Open the original file as object
            File file2 = new File("dummy.txt"); // Open the dummy file as an object
            file1.delete(); // Delete the original file
            file2.renameTo(file1); // Rename dummy to original file name
            return;
        } catch (IOException e) {
            msgBox("IO Exception Error Occured.", "An Error Occured", 0); // Display error message
            // Print the details to the terminal
            e.printStackTrace();
        }
    }

    public static void fileCreate(String file) {
        try {
            File newFile = new File(file);
            newFile.createNewFile(); // Create the file
        } catch (IOException e) {
            msgBox("An error occurred.", "Unable To Create File", 0); // Error message
            e.printStackTrace(); // Show stack trace
        }
    }
    // endregion FILE HANDLING
}