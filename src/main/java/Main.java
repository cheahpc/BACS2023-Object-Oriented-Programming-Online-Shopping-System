import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle errors
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner; // Import the Scanner class to read text files
import javax.swing.*;

import java.util.regex.*;

public class Main {
    public static final String DELIMITER = ":";
    public static final String ACCOUNT_FILE = "account.txt";
    public static final String GUEST_FILE = "guest.txt";
    public static String userInput;
    public static String[] tempStrings = new String[10];
    public static int option;
    public static Scanner sc = new Scanner(System.in);
    public static boolean loopVal = true;

    public static void main(String[] args) {
        // int dumm;
        // dumm = 1;
        // String newVar = getLine("BZV87HWK2KI", "account.txt");
        // msgBox("message", splitData(newVar)[0], -1);
        // msgBox("message", splitData(newVar)[1], 0);
        // msgBox("message", splitData(newVar)[2], 1);
        // msgBox("message", splitData(newVar)[3], 2);
        // msgBox("message", splitData(newVar)[4], 3);

        do {
            switch (getOption(0)) { // Login Interface
                case 1: // User Login
                    loopVal = runUserLogin();
                    break;
                case 2: // Create User Account
                    loopVal = runCreateAccount();
                    break;
                case 3: // Login as Guest
                    loopVal = runGuestLogin();
                    break;
                case 4: // Register New Guest
                    loopVal = runGuestSignUp();
                    break;
                case 5:
                    loopVal = false;
                    break;
                case 6:
                    runExitProgram();
                    break;
                default:
                    break;
            }
        } while (loopVal);

    }

    // region MENU FUNCTION
    public static boolean runUserLogin() {
        UserLogin: do {
            switch (getOption(1)) {
                case 1: // User Choose 1 - User Name
                    tempStrings[0] = getInput(1, 1); // Enter UserName
                    // If user name is not found
                    if (getLine(tempStrings[0], ACCOUNT_FILE) == "") {
                        // Print user not found message
                        msgBox("Account not found with username: " + tempStrings[0],
                                "User Does Not Exist", 1);
                        tempStrings[0] = null; // Reset the temp var
                    }
                    break;
                case 2: // User Choose 2 - Password
                    if (tempStrings[0] == null) { // If no user name is empty
                        // Prompt user to input user name first
                        msgBox("Please input user name first.", "User Name Is Empty", 1);
                        continue UserLogin;
                    } else {
                        tempStrings[1] = getInput(1, 2); // Enter Password
                        // If password does not match
                        if (splitData(getLine(tempStrings[0], ACCOUNT_FILE))[1] != tempStrings[1]) {
                            // Print wrong password message
                            msgBox("Wrong password", "Password Incorrect", 0);
                            tempStrings[1] = null;
                        }
                    }
                    break;
                case 3: // User Choose 3 - Back
                    Arrays.fill(tempStrings, null); // Reset Array
                    return true; // Back to Main Menu
            }
        } while (tempStrings[0] == null || tempStrings[1] == null);
        return false; // Continue to next section
    }

    public static boolean runCreateAccount() {
        CreateAccount: do {
            switch (getOption(2)) {
                case 1: // Create New User Account - Set User name
                    tempStrings[0] = getInput(2, 1);
                    // If user name is found
                    if (getLine(tempStrings[0], ACCOUNT_FILE) != "") {
                        msgBox("User name " + tempStrings[0] + " already taken: ", "User Name Already Taken", 1);
                        tempStrings[0] = null; // Reset the temp var
                    }
                    break;
                case 2: // Create New User Account - Set Password
                    tempStrings[1] = getInput(2, 2);
                    break;
                case 3: // Create New User Account - Set Full name
                    tempStrings[2] = getInput(2, 3);
                    break;
                case 4: // Create New User Account - Set Contact number
                    tempStrings[3] = getInput(2, 4);
                    break;
                case 5: // Create New User Account - Set Email
                    tempStrings[5] = getInput(2, 5);
                    break;
                case 6: // Create New User Account - Set Address
                    tempStrings[6] = getInput(2, 6);
                    break;
                case 7: // back to previous menu
                    Arrays.fill(tempStrings, null); // Reset Array
                    return true; // Back to main menu
            }
            // Check for empty string,
            for (int i = 0; i < 7; i++) {
                if (tempStrings[i] == null) {
                    // Continue with this menu if there are empty field
                    continue CreateAccount;
                }
            }
            return false; // Continue to next section
        } while (true);
    }

