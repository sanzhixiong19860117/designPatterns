# 单利模式

## 我会单利模式

```java
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
```

这种方式在多线程下面会在new的时候创建很多的对象。

```java
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
                System.out.println(Mgr02.getInstance().hashCode());
            }).start();
        }
    }
}
```

运行结果如下

```
830774632
608372473
1187900321
1450099523
1680279605
```

我们发现不同的对象的哈希数值不一样，从客观上说是不一样的。然后我使用地址打印以后发现地址也是不一样的。

```
com.joy.Mgr02@64270835
com.joy.Mgr02@22cc4c4a
com.joy.Mgr02@74450d14
```

由上面的说明，这种方式在多线程的方式下，不是安全的。后面我就加锁进行操作。

使用双判断操作以后避免的多线程的操作出现的问题

```java
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
```

打印出结果是

```
com.joy.Mgr03@655b6e05
com.joy.Mgr03@655b6e05
com.joy.Mgr03@655b6e05
com.joy.Mgr03@655b6e05
com.joy.Mgr03@655b6e05
```

这样我解决了我的我出现的问题。

## 饿汉的方式操作（必须要学会）

```java
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
```

饿汉的方式注意点

1. 直接静态的new对象，这样会在不需要的时候也占用内存（这个不是太完美）
2. 构造函数设置成私有强行不让外界new
3. 它是线程安全的操作方式

完美的一种方式使用内部类进行创建

```java
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
```

如果我没有用内部类的话，就不会把对象加入到内存中。

## 最后一个是大神用结构体进行操作的（可以选用）

```java
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
```