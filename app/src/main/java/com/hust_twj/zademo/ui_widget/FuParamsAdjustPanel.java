package com.hust_twj.zademo.ui_widget;

import android.app.Activity;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.constans.FUConstants;
import com.hust_twj.zademo.constans.PreferenceKey;
import com.hust_twj.zademo.utils.BasePopupWindow;
import com.hust_twj.zademo.utils.PreferenceUtil;


/**
 * Created by Wenjing.Tang
 * on 2019/2/26
 *
 */
public class FuParamsAdjustPanel extends BasePopupWindow implements SeekBar.OnSeekBarChangeListener {

    private Activity activity;
    private Switch mBeautySwitch;
    private SeekBar mSeekBarBlurLevel, mSeekBarColorLevel, mSeekBarCheekThinning, mSeekBarEyeEnlarging;
    //磨皮/美白/瘦脸/大眼
    private float mBlurLevel, mColorLevel, mCheekThinning, mEyeEnlarging;

    public static final int FLAG_BLUR_LEVEL = 1;
    public static final int FLAG_COLOR_LEVEL = 2;
    public static final int FLAG_CHEEK_THINNING = 3;
    public static final int FLAG_EYE_ENLARGING = 4;

    public FuParamsAdjustPanel(Activity activity) {
        super(activity);
        this.activity = activity;

        initData();
        setListener();
        updateParentWindowAlpha(BasePopupWindow.ALPHA_TRANSPARENT);
    }

    private void initData() {
        mBlurLevel = PreferenceUtil.getFloat(activity, PreferenceKey.BLUR_LEVEL, FUConstants.DEFAULT_BLUR_LEVEL);
        mColorLevel = PreferenceUtil.getFloat(activity, PreferenceKey.COLOR_LEVEL, FUConstants.DEFAULT_COLOR_LEVEL);
        mCheekThinning = PreferenceUtil.getFloat(activity, PreferenceKey.CHEEK_THINNING, FUConstants.DEFAULT_CHEEK_THINNING);
        mEyeEnlarging = PreferenceUtil.getFloat(activity, PreferenceKey.EYE_ENLARGING, FUConstants.DEFAULT_EYE_ENLARGING);

        //初始化SeekBar值
        mSeekBarBlurLevel.setProgress((int) (mBlurLevel * 100));
        mSeekBarColorLevel.setProgress((int) (mColorLevel * 100));
        mSeekBarCheekThinning.setProgress((int) (mCheekThinning * 100));
        mSeekBarEyeEnlarging.setProgress((int) (mEyeEnlarging * 100));

        boolean isSwitchOn = PreferenceUtil.getBoolean(activity, PreferenceKey.IS_BEAUTY_SWITCH_ON, true);
        setBeautySwitch(isSwitchOn);
    }

    private void setListener() {
        mSeekBarBlurLevel.setOnSeekBarChangeListener(this);
        mSeekBarColorLevel.setOnSeekBarChangeListener(this);
        mSeekBarCheekThinning.setOnSeekBarChangeListener(this);
        mSeekBarEyeEnlarging.setOnSeekBarChangeListener(this);
        mBeautySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mOnFuParamsChangeListener != null) {
                    mOnFuParamsChangeListener.onBeautySwitchChange(isChecked);
                    PreferenceUtil.saveValue(getContext(), PreferenceKey.IS_BEAUTY_SWITCH_ON, isChecked);
                }
            }
        });
    }

    @Override
    public void popupFromBottom() {
        super.popupFromBottom();
        updateParentWindowAlpha(BasePopupWindow.ALPHA_TRANSPARENT);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fu_params_adjust_panel;
    }

    @Override
    protected void init() {
        mBeautySwitch = find(R.id.beauty_switch);
        mSeekBarBlurLevel = find(R.id.blur_level_seek_bar);
        mSeekBarColorLevel = find(R.id.color_level_seek_bar);
        mSeekBarCheekThinning = find(R.id.cheek_thinning_seek_bar);
        mSeekBarEyeEnlarging = find(R.id.eye_enlarging_seek_bar);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (mOnFuParamsChangeListener == null) {
            return;
        }
        if (seekBar == mSeekBarBlurLevel) {
            mBlurLevel = progress / 100f;
            mOnFuParamsChangeListener.onFuParamsChange(FLAG_BLUR_LEVEL, mBlurLevel);
        } else if (seekBar == mSeekBarColorLevel) {
            mColorLevel = progress / 100f;
            mOnFuParamsChangeListener.onFuParamsChange(FLAG_COLOR_LEVEL, mColorLevel);
        } else if (seekBar == mSeekBarCheekThinning) {
            mCheekThinning = progress / 100f;
            mOnFuParamsChangeListener.onFuParamsChange(FLAG_CHEEK_THINNING, mCheekThinning);
        } else if (seekBar == mSeekBarEyeEnlarging) {
            mEyeEnlarging = progress / 100f;
            mOnFuParamsChangeListener.onFuParamsChange(FLAG_EYE_ENLARGING, mEyeEnlarging);
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        PreferenceUtil.saveValue(activity, PreferenceKey.BLUR_LEVEL, mBlurLevel);
        PreferenceUtil.saveValue(activity, PreferenceKey.COLOR_LEVEL, mColorLevel);
        PreferenceUtil.saveValue(activity, PreferenceKey.CHEEK_THINNING, mCheekThinning);
        PreferenceUtil.saveValue(activity, PreferenceKey.EYE_ENLARGING, mEyeEnlarging);
    }

    private void setBeautySwitch(boolean isBeautySwitchOn) {
        mBeautySwitch.setChecked(isBeautySwitchOn);
    }

    public interface OnFuParamsChangeListener {
        /**
         * 美颜开/关
         * @param isBeautySwitchOn  true：开启美颜
         */
        void onBeautySwitchChange(boolean isBeautySwitchOn);

        /**
         * FU参数改变回调
         *
         * @param flag  美颜参数标识
         * @param value 参数值
         */
        void onFuParamsChange(int flag, float value);
    }

    private OnFuParamsChangeListener mOnFuParamsChangeListener;

    public void setOnFuParamsChangeListener(OnFuParamsChangeListener onFuParamsChangeListener) {
        this.mOnFuParamsChangeListener = onFuParamsChangeListener;
    }

}
