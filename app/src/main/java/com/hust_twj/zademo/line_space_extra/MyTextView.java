package com.hust_twj.zademo.line_space_extra;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.hust_twj.zademo.R;


public class MyTextView extends AppCompatTextView {

	private Paint mPaint;
	private Rect  mRect;

	private int baseLineColor;
	private int boundsColor;
	private int textBottomColor;

	public MyTextView(Context context) {
		this(context, null);
	}

	public MyTextView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.STROKE);

		mRect = new Rect();
		baseLineColor = getResources().getColor(R.color.baseline);
		boundsColor = getResources().getColor(R.color.bounds);
		textBottomColor = getResources().getColor(R.color.text_bottom_line);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int count = getLineCount();

		for (int i = 0; i < count; i++) {
			int baseline = getLineBounds(i, mRect);

			mPaint.setColor(baseLineColor);
			canvas.drawLine(mRect.left, baseline, mRect.right, baseline, mPaint);

			mPaint.setColor(textBottomColor);
			canvas.drawLine(mRect.left, baseline + getPaint().getFontMetricsInt().descent, mRect.right, baseline + getPaint().getFontMetricsInt().descent, mPaint);

			mPaint.setColor(boundsColor);
			canvas.drawRect(mRect, mPaint);
		}

	}
}
