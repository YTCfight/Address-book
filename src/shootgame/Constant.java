package shootgame;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 常量类
 * @author long
 *
 */
public class Constant {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 650;

    public static BufferedImage background;
    public static BufferedImage start;
    public static BufferedImage pause;
    public static BufferedImage gameover;
    public static BufferedImage airplane;
    public static BufferedImage bee;
    public static BufferedImage bullet;
    public static BufferedImage hero0;
    public static BufferedImage hero1;

    public static final int START = 0;
    public static final int PAUSE = 1;
    public static final int RUNNING = 2;
    public static final int GAME_OVER = 3;

    static {
        try {
            background=ImageIO.read(Constant.class.getResource("background.png"));
            start=ImageIO.read(Constant.class.getResource("start.png"));
            pause=ImageIO.read(Constant.class.getResource("pause.png"));
            gameover=ImageIO.read(Constant.class.getResource("gameover.png"));
            airplane=ImageIO.read(Constant.class.getResource("airplane.png"));
            bee=ImageIO.read(Constant.class.getResource("bee.png"));
            bullet=ImageIO.read(Constant.class.getResource("bullet.png"));
            hero0=ImageIO.read(Constant.class.getResource("hero0.png"));
            hero1=ImageIO.read(Constant.class.getResource("hero1.png"));
        } catch (IOException e) {
            System.out.println("图片加载异常");
            e.printStackTrace();
        }
    }

}