    public static boolean runGuestLogin() {
        GuestLogin: do {
            switch (getOption(3)) {
                case 1: // Guest Loin - Set Guest ID
                    tempStrings[0] = getInput(3, 1); // Get the ID from user

                    if (getLine(tempStrings[0], GUEST_FILE) == "") { // If ID not found
                        msgBox("Guest ID not registered in System.", "Invalid Guest ID", 1);
                        tempStrings[0] = null; // Reset the temp var
                    }
                    break;
                case 2: // back to previous menu
                    tempStrings[0] = null; // Reset temporary var
                    return true; // Back to main menu
            }
            // Check for empty string,
            if (tempStrings[0] == null) {
                // Continue with this menu if there are empty field
                continue GuestLogin;
            }
            return false; // Continue to next section
        } while (true);
    }

    public static boolean runGuestSignUp() {
        GuestSignUp: do {
            switch (getOption(4)) {
                case 1: // Guest Sign Up - Set Full Name
                    tempStrings[0] = getInput(4, 1);
                    break;
                case 2: // Guest Sign Up - Set Contact Number
                    tempStrings[1] = getInput(4, 2);
                    break;
                case 3: // Guest Sign Up - Set Address
                    tempStrings[2] = getInput(4, 3);
                    break;
                case 4: // Guest Sign Up - Go Back to previous menu
                    Arrays.fill(tempStrings, null); // Reset Array
                    return true; // Back to main menu
            }

            // Check for empty string,
            for (int i = 0; i < 3; i++) {
                if (tempStrings[i] == null) {
                    // Continue with this menu if there are empty field
                    echo("testing 123 abc debug mode", true);
                    continue GuestSignUp;
                }
            }

            return false; // Continue to next section
        } while (true);
    }

    public static void runExitProgram() {
        msgBox("Thank you for you support!", "See You Again!", 1);
        System.exit(0);
    }
    // endregion MENU FUNCTION

    // region DISPLAY
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void interfaceDisplay(int level) {
        if (level < 10) {
            clearScreen();
        }
        switch (level) {
            case 0: // Main Menu
                echo("------------------------------------------------------------------", true);
                echo("               Welcome To Number 3 Shopping Center!               ", true);
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
                echo(">>    1.      User Name   : " + ((tempStrings[0] == null) ? "" : tempStrings[0]), true);
                echo(">>    2.      Password    : " + ((tempStrings[1] == null) ? "" : tempStrings[1]), true);
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
                echo(">>    1.      User Name       : " + ((tempStrings[0] == null) ? "" : tempStrings[0]), true);
                echo(">>    2.      Password        : " + ((tempStrings[1] == null) ? "" : tempStrings[1]), true);
                echo(">>    3.      Full Name       : " + ((tempStrings[2] == null) ? "" : tempStrings[2]), true);
                echo(">>    4.      Contact Number  : " + ((tempStrings[3] == null) ? "" : tempStrings[3]), true);
                echo(">>    5.      Email           : " + ((tempStrings[4] == null) ? "" : tempStrings[4]), true);
                echo(">>    6.      Address         : " + ((tempStrings[5] == null) ? "" : tempStrings[5]), true);
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
            case 3: // Continue As Guest
                echo("------------------------------------------------------------------", true);
                echo("                            Guest Login                           ", true);
                echo("------------------------------------------------------------------", true);
                echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                echo(">>    1.      Guest ID         : " + ((tempStrings[0] == null) ? "" : tempStrings[0]), true);
                echo(">>", true);
                echo(">>    2.      Back", true);
                echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                echo(">>", true);
                break;
            case 30:
                echo(">> Choose an option: ", false);
                break;
            case 31:
                echo(">> Please enter 1. GUEST ID: ", false);
                break;
            case 4: // Create a Guest
                echo("------------------------------------------------------------------", true);
                echo("                           Guest Sign Up                          ", true);
                echo("------------------------------------------------------------------", true);
                echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                // TODO Interface Design - Guest Sign up (generate Guest ID)
                echo(">>    ..      Guest ID        : " + "(function to get auto ID)", true);
                echo(">>    1.      Full Name       : " + ((tempStrings[0] == null) ? "" : tempStrings[0]), true);
                echo(">>    2.      Contact Number  : " + ((tempStrings[1] == null) ? "" : tempStrings[1]), true);
                echo(">>    3.      Address         : " + ((tempStrings[2] == null) ? "" : tempStrings[2]), true);
                echo(">>", true);
                echo(">>    4.      Back", true);
                echo(">> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", true);
                echo(">>", true);
                break;
            case 40:
                echo(">> Choose an option: ", false);
                break;
            case 41:
                echo(">> Please enter 1. FULL NAME: ", false);
                break;
            case 42:
                echo(">> Please enter 2. CONTACT NUBMER: ", false);
                break;
            case 43:
                echo(">> Please enter 3. ADDRESS: ", false);
                break;
            case 6: // Exit Program
                break;
            default:
                break;
        }
    }
    // endregion DISPLAY

