package com.hust_twj.zademo.jvm.utils;

/**
 * description ：
 * Created by Wenjing.Tang on 2019-05-27.
 */
final class Preconditions {

    /**
     * Returns instance unless it's null.
     *
     * @throws //NullPointerException if instance is null
     */
    static <T> T checkNotNull(T instance, String name) {
        if (instance == null) {
            throw new NullPointerException(name + " must not be null");
        }
        return instance;
    }

    private Preconditions() {
        throw new AssertionError();
    }
}
