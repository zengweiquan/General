package com.sshy.general;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GeneralTest {
    private volatile Integer money=100;

    @Test
    public void t1(){
        if (money==0){
            System.out.println("钱已经抢光了~~~");
            return;
        }
        System.out.println(money+":钱已经抢光了~~~"+Thread.currentThread().getName());
        money--;
    }
    @Test
    public void t2(){
        for (int i = 0; i <200 ; i++) {

             new Thread(() -> {
                t1();

            }).start();
            new Thread(() -> {
                t1();
            }).start();

        }
    }
}
