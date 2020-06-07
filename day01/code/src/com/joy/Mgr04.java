package com.joy;

/**
 * @author joy
 * @date 2020/6/7
 * 饿汉方式
 */
public class Mgr04 {
    private static Mgr04 mgr04 = new Mgr04();

    private Mgr04() {
    }

    public static Mgr04 getInstance() {
        return mgr04;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Mgr04.getInstance());
        }
    }
}
