import java.util.Scanner;

public class checkSum {
    static String addBinary(String a, String b) {
        int len = a.length();
        StringBuilder result = new StringBuilder();
        int carry = 0;
        for (int i = len - 1; i >= 0; i--) {
            int bit1 = a.charAt(i) - '0';
            int bit2 = b.charAt(i) - '0';
            int sum = bit1 + bit2 + carry;
            result.append(sum % 2);
            carry = sum / 2;
        }
        while (carry > 0) {
            for (int i = result.length() - 1; i >= 0 && carry > 0; i--) {
                int bit = result.charAt(i) - '0';
                int sum = bit + carry;
                result.setCharAt(i, (char) ((sum % 2) + '0'));
                carry = sum / 2;
            }
        }
        return result.reverse().toString();
    }

    static String onesComplement(String str) {
        StringBuilder comp = new StringBuilder();
        for (char c : str.toCharArray()) {
            comp.append(c == '0' ? '1' : '0');
        }
        return comp.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of data words: ");
        int n = sc.nextInt();
        System.out.print("Enter size of each word (in bits): ");
        int wordSize = sc.nextInt();
        sc.nextLine();
        String[] data = new String[n];
        System.out.println("Enter the data words:");
        for (int i = 0; i < n; i++) {
            data[i] = sc.nextLine();
        }
        String sum = data[0];
        for (int i = 1; i < n; i++) {
            sum = addBinary(sum, data[i]);
        }
        String checksum = onesComplement(sum);
        System.out.println("Checksum (Sender): " + checksum);
        System.out.println("\n--- Receiver Side ---");
        String[] received = new String[n + 1];
        System.out.println("Enter received data words (including checksum):");
        for (int i = 0; i < n + 1; i++) {
            received[i] = sc.nextLine();
        }

        String recvSum = received[0];
        for (int i = 1; i < received.length; i++) {
            recvSum = addBinary(recvSum, received[i]);
        }
        String result = onesComplement(recvSum);
        if (result.contains("1")) {
            System.out.println("Error detected in received data.");
        } else {
            System.out.println("No error detected. Data received correctly.");
        }
        sc.close();
    }
}
