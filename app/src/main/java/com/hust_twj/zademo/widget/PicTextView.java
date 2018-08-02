package com.hust_twj.zademo.widget;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.DensityUtils;
import com.hust_twj.zademo.utils.LogUtils;

/**
 * 图文混排控件 V 1.0.0
 * 待优化：当超过三行，且三行临界处是一个长英文单词时，会分割掉该单词
 *
 * 如 最近在做一个类似于QQ空间的一个社交圈的模块的开发。有一个需求是当用户发表的内容超出4行时，显示一个按钮，点击按钮展示全文。我还真没有发现TextView有获取文本内容有没有显示省略号这个方法。没办法，只能自己想办法了。
 */

    public class PicTextView  extends RelativeLayout{

        private Context mContext;
        public TextView mContentTv1, mContentTv2;

        private TextView mTvIcon;
        private ImageView mIvIcon;
        public static final int TYPE_TEXT = 1;
        public static final int TYPE_PICTURE = 2;
        private int showType;//icon处的显示样式，图片或者文字
    //上部分显示行数
    private static final int UPPER_LINE = 3;

    public PicTextView(Context context) {
        this(context, null);
    }

    public PicTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PicTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_picture_and_text, this, true);
        mContentTv1 = findViewById(R.id.textview_up);
        mContentTv2 = findViewById(R.id.textview_down);
        mTvIcon = findViewById(R.id.tv_icon);
        mIvIcon = findViewById(R.id.icon_heart_word);
    }

    public void setType(int type){
        showType = type;
        if (type == TYPE_TEXT){
            mTvIcon.setVisibility(VISIBLE);
            mIvIcon.setVisibility(GONE);
        }else if (type == TYPE_PICTURE){
            mTvIcon.setVisibility(VISIBLE);
            mIvIcon.setVisibility(GONE);
        }
    }

    public void setIconText(String iconText){
        if (showType == TYPE_TEXT){
            mTvIcon.setText(iconText);
        }
    }

    public void setContent(String content) {
        mContentTv1.setText(content);

        Paint paint = mContentTv1.getPaint();
        float totalWidth = paint.measureText(content);
        float width = DensityUtils.getScreenWidth(mContext) - DensityUtils.dp2px(mContext, 60);
        int firstLineCount = paint.breakText(content, true, width, null);

        int line = (int) Math.ceil(totalWidth / width);

        LogUtils.e("twj123", "firstLineCount: " +firstLineCount +" -- line: " +line);
        boolean showDown = false;
        if (totalWidth - width * UPPER_LINE > 0) {
            showDown = true;
        }
        if (showDown) { //UPPER_LINE行或UPPER_LINE行以上的情况
            mContentTv2.setVisibility(VISIBLE);
            int startIndex = 0;
            int endIndex = firstLineCount * UPPER_LINE - 1;
            String strText;
            if (content.length() > endIndex - startIndex) {
                strText = content.subSequence(startIndex, endIndex).toString();
                final String expandedStr = content.length() > strText.length() ? content.substring(strText.length()) : "";
                mContentTv1.setText(strText);
                mContentTv1.setMaxLines(UPPER_LINE);
                mContentTv2.setText(expandedStr);
            }
        }else {
            mContentTv1.setText(content);
            mContentTv2.setVisibility(GONE);
        }
    }

}
