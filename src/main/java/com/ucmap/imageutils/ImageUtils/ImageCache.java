package com.ucmap.imageutils.ImageUtils;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/7/23.
 * Justson
 */
public interface ImageCache {

    Bitmap getBitmap(String url);

    void putBitmap(String url, Bitmap bitmap);

}
