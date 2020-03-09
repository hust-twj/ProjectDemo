package com.hust_twj.zademo.widget;

import android.content.Context;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.DensityUtils;
import com.hust_twj.zademo.utils.StringUtils;


/**
 * @link HeartWordTextView
 * Created by wenjing.tang on 2018/3/27.
 */

public class AutoFitTextView extends RelativeLayout {

    public TextView mContentTv1, mContentTv2, mContentCountTv;
    public RelativeLayout rlLayout, mContentTv2RlLayout;
    public ImageView heartWordLogo;
    private View mLayoutWordCount;

    private Context mContext;

    /**
     * @param context
     */
    public AutoFitTextView(Context context) {
        this(context, null);
    }

    public AutoFitTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoFitTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_auto_fit_text_view, this, true);
        mContentTv1 = findViewById(R.id.textview_up);
        mContentTv2 = findViewById(R.id.textview_down);
        rlLayout = findViewById(R.id.rl_layout);
        mContentTv2RlLayout = findViewById(R.id.textview_down_rl);
        mContentCountTv = findViewById(R.id.word_count);
        heartWordLogo = findViewById(R.id.icon_heart_word);
        mLayoutWordCount = findViewById(R.id.word_count_layout);
    }

    public void setContent(String content) {
        if (!StringUtils.isEmpty(content) && !StringUtils.isEmpty(mContentTv1.getText().toString())
                && content.startsWith(mContentTv1.getText().toString())) {
            return;
        }
        mContentTv1.setText(content);

        Paint paint = mContentTv1.getPaint();
        float totalWidth = paint.measureText(content) + 0.5f;
        float width = DensityUtils.getScreenWidth(mContext) - DensityUtils.dp2px(mContext, 73);
        int firstLineCount = paint.breakText(content, true, width, null);

        int line = (int) Math.ceil(totalWidth / width);


        boolean has3Lines = false;
        if (line > 2) {
            has3Lines = true;
        }
        if (has3Lines) { //3行以上的情况
            mContentTv2RlLayout.setVisibility(VISIBLE);
            mLayoutWordCount.setVisibility(VISIBLE);
            int startIndex = 0;
            int endIndex = firstLineCount;
            String strText = "";
            if (content.length() > endIndex - startIndex) {
                strText = content.subSequence(startIndex, endIndex).toString();
                final String expanedStr = content.length() > strText.length() ? content.substring(strText.length()) : "";
                mContentCountTv.setText(mContext.getString(R.string.all_text_count_left, content.length()));
                mContentTv1.setText(strText);
                mContentTv1.setMaxLines(1);
                mContentTv2.setText(expanedStr);
                rlLayout.setVisibility(View.VISIBLE);
                heartWordLogo.setPadding(0, 0, 0, DensityUtils.dp2px(mContext, 34));
            } else {
                rlLayout.setVisibility(View.GONE);
            }

        } else {
            if (line == 2) { //2行的情况
                if (firstLineCount == content.length()) {
                    heartWordLogo.setPadding(0, 0, 0, DensityUtils.dp2px(mContext, 34) - DensityUtils.sp2px(mContext, 22));
                } else {
                    String contentLine2 = content.replace(content.substring(0, firstLineCount),
                            content.substring(0, firstLineCount) + "\n");
                    heartWordLogo.setPadding(0, 0, 0, DensityUtils.dp2px(mContext, 34));
                    mContentTv1.setText(contentLine2);
                    mContentTv1.setMaxLines(2);
                }
            } else if (line == 1) { //1行的情况
                heartWordLogo.setPadding(0, 0, 0, DensityUtils.dp2px(mContext, 34) - DensityUtils.sp2px(mContext, 22));
//                holder.mContentTv1.setMaxLines(1);
            }
            mContentTv2RlLayout.setVisibility(View.GONE);
        }
    }
}
