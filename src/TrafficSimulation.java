import javax.swing.JFrame;

public class TrafficSimulation {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Traffic Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TrafficPanel trafficPanel = new TrafficPanel();

        frame.getContentPane().add(trafficPanel);
        frame.pack();
        frame.setVisible(true);

        // Start traffic control in a new thread
        TrafficController controller = new TrafficController(trafficPanel);
        controller.start(); // Start the traffic controller thread
    }
}
