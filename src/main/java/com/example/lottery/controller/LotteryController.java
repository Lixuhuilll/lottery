package com.example.lottery.controller;

import com.example.lottery.entity.Lottery;
import com.example.lottery.entity.LotteryMsg;
import com.example.lottery.util.LotteryUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LotteryController {

    @ResponseBody
    @PostMapping("/lottery")
    public LotteryMsg lottery(@RequestParam(defaultValue = "1") int num,
                              @RequestBody Lottery input) {
        if (input == null || input.getRedBalls() == null || input.getBlueBalls() == null
                || input.getRedBalls().size() != 6 || input.getBlueBalls().size() != 1) {
            return null;
        }
        // 生成随机双色球作为中奖号码
        Lottery winLottery = LotteryUtil.getRandomTwoColorBallLottery();
        // 通过双色球中奖规则计算奖金金额
        int money = num * LotteryUtil.twoColorBallCalculate(winLottery, input);
        return new LotteryMsg(money, winLottery);
    }
}
