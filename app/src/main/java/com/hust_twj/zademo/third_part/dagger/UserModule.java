package com.hust_twj.zademo.third_part.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * module：提供依赖的对象
 * @author hust_twj
 * @date 2020/11/1
 */
@Module
public class UserModule {

    //@Provides：提供依赖的
    //@Inject：使用依赖的
    @Provides
    public ApiService provideApiService() {
        return new ApiService();
    }
}
