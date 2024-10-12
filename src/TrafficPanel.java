import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class TrafficPanel extends JPanel {
    private TrafficLightColor trafficLightColor;
    private List<Car> cars;

    public TrafficPanel() {
        setPreferredSize(new Dimension(800, 400));
        trafficLightColor = TrafficLightColor.RED;
        cars = new ArrayList<>();

        // Adăugăm mașini inițiale
        for (int i = 0; i < 5; i++) {
            cars.add(new Car("Car " + (i + 1), this));
        }

        // Pornim mașinile
        for (Car car : cars) {
            car.start();
        }

        // Pornim semaforul
        new TrafficLightThread(this).start();
    }

    public void setTrafficLightColor(TrafficLightColor color) {
        this.trafficLightColor = color;
        repaint();
    }

    public TrafficLightColor getTrafficLightColor() {
        return trafficLightColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTrafficLight(g);

        // Desenăm mașinile
        for (Car car : cars) {
            car.draw(g);
        }
    }

    private void drawTrafficLight(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(50, 50, 50, 150);

        g.setColor(trafficLightColor == TrafficLightColor.RED ? Color.RED : Color.GRAY);
        g.fillOval(55, 60, 40, 40);

        g.setColor(trafficLightColor == TrafficLightColor.YELLOW ? Color.YELLOW : Color.GRAY);
        g.fillOval(55, 110, 40, 40);

        g.setColor(trafficLightColor == TrafficLightColor.GREEN ? Color.GREEN : Color.GRAY);
        g.fillOval(55, 160, 40, 40);
    }

    public void removeCar(Car car) {
        cars.remove(car);

        // Așteptăm un moment pentru a permite utilizatorului să observe că mașina a
        // dispărut
        try {
            Thread.sleep(500); // 0.5 secunde
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Adăugăm o nouă mașină când una este eliminată
        cars.add(new Car("Car " + (cars.size() + 1), this));
    }

    public void updateCarPosition(String carName, int distance) {
        repaint(); // Re-desenăm panoul
    }

}
