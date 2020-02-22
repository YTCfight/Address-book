package shootgame;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImage;

/**
 * 英雄机类
 * @author long
 *
 */
public class Hero extends FlyingObject{
    BufferedImage[] images; //图片数组
    private int index; //图片数组索引
    private int life; //命
    private int doubleFire; //双倍火力

    public Hero(){
        this.image = Constant.hero0;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.x = 150;
        this.y = 400;

        this.life = 3;
        this.doubleFire = 20;
        this.index = 0;
        this.images = new BufferedImage[]{Constant.hero0,Constant.hero1};
    }

    /**
     * 获取生命值
     * @return
     */
    public int getLife() {
        return life;
    }

    /**
     * 增加一条命
     */
    public void addLife(){
        life++;
    }

    /**
     * 减一条命
     */
    public void substractLife(){
        life--;
    }

    /**
     * 设置双倍火力
     * @param doubleFire
     */
    public void setDoubleFire(int doubleFire) {
        this.doubleFire = doubleFire;
    }

    public void addDoubleFire(){
        doubleFire += 40;
    }
    /**
     * 重写走步方法
     * 英雄机走步即切换图片，实现动画效果
     */
    public void step(){
        image = images[index++/10%images.length];
    }

    /**
     * 英雄机射出子弹
     * @return
     */
    public Bullet[] shoot(){
        Bullet[] b;
        int temp = this.width/4;
        if(doubleFire>0){
            b = new Bullet[2];
            b[0] = new Bullet(this.x+1*temp-1,this.y-10);
            b[1] = new Bullet(this.x+3*temp-1,this.y-10);
            doubleFire -= 2;
        }else{
            b = new Bullet[1];
            b[0] = new Bullet(this.x+2*temp-1,this.y-10);
        }
        return b;
    }

    /**
     * 英雄机随鼠标移动方法
     * @param x
     * @param y
     */
    public void move(int x,int y){
        this.x = x-this.width/2;
        this.y = y-this.height/2;
    }

    /**
     * 英雄机永不越界
     */
    public boolean outOfBounds(){
        return false;
    }

    public boolean hit(FlyingObject f){
        int x1 = f.x-this.width/2;
        int x2 = f.x+f.width+this.width/2;
        int y1 = f.y-this.height/2;
        int y2 = f.y+f.height+this.height/2;
        int x = this.x+this.width/2;
        int y = this.y+this.height/2;
        return (x>x1&&x<x2)&&(y>y1&&y<y2);
    }
}

