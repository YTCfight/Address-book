package shootgame;
/**
*奖励接口
*@auhtor long
*
 */
public interface Award {
    int LIFE = 0; //命
    int DOUBLE_FIRE = 1; //双倍火力

    int getType(); //获取奖励类型

}
