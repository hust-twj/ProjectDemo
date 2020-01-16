package com.hust_twj.zademo.bitmap_opti;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;


public class BitmapSampleSizeActivity extends Activity {

    private ImageView mIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bitmap_sample_size);

        mIv = findViewById(R.id.iv_image);

    }

    public void loadImage(View view) {

        LogUtils.e("twj124", mIv.getWidth() + "  " + mIv.getHeight());

        //加载原图，消耗内存139M-->206M-->108M
        mIv.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.world_map,mIv.getWidth(),mIv.getHeight()));
       /* BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.world_map, options);

        options.inSampleSize = calculateInSampleSize(options, mIv.getWidth(), mIv.getHeight());
        options.inJustDecodeBounds = false;

        //Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        mIv.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.world_map, options));*/

        /*try {
            //InputStream inputStream = getAssets().open("world_map.jpg");

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(), R.drawable.world_map, options);

            options.inSampleSize= calculateInSampleSize(options, mIv.getWidth(), mIv.getHeight());
            options.inJustDecodeBounds = false;

            //Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            mIv.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.world_map, options));

        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    /**
     * 图片资源的采样
     * @param res
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小  
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //解析图片
        BitmapFactory.decodeResource(res, resId, options);
        // 调用上面定义的方法计算inSampleSize值，采样图片  
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片 
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * 获取采样值
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        //图片的原始宽高
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        if (width > reqWidth || height > reqHeight) {
            int halfWidth = width / 2;
            int halfHeight = height / 2;
            while ((halfWidth / inSampleSize >= reqWidth) && (halfHeight / inSampleSize >= reqHeight)) {
                inSampleSize *= 2;
            }
        }

        LogUtils.e("twj124", width + "  " + height + "  " + reqWidth + "  " +
                reqHeight + "  " + inSampleSize);

        return inSampleSize;
    }


}
