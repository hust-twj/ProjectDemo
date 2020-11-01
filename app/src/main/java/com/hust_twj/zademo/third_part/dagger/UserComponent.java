package com.hust_twj.zademo.third_part.dagger;

import dagger.Component;

/**
 * component：桥梁连接 module和依赖container
 * @author hust_twj
 * @date 2020/11/1
 */
//连接module，编译成功后自动生成 DaggerUserComponent
@Component(modules = UserModule.class)
public interface UserComponent {

    /**
     * 连接依赖container：DaggerActivity
     * @param activity
     */
    void inject(Dagger2Activity activity);

}
