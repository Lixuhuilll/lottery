package com.example.lottery.util;

import com.example.lottery.entity.Lottery;

public class LotteryUtil {
    /**
     * 双色球中奖规则计算单注奖金金额 <br>
     * 0+1、1+1、2+1 单注5元 <br>
     * 3+1、4+0 单注10元 <br>
     * 4+1、5+0 单注200元 <br>
     * 5+1 单注3000元 <br>
     * 6+0 单注125万 <br>
     * 6+1 单注500万
     *
     * @param winLottery 中奖彩票
     * @param input      用户输入的彩票
     */
    public static int twoColorBallCalculate(Lottery winLottery, Lottery input) {
        int[] winner = getWinner(winLottery, input);

        return switch (winner[1]) {
            case 0 -> switch (winner[0]) {
                case 4 -> 10;
                case 5 -> 200;
                case 6 -> 125_0000; // 125万
                default -> 0;
            };
            case 1 -> switch (winner[0]) {
                case 0, 1, 2 -> 5;
                case 3 -> 10;
                case 4 -> 200;
                case 5 -> 3000;
                case 6 -> 500_0000; // 500万
                default -> 0;
            };
            default -> 0;
        };
    }

    /**
     * 获取中奖情况
     *
     * @param winLottery 中奖彩票
     * @param input      用户输入的彩票
     * @return winner[0] 红球中奖个数, winner[1] 蓝球中奖个数
     */
    public static int[] getWinner(Lottery winLottery, Lottery input) {
        int[] winner = new int[2];
        for (Integer redBall : winLottery.getRedBalls()) {
            if (input.getRedBalls().contains(redBall)) {
                winner[0]++;
            }
        }
        for (Integer blueBall : winLottery.getBlueBalls()) {
            if (input.getBlueBalls().contains(blueBall)) {
                winner[1]++;
            }
        }
        return winner;
    }

    /**
     * 调用 Lottery 默认的随机生成函数，随机生成一注双色球彩票
     *
     * @return 生成的彩票
     */
    public static Lottery getRandomTwoColorBallLottery() {
        Lottery lottery = new Lottery();
        lottery.random();
        return lottery;
    }
}