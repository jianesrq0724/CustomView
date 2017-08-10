package com.ruiqin.customview.module.test;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class JNC extends Wine {

    public void fun1(String a) {
        System.out.println("JNC 的 Fun1...");
        fun2();
    }

    /**
     * 子类重写父类方法
     * 指向子类的父类引用调用fun2时，必定是调用该方法
     */
    @Override
    public void fun2() {
        System.out.println("JNC 的Fun2...");
    }

}
