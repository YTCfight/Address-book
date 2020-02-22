package shootgame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 游戏主界面类
 * @author long
 *
 */
public class ShootGame extends JPanel{
    private FlyingObject[] flyings = {}; //����������
    private Bullet[] bullets = {}; //�ӵ�����
    private Hero hero = new Hero(); //Ӣ�ۻ�

    private int score = 0; //�÷�

    private int state = 0; //��Ϸ״̬

    /**
     * 重写 paint 方法
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(Constant.background,0,0,null);
        paintHero(g);
        paintFlyings(g);
        paintBullets(g);
        paintScore(g);
        paintState(g);
    }

    /**
     * 游戏画面开始、运行、暂停、结束四种状态
     * @param g
     */
    public void paintState(Graphics g){
        switch (state) {
            case Constant.START:
                g.drawImage(Constant.start,0,0,null);
                break;
            case Constant.PAUSE:
                g.drawImage(Constant.pause,0,0,null);
                break;
            case Constant.GAME_OVER:
                g.drawImage(Constant.gameover,0,0,null);
                break;

            default:
                break;
        }
    }

    /**
     * 画分数和命
     * @param g
     */
    public void paintScore(Graphics g){
        Color c = g.getColor();
        g.setColor(new Color(0x8A2BE2));
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        g.drawString("SCORE:"+score,10,25);
        g.drawString("LIFE:"+hero.getLife(),10,50);
        g.setColor(c);
    }

    /**
     * 画英雄机
     * @param g
     */
    private void paintHero(Graphics g) {
        g.drawImage(hero.image,hero.x,hero.y,null);
    }

    /**
     * 画飞行物
     * @param g
     */
    private void paintFlyings(Graphics g) {
        for(int i=0;i<flyings.length;i++){
            FlyingObject f = flyings[i];
            g.drawImage(f.image,f.x,f.y,null);
        }
    }

    /**
     * 画子弹
     * @param g
     */
    private void paintBullets(Graphics g) {
        for(int i=0;i<bullets.length;i++){
            Bullet b = bullets[i];
            g.drawImage(b.image,b.x,b.y,null);
        }
    }

    /**
     * 飞行物入场方法
     * 每隔 400 ms 入场一次
     */
    private int enterIndex = 0;
    public void enterAction(){
        enterIndex ++;
        if(enterIndex%40==0){
            FlyingObject f = nextOne();
            flyings=Arrays.copyOf(flyings,flyings.length+1);
            flyings[flyings.length-1] = f;
        }
    }

    /**
     * 飞行物、子弹、英雄机走步方法
     */
    public void stepAction(){
        hero.step();
        for(int i=0;i<flyings.length;i++){
            flyings[i].step();
        }
        for(int j=0;j<bullets.length;j++){
            bullets[j].step();
        }
    }

    /**
     * 子弹入场方法
     * 子弹有英雄机射出
     * 每隔 300 ms 射击一次
     */
    private int shootIndex = 0;
    public void shootAction(){
        shootIndex ++;
        if(shootIndex%30==0){
            Bullet[] bs = hero.shoot();
            bullets = Arrays.copyOf(bullets,bullets.length+bs.length);
            System.arraycopy(bs,0,bullets,bullets.length-bs.length,bs.length);
        }
    }

    /**
     * 检测是否出界，出界则移除
     */
    public void outOfBoundsAction(){
        int index = 0;
        FlyingObject[] flyingLives = new FlyingObject[flyings.length];
        for(int i=0;i<flyings.length;i++){
            FlyingObject f = flyings[i];
            if(!f.outOfBounds()){
                flyingLives[index++] = f;
            }
        }
        flyings = Arrays.copyOf(flyingLives,index);

        index = 0;
        Bullet[] bs = new Bullet[bullets.length];
        for(int i=0;i<bullets.length;i++){
            Bullet b = bullets[i];
            if(!b.outOfBounds()){
                bs[index++] = b;
            }
        }
        bullets = Arrays.copyOf(bs,index);

        //System.out.println(flyings.length+"--"+bullets.length);
    }

