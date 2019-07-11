package com.hust_twj.zademo.jvm.invoke;

/**
 * @author hust_twj
 * @date 2019/7/12
 */
public class InvokeTest {

    private String tag = "InvokeTest";

    /**
     * 私有方法
     * @param a
     * @param b
     * @return
     */
    private int add(int a, int b){
        return a + b;
    }

    public String getTag() {
        return tag;
    }

}
