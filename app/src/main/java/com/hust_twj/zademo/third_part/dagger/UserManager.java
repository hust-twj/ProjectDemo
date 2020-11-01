package com.hust_twj.zademo.third_part.dagger;

/**
 * @author hust_twj
 * @date 2020/10/30
 */
public class UserManager {

    private ApiService mApiService;
    private UserStore mUserStore;

    public UserManager() {
        mApiService = new ApiService();
        mUserStore = new UserStore();
    }

    public void register() {
        mApiService.register();
        mUserStore.register();
    }

}