    // region GET OPTION INPUT
    public static int getOption(int level) {
        do {
            interfaceDisplay(level);
            interfaceDisplay(level * 10);
            if (inputPatternCheck(level)) {
                break; // Break if input match
            } else {
                // Else show error messages
                msgBox("Input option denied.", "Invalid Input Option", 0);
            }
        } while (true);
        return Integer.parseInt(userInput); // Return user choice
    }

    public static String getInput(int level1, int level2) {
        String message = "", title = "";
        switch (level1) {
            case 1: // User Login
                switch (level2) {
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
                switch (level2) {
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
                switch (level2) {
                    case 1: // Guest Login - Set Guest ID
                        message = "Only 5 digits Guest ID accepted.\n eg. 02919";
                        title = "Invalid Guest ID";
                        break;
                    default:
                        break;
                }
                break;
            case 4: // Guest Sign Up
                switch (level2) {
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
                    default:
                        break;
                }
                break;
            default:
                break;
        }

        // Check input with pattern
        do {
            interfaceDisplay(level1);
            interfaceDisplay((level1 * 10) + level2);
            if (inputPatternCheck((level1 * 10) + level2)) {
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

    public static boolean inputPatternCheck(int inputID) {
        Pattern regExPat; // Create object for Regex Pattern
        Matcher matchVar; // Create object for Matching Regex
        userInput = ""; // Reset Input
        userInput = readIn(); // Read user input
        // Check Input ID
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
            case 2: //
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
                regExPat = Pattern.compile("[0-9]{5}");
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
        matchVar = regExPat.matcher(userInput);
        // Return true or false if matched or not matched
        return matchVar.matches();
    }

    // endregion INPUT

    // region OUTPUT
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
    public static String getLine(String keyWord, String file) {
        try {
            File dataFile = new File(file); // Open the file
            try {
                Scanner dataFileLine = new Scanner(dataFile); // Create object to read the file
                String data = dataFileLine.nextLine(); // Read the file line into the object
                while (dataFileLine.hasNextLine()) { // While there are lines to read
                    String[] dummy = data.split(":"); // Split each line into arrays with delimiter
                    if (dummy[0].equalsIgnoreCase(keyWord)) { // If the first element of the line match with the keyword
                        dataFileLine.close(); // close the object to read the file
                        return data; // Return the line that has matching keyword
                    }
                    data = dataFileLine.nextLine(); // Else read the next line
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

    public static String[] splitData(String inputLine) {
        String[] dataArr = inputLine.split(DELIMITER); // Split each line into arrays with delimiter
        return dataArr;
    }

    public static void setLine(String data, String file) {
        try {
            FileWriter writeAppend = new FileWriter(file); // Open file
            writeAppend.append("\n"); // Input a CRLF to the data file
            writeAppend.append(data); // Append in the data
            writeAppend.close(); // Close the file
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

    // endregion FILE HANDLING
}