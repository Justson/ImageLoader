package com.ucmap.imageutils.ImageUtils;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/7/23.
 * 作者：Justson
 */
public class MessengerBean {

    private Object arg1;
    private Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    private ImageView imageView;

    public Object getArg1() {
        return arg1;
    }

    public void setArg1(Object arg1) {
        this.arg1 = arg1;
    }
}
