import java.util.concurrent.TimeUnit;

public class circuitSwitching {
    public static void main(String[] args) {
        System.out.println("Simulating Circuit Switching:");
        try {

            System.out.println("Establishing circuit between Sender and Receiver...");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Circuit established. Dedicated path reserved.");
            System.out.println("Sending data: 'Hello, Circuit Switching!'");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("Data sent successfully.");
            System.out.println("Releasing the dedicated circuit...");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Circuit released. Path now available for others.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Simulation interrupted.");
        }
    }
}
