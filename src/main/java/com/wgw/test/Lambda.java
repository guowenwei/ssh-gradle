package com.wgw.test;

import org.junit.Test;

public class Lambda {

    @Test
    public void test1(){
        //new Thread(() -> System.out.println("魏国文好帅！")).start();
        () -> System.out.println("打印lambda表达式");
    }
}
