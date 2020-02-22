package shootgame;

import java.util.Random;

/**
 * 子弹类
 * @author long
 *
 */
public class Bullet extends FlyingObject{
    private int speed = 5; //�ӵ����ٶ�

    public Bullet(int x,int y){
        this.image = Constant.bullet;
        this.width = image.getWidth();
        this.height = image.getHeight();

        this.x = x;
        this.y = y;
    }

    /**
     * 重写 step 方法
     * 子弹向上走步
     */
    public void step(){
        y -= speed;
    }

    /**
     * 子弹边界检测
     * 当子弹到到最上边的时候越界
     */
    public boolean outOfBounds(){
        return y<-this.width;
    }
}
