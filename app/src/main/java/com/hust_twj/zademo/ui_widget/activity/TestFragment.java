package com.hust_twj.zademo.ui_widget.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

/**
 * description ：
 * Created by Wenjing.Tang on 2019-05-23.
 */
public class TestFragment extends Fragment {

    private static final String ARG_PARAM = "param_key";
    private String mParam;


    public TestFragment() { }

    public static TestFragment newInstance(String param) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtils.e("twj124", "onAttach");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM);
        }
        LogUtils.e("twj124", "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        // View 初始化，findViewById() 等操作
        LogUtils.e("twj124", "onCreateView");

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.e("twj124", "onActivityCreated");

        // 初始化数据，加载数据等...
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtils.e("twj124", "onViewCreated");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.e("twj124", "onDestroyView");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e("twj124", "onDestroy");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.e("twj124", "onDetach");

    }

}
