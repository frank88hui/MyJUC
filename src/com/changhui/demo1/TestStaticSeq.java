package com.changhui.demo1;

class Father {
    public Father() {
        System.out.println("111111111111111");
    }

    {
        System.out.println("222222222222222");
    }

    static {
        System.out.println("333333333333333");
    }
}

class Son extends Father {
    public Son() {
        System.out.println("444444444444444");
    }

    {
        System.out.println("555555555555555");
    }

    static {
        System.out.println("666666666666666");
    }
}


public class TestStaticSeq {

    public static void main(String[] args) {
        new Son();
        System.out.println("------------------");
        new Son();
        System.out.println("------------------");
        new Father();
    }
}
