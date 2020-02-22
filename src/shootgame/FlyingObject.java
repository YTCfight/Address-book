package shootgame;

import java.awt.image.BufferedImage;

/**
 * 飞行物类
 * @author long
 *
 */
public abstract class FlyingObject {

    protected BufferedImage image; //图片
    protected int x; //x坐标
    protected int y; //y坐标
    protected int width; //图片宽度
    protected int height; //图片高度

    public abstract void step(); //走步方法

    public abstract boolean outOfBounds();

    public boolean shootBy(Bullet b){
        int x1 = this.x - b.width;
        int x2 = this.x + this.width;
        int y1 = this.y;
        int y2 = this.y + this.height;
        int x = b.x;
        int y = b.y;
        return (x > x1 && x < x2) && (y > y1 && y < y2);
    }

}
