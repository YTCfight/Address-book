package shootgame;

import java.util.Random;

/**
 * 小蜜蜂类
 * @author long
 *
 */
public class Bee extends FlyingObject implements Award{
    private int xSpeed = 2; //水平方向移动速度
    private int ySpeed = 3; //垂直方向移动速度
    private int awardType; //奖励类型

    public Bee(){
        this.image = Constant.bee;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.y = -height;
        Random rand = new Random();
        this.x = rand.nextInt(Constant.WIDTH-this.width);
        this.awardType = rand.nextInt(2);

        rand = null;
    }

    /**
     * 打死一只小蜜蜂可以获取的奖励类型
     * 生命或值双倍子弹
     */
    public int getType(){
        return awardType;
    }

    /**
     * 重写走步方法
     *小蜜蜂可以左右走
     * 到最左边或最右边的时候转向
     */
    public void step(){
        x += xSpeed;
        y += ySpeed;
        if(x>Constant.WIDTH-this.width){
            xSpeed = -1;
        }
        if(x<0){
            xSpeed = 1;
        }
    }

    /**
     * 小蜜蜂边界检测
     */
    public boolean outOfBounds(){
        return y>Constant.HEIGHT;
    }

}
