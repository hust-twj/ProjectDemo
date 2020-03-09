package com.hust_twj.zademo.ui_widget.util;

import android.content.Context;
import androidx.core.content.ContextCompat;
import android.view.View;

import com.hust_twj.zademo.R;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-11-17.
 */
public class TabCreateUtils {

    public interface onTitleClickListener{
        void onTitleClick(int index);
    }
    public static void setWhiteTab(Context context, MagicIndicator magicIndicator, final String[] tabNames , final onTitleClickListener listener) {
        final FragmentContainerHelper mFragmentContainerHelper = new FragmentContainerHelper();
        CommonNavigator commonNavigator = new CommonNavigator(context);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return tabNames == null ? 0 : tabNames.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SelectBigPagerTitleView colorTransitionPagerTitleView = new SelectBigPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(ContextCompat.getColor(context, R.color.white));
                colorTransitionPagerTitleView.setSelectedColor(ContextCompat.getColor(context, R.color.white));
                colorTransitionPagerTitleView.setText(tabNames[index]);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mFragmentContainerHelper.handlePageSelected(index);
                        if (listener!=null)listener.onTitleClick(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setColors(ContextCompat.getColor(context, R.color.white));
                indicator.setRoundRadius(3);
                return indicator;
            }
        });
        commonNavigator.setAdjustMode(true);
        magicIndicator.setNavigator(commonNavigator);
        mFragmentContainerHelper.attachMagicIndicator(magicIndicator);
    }
}
