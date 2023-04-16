package com.example.lottery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LotteryMsg {
    private int money;
    private Lottery winLottery;
}
