package futurismo.framework.entities;

import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Entity {
    protected ArrayList<Integer[]> trail;
    public static ArrayList<Entity> entities = new ArrayList<>();
    protected int posX, posY, trailSize;
    public Entity(int x, int y) {
        entities.add(this);
        posX = x;
        posY = y;
        trail = new ArrayList<>();
        trailSize = 100;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public void addTrailPosition(int x, int y) {
        if (trail.size() < trailSize) {
            trail.add(new Integer[] {x, y});
        }
        else {
            System.out.println("The trail is already at max length (" + trailSize +
                    "). Try resizing the trail length using setTrailSize(int size)");
        }
    }
    public void removeFirst() {
        trail.remove(0);
    }
    public void setTrailSize(int size) {
        trailSize = size;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
