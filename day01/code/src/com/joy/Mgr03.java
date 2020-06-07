package com.joy;

/**
 * @author joy
 * @date 2020/6/7
 * 解决第二种方式的加锁操作
 */
public class Mgr03 {
    private static Mgr03 _inter;

    public static Mgr03 getInstance() {
        if (_inter == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (Mgr03.class) {
                if (_inter == null) {
                    _inter = new Mgr03();
                }
            }
        }
        return _inter;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr03.getInstance());
            }).start();
        }
    }
}
