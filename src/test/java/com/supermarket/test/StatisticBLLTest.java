package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.StatisticBLL;
import com.supermarket.DTO.Statistic;
import com.supermarket.utils.Date;

public class StatisticBLLTest{
    StatisticBLL statisticBLL = new StatisticBLL();

    Statistic statistic = new Statistic(2, new Date(23, 10, 2023), 22, 21, false); 

    @Test
    public void arrayTest(){
        for(Statistic statisticRow : statisticBLL.getStatisticList()){
            System.out.println(statisticRow.getAmount());
        }
    }

    @Test
    public void addTest() {
        boolean success = statisticBLL.addStatistic(statistic);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        statistic.setAmount(5);;
        boolean success = statisticBLL.updateStatistic(statistic);
        assertTrue(success);
    }

    @Test
    public void removeTest() {
        boolean success = statisticBLL.deleteStatistic(statistic);
        assertTrue(success);
    }
}