    /**
     * 检测子弹是否击中敌人
     */
    public void bangAction(){
        for(int i=0;i<bullets.length;i++){
            bang(bullets[i],i);
        }
    }

    /**
     * 一颗子弹与所有敌人检测
     * @param b
     * @param num 子弹下标
     */
    public void bang(Bullet b,int num){
        int index = -1;
        for(int i=0;i<flyings.length;i++){
            FlyingObject f = flyings[i];
            if(f.shootBy(b)){
                index = i;
                bullets[num] = bullets[bullets.length-1];
                bullets = Arrays.copyOf(bullets,bullets.length-1);
                break;
            }
        }
        if(index!=-1){
            FlyingObject one = flyings[index];
            if(one instanceof Enemy){
                Enemy e = (Enemy)one;
                score += e.getScore();
            }
            if(one instanceof Award){
                Award a = (Award)one;
                int type = a.getType();
                switch (type) {
                    case Award.LIFE:
                        hero.addLife();
                        break;
                    case Award.DOUBLE_FIRE:
                        hero.addDoubleFire();
                        break;
                    default:
                        break;
                }
            }

            flyings[index] = flyings[flyings.length-1];
            flyings=Arrays.copyOf(flyings, flyings.length-1);
        }
    }

    /**
     * 检测游戏是否结束
     */
    public void checkGameOver(){
        if(isGameOVer()){
            state = Constant.GAME_OVER;
        }
    }

    /**
     * 检测英雄机是否与敌机碰撞
     * 一旦碰撞，减命，双倍火力清零
     */
    public boolean isGameOVer(){
        for(int i=0;i<flyings.length;i++){
            FlyingObject f = flyings[i];
            if(hero.hit(f)){
                hero.substractLife();
                hero.setDoubleFire(0);
                flyings[i] = flyings[flyings.length-1];
                flyings=Arrays.copyOf(flyings, flyings.length-1);
            }
            if(hero.getLife()<0){
                break;
            }
        }
        return hero.getLife()<=0;
    }

    /**
     * 游戏启动方法
     */
    private int interval = 10;
    Timer timer;
    public void action(){
        MouseAdapter l = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(state==Constant.START){ //��Ϸ��ʼ
                    state = Constant.RUNNING;
                }
                if(state==Constant.GAME_OVER){ //����ս��
                    hero = new Hero();
                    flyings = new FlyingObject[0];
                    bullets = new Bullet[0];
                    score = 0;
                    state = Constant.START;
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(state==Constant.PAUSE){ //
                    state = Constant.RUNNING;
                }

            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(state==Constant.RUNNING){
                    state=Constant.PAUSE;
                }
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                if(state ==Constant.RUNNING){
                    int x = e.getX();
                    int y = e.getY();
                    hero.move(x,y);
                }
            }
        };
        this.addMouseListener(l);
        this.addMouseMotionListener(l);

        timer = new Timer();
        timer.schedule(new TimerTask(){
            public void run() {
                if(state ==Constant.RUNNING){
                    enterAction(); //�������볡
                    stepAction(); //�������߲�
                    shootAction(); //�ӵ����볪
                    outOfBoundsAction(); //Խ����
                    bangAction(); //�ӵ����������
                    checkGameOver(); //�����Ϸ�Ƿ����
                }
                repaint();
            }
        }, interval,interval);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Fly");
        ShootGame game = new ShootGame();
        frame.add(game);
        frame.setSize(Constant.WIDTH,Constant.HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        game.action();
    }

    /**
     * 工厂方法
     * 用于产生一个敌机或者小蜜蜂
     * @return
     */
    public static FlyingObject nextOne(){
        Random rand = new Random();
        int type = rand.nextInt(20);
        if(type<2){
            return new Bee();
        }else{
            return new Airplane();
        }
    }

}
