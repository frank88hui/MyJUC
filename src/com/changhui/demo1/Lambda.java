package com.changhui.demo1;
@FunctionalInterface
interface Foo{
    void sayHello(String A,String B);

    default int div(int x ,int y){
        return x/y;
    }

    static int sub(int x ,int y){
        return x-y;
    }
}

public class Lambda {

    public static void main(String[] args) {

        Foo foo= (A, B) -> System.out.println("hello lambda expression "+A+" "+B);

        foo.sayHello("Java","Python");

        System.out.println(foo.div(9, 3));

        System.out.println(Foo.sub(9, 3));
    }

}
