package com.hust_twj.zademo.jet_pack.live_data;

import androidx.lifecycle.MutableLiveData;

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-08-07.
 */
public class UserViewModel extends MutableLiveData {

    private MutableLiveData<UserInfo> mUser = new MutableLiveData<>();

    private UserViewModel() {

    }

    private static class Holder {
        public static final UserViewModel INSTANCE = new UserViewModel();
    }

    public static UserViewModel getInstance() {
        return Holder.INSTANCE;
    }

    public void setUser(UserInfo user) {
        mUser.setValue(user);
    }

    public MutableLiveData<UserInfo> getUser() {
        return mUser;
    }


}
