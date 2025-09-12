import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class packetSwitching {
    static class Packet {
        int id;
        String data;
        String destination;

        public Packet(int id, String data, String destination) {
            this.id = id;
            this.data = data;
            this.destination = destination;
        }

        @Override
        public String toString() {
            return "Packet{" + "id=" + id + ", data='" + data + "', destination='" + destination + "'}";
        }
    }

    public static void main(String[] args) {
        System.out.println("Simulating Packet Switching:");
        String message = "This is a message for packet switching demonstration.";
        String destination = "ReceiverA";
        List<Packet> packets = new ArrayList<>();
        int packetSize = 10;
        for (int i = 0; i < message.length(); i += packetSize) {
            int endIndex = Math.min(i + packetSize, message.length());
            String packetData = message.substring(i, endIndex);
            packets.add(new Packet(i / packetSize, packetData, destination));
        }
        System.out.println("Message broken into packets: " + packets.size());

        Random random = new Random();
        for (Packet packet : packets) {
            try {

                int delay = random.nextInt(3) + 1;
                TimeUnit.SECONDS.sleep(delay);
                System.out.println(
                        "Packet " + packet.id + " arrived at intermediate node (simulated delay: " + delay + "s)");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Simulation interrupted.");
            }
        }
        System.out.println("All packets arrived at destination. Reassembling message...");
        StringBuilder reassembledMessage = new StringBuilder();
        for (Packet packet : packets) {
            reassembledMessage.append(packet.data);
        }
        System.out.println("Reassembled message: '" + reassembledMessage.toString() + "'");
    }
}
