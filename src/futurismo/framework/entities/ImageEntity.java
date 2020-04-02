package futurismo.framework.entities;

import futurismo.framework.Frame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class ImageEntity extends Entity{
    public enum TrailType{
        COLORTRAIL, IMAGETRAIL, LINETRAIL, DOUBLE, TRIPLE
    }
    protected BufferedImage image;
    protected Color color;
    protected TrailType trailType;
    protected int width, height, frames, speed, lineLength,
            trailFrequency, distanceFromFirst, minDistFromScreen;
    public ImageEntity(int x, int y, String path, TrailType trailType) {
        super(x, y);
        this.trailType = trailType;
        frames = 0;
        speed = 1;
        lineLength = 100;
        trailFrequency = 0;
        distanceFromFirst = 50;
        minDistFromScreen = 120;
        color = new Color(
                170,
                170,
                170,
                5);
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
            width = 100;
            height = 50;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tick() {

        if (posX + width <= Frame.SCALED_WIDTH - minDistFromScreen) {
            posX+=speed;
            frames++;
            if (frames > trailFrequency) {
                addTrailPosition(posX, posY);

                if (trail.size() >= trailSize) {
                    removeFirst();
                }
                frames = 0;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (trailType == TrailType.LINETRAIL) renderLineTrail(g);
        if (trailType == TrailType.COLORTRAIL) renderColorTrail(g);
        if (trailType == TrailType.IMAGETRAIL) renderImageTrail(g, image);
        if (trailType == TrailType.DOUBLE) {
            renderColorTrail(g);
            renderLineTrail(g);
        }
        if (trailType == TrailType.TRIPLE) {
            renderColorTrail(g);
            renderLineTrail(g);
            renderImageTrail(g, image);
        }


        g.drawImage(image, posX, posY, width, height, null);
    }

    protected void renderColorTrail(Graphics g) {
        g.setColor(color.brighter().brighter());
        for (Integer[] pos : trail) {
            g.fillRect(pos[0]-height, pos[1], width, height+10);
        }
    }
    protected void renderImageTrail(Graphics g, BufferedImage img) {
        for (Integer[] pos : trail) {
            g.drawImage(img, pos[0]-distanceFromFirst, pos[1], width, height, null);
        }
    }
    protected void renderLineTrail(Graphics g) {
        g.setColor(color);
        int rand1, rand2, rand3, rand4, rand5, rand6, rand7, rand8;
        rand1 = new Random().nextInt(50);
        rand2 = new Random().nextInt(50);
        rand3 = new Random().nextInt(50);
        rand4 = new Random().nextInt(50);
        rand5 = new Random().nextInt(100);
        rand6 = new Random().nextInt(100);
        rand7 = new Random().nextInt(100);
        rand8 = new Random().nextInt(100);

        g.drawLine(posX - rand8, posY+rand1, posX + rand1, posY + rand1);
        g.drawLine(posX - rand7, posY+rand2, posX + rand3, posY+rand2);
        g.drawLine(posX - rand6, posY+rand3, posX + rand2, posY+rand3);
        g.drawLine(posX - rand5, posY+rand4, posX + rand4, posY+rand4);

    }

    public void setMinDistFromScreen(int distance) {
        minDistFromScreen = distance;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setColor(Color c) {
        color = c;
    }
    public void setTrailFrequency(int frequency) {
        trailFrequency = frequency;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setTrailType(TrailType t) {
        trailType = t;
    }
    public void setTrailInterval(int interval) {
        distanceFromFirst = interval;
    }
    public void setImage(String s) {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getWidth() {
        return width;
    }
}
