public class TrafficLightThread extends Thread {
    private TrafficPanel trafficPanel;

    public TrafficLightThread(TrafficPanel trafficPanel) {
        this.trafficPanel = trafficPanel;
    }

    public void run() {
        try {
            while (true) {
                // Semafor roșu
                trafficPanel.setTrafficLightColor(TrafficLightColor.RED);
                sleep(2000); // 2 secunde

                // Semafor galben
                trafficPanel.setTrafficLightColor(TrafficLightColor.YELLOW);
                sleep(1000); // 1 secundă

                // Semafor verde
                trafficPanel.setTrafficLightColor(TrafficLightColor.GREEN);
                synchronized (trafficPanel) {
                    trafficPanel.notifyAll(); // Notificăm toate mașinile
                }
                sleep(2000); // 2 secunde

                // Reîntoarcerea la roșu
                trafficPanel.setTrafficLightColor(TrafficLightColor.RED);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
