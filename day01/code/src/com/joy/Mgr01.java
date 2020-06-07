package com.joy;

/**
 * @author joy
 * @date 2020/6/7
 * 第一个是我会写的设计模式
 */
public class Mgr01 {
    private static Mgr01 _inter = null;
    public static Mgr01 getInter(){
        if(_inter == null){
            _inter = new Mgr01();
        }
        return _inter;
    }

    public void sayHello(){
        System.out.println("hello");
    };

    public static void main(String[] args) {
        Mgr01 mgr01 = Mgr01.getInter();
        mgr01.sayHello();
    }
}
