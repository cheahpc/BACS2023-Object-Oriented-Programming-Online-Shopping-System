import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadDelimited {
    public static void test(String[] args) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("account.txt"));

            // Check if there is another line of input
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                // parse each line using delimiter
                parseData(str);
            }
        } catch (IOException exp) {
            exp.printStackTrace();
        } finally {
            if (sc != null)
                sc.close();
        }
    }

    private static void parseData(String str) {
        String acctFrom, acctTo, amount, val4, val5, val6;
        Scanner lineScanner = new Scanner(str);
        lineScanner.useDelimiter(",");
        while (lineScanner.hasNext()) {
            acctFrom = lineScanner.next();
            acctTo = lineScanner.next();
            amount = lineScanner.next();
            val4 = lineScanner.next();
            val5 = lineScanner.next();
            val6 = lineScanner.next();
            System.out.println("Account From- " + acctFrom + " Account To- " + acctTo +
                    " Amount- " + amount + "val4- " + val4 + "val5- " + val5 + "val6- " + val6);
        }
        lineScanner.close();
    }
}