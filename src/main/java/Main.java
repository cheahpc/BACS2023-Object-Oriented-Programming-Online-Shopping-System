import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.NoSuchElementException;
import java.util.Scanner; // Import the Scanner class to read text files
import javax.swing.*;

public class Main {
    public static final String DELIMITER = ":";
    public static String file;
    public static String dataLine;

    public static void main(String[] args) {

        int dumm;
        dumm = 1;
        String newVar = findLine("Hashim Palmer", "account.txt");
        // msgBox("message", splitData(newVar)[0], -1);
        // msgBox("message", splitData(newVar)[1], 0);
        // msgBox("message", splitData(newVar)[2], 1);
        // msgBox("message", splitData(newVar)[3], 2);
        // msgBox("message", splitData(newVar)[4], 3);

        echo(String.format("This is something %s\n This is the integer: %d", newVar, dumm));

        // System.out.println(JOptionPane.OK_CANCEL_OPTION);

        // DataFile accountFile = new DataFile("account.txt");

        // System.out.println(accountFile.findLine("Hashim Palmer"));
        // ! User, Cart, Item, Order Detail
    }

    // region OUTPUT
    public static void msgBox(String message, String title, int optionType) {
        // PLAIN_MESSAGE = -1
        // ERROR_MESSAGE = 0
        // INFORMATION_MESSAGE = 1
        // WARNING_MESSAGE = 2
        // QUESTION_MESSAGE = 3
        JOptionPane.showMessageDialog(null, message, title, optionType);
    }

    public static void echo(String message) {
        System.out.println(message);
    }

  
    // endregion OUTPUT

    public static String findLine(String keyWord, String file) {

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
                msgBox("No matching record found.", "Record Does Not Exist", -1); // Show the message
                return "";
            } catch (NoSuchElementException e) {
                // Catch the empty file error and print the message
                msgBox("The file has no data inside.", "File Is Empty", -1);
                // Print he details to the termianl
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // Catch the file not found error and print the message
            msgBox("The data file is missing!", "File Not Found", 0);
            // Print he details to the termianl
            e.printStackTrace();
        }
        return "";
    }

    public static String[] splitData(String inputLine) {
        String[] dataArr = inputLine.split(DELIMITER); // Split each line into arrays with delimiter
        return dataArr;
    }

}