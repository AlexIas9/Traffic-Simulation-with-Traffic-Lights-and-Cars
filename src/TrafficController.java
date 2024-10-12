public class TrafficController extends Thread {
    private TrafficPanel trafficPanel;

    public TrafficController(TrafficPanel trafficPanel) {
        this.trafficPanel = trafficPanel;
    }

    public void run() {
        try {
            while (true) {
                // Change traffic light states
                trafficPanel.setTrafficLightColor(TrafficLightColor.RED);
                Thread.sleep(5000); // Red light for 5 seconds
                trafficPanel.setTrafficLightColor(TrafficLightColor.GREEN);
                Thread.sleep(5000); // Green light for 5 seconds
                trafficPanel.setTrafficLightColor(TrafficLightColor.YELLOW);
                Thread.sleep(2000); // Yellow light for 2 seconds
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
