package com.hust_twj.zademo.view.scroller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.hust_twj.zademo.R;

/**
 * @author hust_twj
 * @date 2021/2/20
 */
public class ScrollerView extends View {

    private Bitmap bitmap;
    private Scroller scroller;

    public ScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        scroller = new Scroller(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, null);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                scroller.startScroll(10, 10, 10, 10);
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        Log.e("twj124", "ScrollerView:" + scroller.computeScrollOffset() + "  " + scroller.getCurrX() + "  " + scroller.getCurrY());
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
        }

    }
}