package com.hust_twj.zademo.moments_2_0.hot_topic.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatTextView;
import android.text.DynamicLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.CenteredImageSpan;
import com.hust_twj.zademo.utils.DensityUtils;
import com.hust_twj.zademo.utils.LogUtils;

import java.lang.reflect.Field;

/**
 * Created by Xingjie
 * on 2018/3/26.
 */
public class ContentIntroductionLayout extends AppCompatTextView  {

    private SpannableString sSIcon;
    private static final String SS_LAB = "#&&#";
    private Context mContext;
    private static final int MAX_LINES = 3;
    private  SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder() ;

    public ContentIntroductionLayout(Context context) {
        this(context, null);
    }

    public ContentIntroductionLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContentIntroductionLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        setTextSize(15);
        setTextColor(0xff525566);
        setMaxLines(MAX_LINES);
        setEllipsize(TextUtils.TruncateAt.END);
        setLineSpacing(DensityUtils.dp2px(mContext, 6), getLineSpacingMultiplier());
        setPadding(DensityUtils.dp2px(mContext, 8), DensityUtils.dp2px(mContext, 6),
                DensityUtils.dp2px(mContext, 15), DensityUtils.dp2px(mContext, 15));
        setBackgroundColor(0xfff5f5f5);
    }

    public void updateView(String content) {
        if (TextUtils.isEmpty(content)) {
            setVisibility(GONE);
        } else {
            setVisibility(VISIBLE);

            sSIcon = new SpannableString(SS_LAB);

            //先设置已获取的资源，占位，不然等待图片资源下载完再设置会突然闪一下
            sSIcon.setSpan(new CenteredImageSpan(mContext, R.drawable.icon_moment),
                    0, SS_LAB.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            setText("");
            append(sSIcon);
            append(" ");
            append(content);

            LogUtils.e("twj123", getLineCount());
           /* if (getLineCount() > MAX_LINES){
                // this returns _1 past_ the index of the last character shown
                // on the indicated line. the lines are zero indexed, so the last
                // valid line is maxLines -1;
                int lastCharShown = getLayout().getLineVisibleEnd(MAX_LINES - 1);
                // chop off some characters. this value is arbitrary, i chose 3 just
                // to be conservative.
                int numCharsToChop = 3;
                String truncatedText = content.substring(0, lastCharShown - numCharsToChop);
                // ellipsize! note ellipsis character.
                setText(truncatedText+"…");
                // reapply the span, since the text has been changed.
                //spannable.setSpan(boldSpan, 10, 15, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            }else {
                setText("");
                append(sSIcon);
                append(" ");
                append(content);
            }*/
/*
            ImageLoaderFactory.get().with(getContext())
                    .load(entity.url)
                    .into(new BitmapTarget() {
                        @Override
                        public void onResourceReady(Bitmap bitmap) {
                            //重新设置大小，否则图片是三倍，在720手机上图会放大
                            int size = DensityUtils.dp2px(mContext, 16);
                            bitmap = BitmapUtils.resize(bitmap, size, size);
                            sSIcon.setSpan(new CenteredImageSpan(mContext, bitmap),
                                    0, SS_LAB.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                            setText("");
                            append(sSIcon);
                            append(" ");
                            append(entity.content);
                        }

                        @Override
                        public void onLoadFailed(Exception e) {
                            setText(entity.content);
                        }
                    });*/
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            CharSequence charSequence = getText() ;
            int lastCharDown = getLayout().getLineVisibleEnd(MAX_LINES - 1) ;
            if (charSequence.length() > lastCharDown){
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder() ;
                spannableStringBuilder.append(charSequence.subSequence(0, lastCharDown - 2)).append("...") ;
                setText(spannableStringBuilder);
            }
        }catch (Exception e){

        }
        super.onDraw(canvas);
    }

    /*@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        StaticLayout layout = null;
        Field field = null;
        try {
            Field staticField = DynamicLayout.class.getDeclaredField("sStaticLayout");
            staticField.setAccessible(true);
            layout = (StaticLayout) staticField.get(DynamicLayout.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (layout != null) {
            try {
                field = StaticLayout.class.getDeclaredField("mMaximumVisibleLineCount");
                field.setAccessible(true);
                field.setInt(layout, getMaxLines());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (layout != null && field != null) {
            try {
                field.setInt(layout, Integer.MAX_VALUE);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }*/
}