package com.hust_twj.zademo.moments_2_0.hot_topic.widget;

import android.content.Context;

import androidx.appcompat.widget.AppCompatTextView;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.CenteredImageSpan;
import com.hust_twj.zademo.utils.DensityUtils;
import com.hust_twj.zademo.utils.LogUtils;

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

            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
                @Override
                public void onGlobalLayout(){
                    ViewTreeObserver viewTreeObserver = getViewTreeObserver();
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                    LogUtils.e("twj123", getLineCount());
                   /* if (getLineCount() > MAX_LINES)
                    {
                        int endOfLastLine = getLayout().getLineEnd(MAX_LINES - 1);
                        String newVal = getText().subSequence(0, endOfLastLine - 3) + "...";
                        setText(newVal);
                    }*/
                   //总行数多余3行，显示" ... "
                   if (getLineCount() > MAX_LINES){
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
                   }


                }
            });

        }
    }

 /*   @Override
    protected void onDraw(Canvas canvas) {
        LogUtils.e("twj123", getLineCount());
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
    }*/


}