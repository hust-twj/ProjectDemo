package com.hust_twj.zademo.jvm.utils;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import static com.hust_twj.zademo.jvm.utils.Preconditions.checkNotNull;


/**
 * description ：
 * Created by Wenjing.Tang on 2019-05-27.
 */
public class KeyedWeakReference extends WeakReference<Object> {
    public final String key;
    public final String name;

    public KeyedWeakReference(Object referent, String key, String name,
                       ReferenceQueue<Object> referenceQueue) {
        super(checkNotNull(referent, "referent"), checkNotNull(referenceQueue, "referenceQueue"));
        this.key = checkNotNull(key, "key");
        this.name = checkNotNull(name, "name");
    }
}