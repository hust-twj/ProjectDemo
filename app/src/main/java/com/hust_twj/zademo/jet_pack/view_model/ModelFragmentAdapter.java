package com.hust_twj.zademo.jet_pack.view_model;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hust_twj.zademo.jet_pack.view_model.fragment.Fragment_1;
import com.hust_twj.zademo.jet_pack.view_model.fragment.Fragment_2;
import com.hust_twj.zademo.jet_pack.view_model.fragment.Fragment_3;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/4/29.
 */
public class ModelFragmentAdapter extends FragmentStateAdapter {

    public ModelFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Fragment_1();
            case 1:
                return new Fragment_2();
            default:
                return new Fragment_3();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
