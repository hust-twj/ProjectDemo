package com.hust_twj.zademo.ui_widget.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.ui_widget.widget.CircleMenuLayout;
import com.hust_twj.zademo.ui_widget.widget.EllipseMenuLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * description ：圆环形菜单
 * Created by Wenjing.Tang on 2019-05-28.
 */
public class CircleMenuActivity extends Activity {


    CircleMenuLayout circleMenu;
    private List<Menu> menuList = new ArrayList<>();

    private String[] mItemTexts = new String[]{"朋友圈 ", "腾讯QQ", "QQ空间",
            "腾讯微博", "微信好友", "新浪微博", "新浪微博1", "新浪微博2"};

    private int[] mItemImgs = new int[]{R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_circle_menu);


        circleMenu = findViewById(R.id.id_menu_layout);

        for (int i = 0; i < mItemTexts.length; i++) {
            Menu m = new Menu();
            m.imgId = mItemImgs[i];
            m.label = mItemTexts[i];
            menuList.add(m);
        }
        circleMenu.setMenus(menuList, new CircleMenuLayout.OnLoadResCallback() {

            @Override
            public void showItem(Object o, ImageView img, TextView txt) {
                Menu m = (Menu) o;
                img.setBackground(getResources().getDrawable(m.imgId));
                txt.setText(m.label);
            }
        });


        circleMenu.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener(){

            @Override
            public void itemClick(View view, int pos){
                Toast.makeText(CircleMenuActivity.this, mItemTexts[pos],
                        Toast.LENGTH_SHORT).show();

            }
        });

    }


    private static class Menu{
        public int imgId;
        public String label;
    }


}
