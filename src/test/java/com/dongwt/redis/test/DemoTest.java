package com.dongwt.redis.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoTest {

    public static void main(String[] args) {
                ExecutorService executorService = Executors.newFixedThreadPool(1);
                executorService.submit(new Runnable() {
                    
                    @Override
                    public void run() {
                        System.out.println(".....");
                    }
                });
                
                System.out.println("+++");

    }
}
