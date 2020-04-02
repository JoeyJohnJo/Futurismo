package futurismo.framework.states;

import futurismo.framework.Frame;
import futurismo.framework.entities.Entity;
import futurismo.framework.entities.ImageEntity;
import futurismo.framework.entities.Rocket;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayingState extends State {
    private ImageEntity train;
    private Rocket rocket;
    private boolean trainChanged = false;
    private ImageEntity plane1, plane2, plane3;
    private ImageEntity car, car2, car3, car5, car6, car7;
    private BufferedImage background1;
    private BufferedImage background2;
    private int x = 0;
    int portalX = Frame.SCALED_WIDTH - 10;
    private int frames = 0;

    public PlayingState(StateMachine stateMachine) {
        super("Playing State", stateMachine);
        train = new ImageEntity(10, Frame.SCALED_HEIGHT - 110,
                "/res/train.png", ImageEntity.TrailType.TRIPLE);
        rocket = new Rocket(Frame.SCALED_WIDTH + 1, 200);
        initCar1();
        initCar2();
        initCar3();

        initCar5();
        initCar6();
        initCar7();
        initPlane1();
        initPlane2();
        initPlane3();

        try {
            background1 = ImageIO.read(getClass().getResourceAsStream("/res/background.png"));
            background2 = ImageIO.read(getClass().getResourceAsStream("/res/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(background1, x, 0, Frame.SCALED_WIDTH, Frame.SCALED_HEIGHT, null);
        g.drawImage(background2, x+Frame.SCALED_WIDTH, 0,
                Frame.SCALED_WIDTH, Frame.SCALED_HEIGHT, null);
        for (Entity e : Entity.entities)
            e.render(g);

        if (frames > 5400) {
            g.setColor(new Color(0,255,0,150));
            g.fillRect( portalX ,Frame.SCALED_HEIGHT - 110, 10, 50);
        }
        rocket.render(g);
    }

    @Override
    public void tick() {
        frames++;
        if (frames > 5400 ) {
            portalX--;

        }
        if (!trainChanged) {
            if (train.getPosX()  + train.getWidth() > portalX) {
                train.setImage("/res/train2.png");
                train.setWidth(250);
                train.setHeight(40);
                train.setPosY(Frame.SCALED_HEIGHT - 100);
                train.setTrailType(ImageEntity.TrailType.COLORTRAIL);
                trainChanged = true;
            }
        }
        if (frames < 9000) {
            x--;
            if (this.x <= -Frame.SCALED_WIDTH) {
                this.x = this.x + Frame.SCALED_WIDTH;
            }
        }
        else {
            train.setMinDistFromScreen(-500);
            car7.setMinDistFromScreen(-500);

            if (car7.getPosX() > Frame.SCALED_WIDTH + 20) {
                if (!(rocket.x <= Frame.SCALED_WIDTH - 500)) {rocket.x -= 1;}
                if (rocket.x <= Frame.SCALED_WIDTH - 500)
                    rocket.launch();
            }
        }
        for (Entity e : Entity.entities)
            e.tick();
    }

    private void initCar1() {
        car = new ImageEntity(-1500, Frame.SCALED_HEIGHT - 90,
                "/res/car1.png", ImageEntity.TrailType.IMAGETRAIL);
        car.setMinDistFromScreen(-500);
        car.setTrailInterval(20);
        car.setTrailFrequency(50);
        car.setTrailSize(5);
    }
    private void initCar2() {
        car2 = new ImageEntity(-2500, Frame.SCALED_HEIGHT - 90,
                "/res/car2.png", ImageEntity.TrailType.IMAGETRAIL);
        car2.setMinDistFromScreen(-500);
        car2.setTrailInterval(20);
        car2.setTrailFrequency(50);
        car2.setTrailSize(3);
    }
    private void initCar3() {
        car3 = new ImageEntity(-3500, Frame.SCALED_HEIGHT - 90,
                "/res/car3.png", ImageEntity.TrailType.LINETRAIL);
        car3.setColor(new Color(68, 128, 201, 5));
        car3.setMinDistFromScreen(-500);
    }
    private void initCar5() {
        car5 = new ImageEntity(-4500, Frame.SCALED_HEIGHT - 90,
                "/res/car5.png", ImageEntity.TrailType.COLORTRAIL);
        car5.setColor(new Color(68, 128, 201, 5));
        car5.setMinDistFromScreen(-500);
    }
    private void initCar6() {
        car6 = new ImageEntity(-11000, Frame.SCALED_HEIGHT - 90,
                "/res/car6.png", ImageEntity.TrailType.LINETRAIL);
        car6.setColor(Color.GREEN);
        car6.setSpeed(2);
        car6.setMinDistFromScreen(-500);
        car6.setTrailInterval(20);
        car6.setTrailFrequency(40);
        car6.setTrailSize(5);
    }
    private void initCar7() {
        car7 = new ImageEntity(-6500, Frame.SCALED_HEIGHT - 90,
                "/res/car7.png", ImageEntity.TrailType.TRIPLE);
        car7.setMinDistFromScreen(450);
        car7.setColor(new Color(255, 126, 107, 2));
    }

    private void initPlane1() {
        plane1 = new ImageEntity(-3000, 10, "/res/plane1.png", ImageEntity.TrailType.COLORTRAIL);
        plane1.setColor(new Color(15,0, 255, 5));
        plane1.setMinDistFromScreen(-500);
    }
    private void initPlane2() {
        plane2 = new ImageEntity(-7000, 10, "/res/plane2.png", ImageEntity.TrailType.LINETRAIL);
        plane2.setMinDistFromScreen(-500);
        plane2.setColor(Color.WHITE);

    }
    private void initPlane3() {
        plane3 = new ImageEntity(-15000, 10, "/res/plane3.png", ImageEntity.TrailType.TRIPLE);
        plane3.setMinDistFromScreen(-500);
        plane3.setWidth(80);
        plane3.setHeight(50);
        plane3.setSpeed(3);
    }
}
