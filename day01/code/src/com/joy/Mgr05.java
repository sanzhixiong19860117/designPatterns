package com.joy;

/**
 * @author joy
 * @date 2020/6/7
 * 内部类创建单利
 */
public class Mgr05 {
    private Mgr05() {
    }

    public void sayHello() {
        System.out.println("hello");
    }

    public static class Mgr05Have {
        private static Mgr05 mgr05 = new Mgr05();
        public static Mgr05 getInter() {
            return mgr05;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Mgr05Have.getInter());
            }).start();
        }
    }
}
