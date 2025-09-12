import java.util.Scanner;

public class CRC {
    static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < b.length(); i++) {
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }

    static String divide(String dividend, String divisor) {
        int pick = divisor.length();
        String tmp = dividend.substring(0, pick);
        while (pick < dividend.length()) {
            if (tmp.charAt(0) == '1') {
                tmp = xor(divisor, tmp) + dividend.charAt(pick);
            } else {
                tmp = xor("0".repeat(pick), tmp) + dividend.charAt(pick);
            }
            pick++;
        }
        if (tmp.charAt(0) == '1') {
            tmp = xor(divisor, tmp);
        } else {
            tmp = xor("0".repeat(pick), tmp);
        }

        return tmp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Data: ");
        String data = sc.nextLine();
        System.out.print("Enter Generator (Divisor): ");
        String divisor = sc.nextLine();
        String appendedData = data + "0".repeat(divisor.length() - 1);
        String remainder = divide(appendedData, divisor);
        String transmittedData = data + remainder;
        System.out.println("CRC Remainder: " + remainder);
        System.out.println("Transmitted Data (Data + CRC): " + transmittedData);
        System.out.print("Enter Received Data: ");
        String received = sc.nextLine();
        String checkRemainder = divide(received, divisor);
        boolean errorFree = checkRemainder.equals("0".repeat(divisor.length() - 1));
        if (errorFree) {
            System.out.println("No Error Detected in Received Data.");
        } else {
            System.out.println("Error Detected in Received Data.");
        }

        sc.close();
    }
}
