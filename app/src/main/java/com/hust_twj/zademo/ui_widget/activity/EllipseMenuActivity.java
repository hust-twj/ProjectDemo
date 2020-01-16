package com.hust_twj.zademo.ui_widget.activity;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.ui_widget.bean.Members;
import com.hust_twj.zademo.ui_widget.widget.GreatHallMenuLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * description ：椭圆环形菜单
 * Created by Wenjing.Tang on 2019-05-28.
 */
public class EllipseMenuActivity extends Activity {


    //EllipseMenuLayout circleMenu;
    GreatHallMenuLayout circleMenu;
    List<Members> list = new ArrayList<>();
    private List<Menu> menuList = new ArrayList<>();

    private String[] mItemTexts = new String[]{"朋友圈 ", "腾讯QQ", "QQ空间",
            "腾讯微博", "微信好友", "新浪微博", "新浪微博1", "新浪微博2"};

    private int[] mItemImgs = new int[]{R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ellipse_menu);


        circleMenu = findViewById(R.id.id_menu_layout);

        for (int i = 0; i < 8; i++) {
            Members member = new Members();
            member.heartValue = String.valueOf(1000 * i);
            member.gender = i % 2;
            member.nickname = "haha " + i;
            member.objectID = 1000 + i;
            list.add(member);
        }
        circleMenu.setMenus(list);

        /*for (int i = 0; i < mItemTexts.length; i++) {
            Menu m = new Menu();
            m.imgId = mItemImgs[i];
            m.label = mItemTexts[i];
            menuList.add(m);
        }
        circleMenu.setMenus(menuList, new EllipseMenuLayout.OnLoadResCallback() {

            @Override
            public void showItem(Object o, ImageView img, TextView txt) {
                Menu m = (Menu) o;
                img.setBackground(getResources().getDrawable(m.imgId));
                //Glide.with(mContext).load(m.imgId).into(img);
                txt.setText(m.label);
            }
        });


        circleMenu.setOnMenuItemClickListener(new EllipseMenuLayout.OnMenuItemClickListener(){

            @Override
            public void itemClick(View view, int pos){
                Toast.makeText(EllipseMenuActivity.this, mItemTexts[pos],
                        Toast.LENGTH_SHORT).show();

            }
        });*/

    }


    private static class Menu {
        public int imgId;
        public String label;
    }


}
