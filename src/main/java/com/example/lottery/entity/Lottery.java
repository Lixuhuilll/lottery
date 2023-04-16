package com.example.lottery.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * 彩票
 */
@Data
public class Lottery {
    private static final int DEFAULT_RED_BALL_COUNT = 6;
    private static final int DEFAULT_BLUE_BALL_COUNT = 1;

    private static final int DEFAULT_MIN_RED_BALL = 1;
    private static final int DEFAULT_MAX_RED_BALL = 33;
    private static final int DEFAULT_MIN_BLUE_BALL = 1;
    private static final int DEFAULT_MAX_BLUE_BALL = 16;

    private Set<Integer> redBalls = new HashSet<>();
    private Set<Integer> blueBalls = new HashSet<>();

    /**
     * 随机生成一注彩票
     *
     * @param redBallCount  红球个数
     * @param blueBallCount 蓝球个数
     * @param minRedBall    红球最小值
     * @param maxRedBall    红球最大值
     * @param minBlueBall   蓝球最小值
     * @param maxBlueBall   蓝球最大值
     */
    public void random(int redBallCount, int blueBallCount,
                       int minRedBall, int maxRedBall,
                       int minBlueBall, int maxBlueBall) {
        redBalls.clear();
        blueBalls.clear();
        // 集合的特性，不会重复，所以不用担心重复的问题
        while (redBalls.size() < redBallCount) {
            // Math.random() 产生的是 >= 0.0 且 < 1.0 的随机数,min需要 >= 1,max才取得到
            redBalls.add((int) (Math.random() * maxRedBall + minRedBall));
        }
        while (blueBalls.size() < blueBallCount) {
            blueBalls.add((int) (Math.random() * maxBlueBall + minBlueBall));
        }
    }

    /**
     * 随机生成一注彩票
     *
     * @param redBallCount  红球个数
     * @param blueBallCount 蓝球个数
     */
    public void random(int redBallCount, int blueBallCount) {
        random(redBallCount, blueBallCount,
                DEFAULT_MIN_RED_BALL, DEFAULT_MAX_RED_BALL,
                DEFAULT_MIN_BLUE_BALL, DEFAULT_MAX_BLUE_BALL);
    }

    /**
     * 默认的随机生成函数，使用双色球规则随机生成一注彩票
     */
    public void random() {
        random(DEFAULT_RED_BALL_COUNT, DEFAULT_BLUE_BALL_COUNT);
    }

}
