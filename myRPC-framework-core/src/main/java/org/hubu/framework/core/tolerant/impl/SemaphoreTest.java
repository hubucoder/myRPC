package org.hubu.framework.core.tolerant.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    private static final int Thread_Count = 30;

    private static ExecutorService executorService = Executors.newFixedThreadPool(Thread_Count);
    private static Semaphore semaphore = new Semaphore(10);

    /**
     * 一次只允许10个线程通过请求
     */
    public static void main(String[] args) {
        for (int i = 0; i < Thread_Count; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println("save data "+System.currentTimeMillis());
                        Thread.sleep(5000);
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
    }
}
