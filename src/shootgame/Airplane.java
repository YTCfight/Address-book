package shootgame;

import java.util.Random;

/**
 * 敌机类
 * @author long
 *
 */
public class Airplane extends FlyingObject implements Enemy{
    private int speed = 3; //飞行速度

    public Airplane(){
        this.image = Constant.airplane;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.y = -height;
        Random rand = new Random();
        this.x = rand.nextInt(Constant.WIDTH-this.width);
        rand = null;
    }

    /**
     * 打掉一个敌机获得的分数
     */
    public int getScore(){
        return 5;
    }

    /**
     * 重写走步方法
     * 向下走一步
     */
    public void step(){
        y += speed;
    }

    /**
     * 重写边界检测方法
     * 当敌机到到最小面的时候越界
     */
    public boolean outOfBounds(){
        return y>Constant.HEIGHT;
    }
}

