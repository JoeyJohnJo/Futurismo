package futurismo.framework.entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Rocket {
    public int x, y, width, height;
    private BufferedImage image;
    public Rocket(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/rocketStationary.png"));
            width = 30;
            height = 150;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launch() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/rocket.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        y--;
    }

    public void render(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
}
