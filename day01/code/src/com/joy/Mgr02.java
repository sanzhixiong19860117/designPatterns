package com.joy;

/**
 * @author joy
 * @date 2020/6/7
 * 第一种方式在多线程的下出现的问题
 */
public class Mgr02 {
    private static Mgr02 mgr02;

    public static Mgr02 getInstance() {
        if (mgr02 == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mgr02 = new Mgr02();
        }
        return mgr02;
    }

    public void sayHello() {
        System.out.println("Hello");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //进行多线程测试
            new Thread(() -> {
                System.out.println(Mgr02.getInstance());
            }).start();
        }
    }
}
