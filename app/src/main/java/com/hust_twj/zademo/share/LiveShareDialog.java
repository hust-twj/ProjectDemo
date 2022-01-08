package com.hust_twj.zademo.share;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import androidx.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


import com.hust_twj.zademo.DemoApplication;
import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.ToastUtils;
import com.hust_twj.zademo.utils.ViewsUtil;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by Wenjing.Tang
 * on 2019/1/31
 */
public class LiveShareDialog extends Dialog implements View.OnClickListener {

    private Activity mActivity;
    private ImageView mIvClose;
    private TextView mTvShareWechat;
    private TextView mTvShareMoment;
    private TextView mTvShareQQ;
    private TextView mTvShareLink;

    private String mTitle;
    private String mShareText;
    private String mShareImgUrl;
    private String mShareUrl;

    private long roomID;

    public LiveShareDialog(@NonNull Activity context) {
        super(context, R.style.CommonDialog_Fullscreen);
        mActivity = context;

        initView();
        bindListener();
    }

    public void setShareContent(String title, String text, String imgUrl, String shareUrl) {
        this.mTitle = title;
        this.mShareText = text;
        this.mShareImgUrl = imgUrl;
        this.mShareUrl = shareUrl;
    }

    /**
     * 设置房间ID
     *
     * @param roomID 房间ID与主播ID一样
     */
    public void setRoomID(long roomID) {
        this.roomID = roomID;
    }

    private void initView() {
        View view = getLayoutInflater().inflate(R.layout.popup_window_live_share, null, false);
        setContentView(view);

        mIvClose = findViewById(R.id.iv_close);
        mTvShareWechat = findViewById(R.id.tv_live_share_we_chat);
        mTvShareMoment = findViewById(R.id.tv_live_share_moment);
        mTvShareQQ = findViewById(R.id.tv_live_share_qq);
        mTvShareLink = findViewById(R.id.tv_live_share_copy_link);
    }

    private void bindListener() {
        ViewsUtil.preventRepeatedClicks(mIvClose, this);
        ViewsUtil.preventRepeatedClicks(mTvShareWechat, this);
        ViewsUtil.preventRepeatedClicks(mTvShareMoment, this);
        ViewsUtil.preventRepeatedClicks(mTvShareQQ, this);
        ViewsUtil.preventRepeatedClicks(mTvShareLink, this);
    }

    /**
     * 弹出窗口
     */
    public void popup() {
        //SoftInputManager.hideSoftInput(mActivity);
        if (getWindow() != null) {
            getWindow().setGravity(Gravity.BOTTOM);
            getWindow().setWindowAnimations(R.style.BottomPopupWindow);
        }
        initWindow();
        show();
    }

    /**
     * 初始化基础逻辑
     */
    private void initWindow() {
        //Window属性
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Activity.WINDOW_SERVICE);
        if (windowManager == null) {
            return;
        }
        Display display = windowManager.getDefaultDisplay();
        if (getWindow() == null) {
            return;
        }
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth(); //设置宽度

        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
        getWindow().setAttributes(lp);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_live_share_we_chat:
                dismiss();
                break;
            case R.id.tv_live_share_moment:
                dismiss();
                break;
            case R.id.tv_live_share_qq:
                dismiss();
                break;
            case R.id.tv_live_share_copy_link:
                copy(mShareUrl);
                dismiss();

                break;
            default:
                break;
        }
    }

    private void copy(String content) {
        //获取剪贴板管理器
        ClipboardManager manager = (ClipboardManager) DemoApplication.getContext().getSystemService(CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("copied_link", content);
        // 将ClipData内容放到系统剪贴板里
        if (manager != null) {
            manager.setPrimaryClip(mClipData);
            ToastUtils.toast(getContext(), "已复制链接到剪贴板");
        }
    }

}
