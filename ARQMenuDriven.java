import java.util.Scanner;

public class ARQMenuDriven {
    public static void stopAndWaitARQ(Scanner sc) {
        System.out.print("Enter number of frames: ");
        int n = sc.nextInt();
        int i = 0;
        while (i < n) {
            System.out.println("Sending frame " + i + "...");
            System.out.print("Was frame " + i + " received correctly? (yes/no): ");
            String response = sc.next();
            if (response.equalsIgnoreCase("yes")) {
                System.out.println("ACK received for frame " + i + "\n");
                i++;
            } else {
                System.out.println("NAK received. Resending frame " + i + "\n");
            }
        }
    }

    public static void goBackNARQ(Scanner sc) {
        System.out.print("Enter total frames: ");
        int total = sc.nextInt();
        System.out.print("Enter window size: ");
        int window = sc.nextInt();

        int i = 0;
        while (i < total) {
            int end = Math.min(i + window, total);
            System.out.println("\nSending frames " + i + " to " + (end - 1));

            boolean errorOccurred = false;
            for (int j = i; j < end; j++) {
                System.out.print("Was frame " + j + " received correctly? (yes/no): ");
                String res = sc.next();
                if (res.equalsIgnoreCase("no")) {
                    System.out.println("NAK for frame " + j + ". Go back to frame " + j);
                    i = j;
                    errorOccurred = true;
                    break;
                }
            }

            if (!errorOccurred) {
                System.out.println("All frames in window acknowledged.");
                i = end;
            }
        }
    }

    public static void selectiveRepeatARQ(Scanner sc) {
        System.out.print("Enter total frames: ");
        int total = sc.nextInt();
        System.out.print("Enter window size: ");
        int window = sc.nextInt();
        boolean[] received = new boolean[total];

        int base = 0;
        while (base < total) {
            int end = Math.min(base + window, total);
            for (int i = base; i < end; i++) {
                if (!received[i]) {
                    System.out.println("Sending frame " + i + "...");
                    System.out.print("Was frame " + i + " received correctly? (yes/no): ");
                    String res = sc.next();
                    if (res.equalsIgnoreCase("yes")) {
                        System.out.println("ACK received for frame " + i);
                        received[i] = true;
                    } else {
                        System.out.println("NAK received. Will resend frame " + i + " later.");
                    }
                }
            }

            while (base < total && received[base]) {
                base++;
            }
            System.out.println();
        }

        System.out.println("All frames sent and acknowledged.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- ARQ Protocols ---");
            System.out.println("1. Stop-and-Wait ARQ");
            System.out.println("2. Go-Back-N ARQ");
            System.out.println("3. Selective Repeat ARQ");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    stopAndWaitARQ(sc);
                    break;
                case 2:
                    goBackNARQ(sc);
                    break;
                case 3:
                    selectiveRepeatARQ(sc);
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }

        } while (choice != 4);
        sc.close();
    }
}
