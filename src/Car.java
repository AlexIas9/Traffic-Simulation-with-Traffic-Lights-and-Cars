import java.awt.Color;
import java.awt.Graphics;

public class Car extends Thread {
    private String name;
    private int distance = 0;
    private TrafficPanel trafficPanel;

    public Car(String name, TrafficPanel trafficPanel) {
        this.name = name;
        this.trafficPanel = trafficPanel;
        setName(name); // setăm numele thread-ului
    }

    public void run() {
        while (distance < 800) { // 800 este lățimea panoului
            synchronized (trafficPanel) {
                // Așteptăm până când semaforul este verde
                while (trafficPanel.getTrafficLightColor() != TrafficLightColor.GREEN) {
                    try {
                        trafficPanel.wait(); // Așteptăm în timp ce semaforul nu este verde
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            // Simulăm mașina mișcându-se la o viteză aleatorie
            int speed = (int) (Math.random() * 10) + 1;
            distance += speed;

            trafficPanel.updateCarPosition(name, distance);

            try {
                // Așteptăm un moment pentru a simula timpul
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        trafficPanel.removeCar(this);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE); // Culorile pot fi personalizate
        g.fillRect(distance, 200, 30, 30); // Schimbat la patrat
        g.setColor(Color.BLACK);
        g.drawString(name, distance, 195);
    }
}
