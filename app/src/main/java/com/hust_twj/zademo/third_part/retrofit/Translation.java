package com.hust_twj.zademo.third_part.retrofit;

import java.io.Serializable;
import java.util.List;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020-01-04.
 */
public class Translation   {

    private int status;

    private content content;
    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }
}
