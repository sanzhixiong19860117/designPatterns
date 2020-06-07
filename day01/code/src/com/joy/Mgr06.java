package com.joy;

/**
 * @author joy
 * @date 2020/6/7
 * 大神的操作
 */
public enum Mgr06 {
    INSTER;

    public void say() {
        System.out.println("hello");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr06.INSTER.hashCode());
            }).start();
        }
    }
}
