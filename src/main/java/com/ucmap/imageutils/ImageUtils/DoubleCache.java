package com.ucmap.imageutils.ImageUtils;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/7/23.
 * Justson
 */
public class DoubleCache implements ImageCache {

    private final MemoryCache memoryCache;
    private final DiskCache diskCache;

    public DoubleCache(Context context) {
        memoryCache = new MemoryCache();
        diskCache = new DiskCache(context);
    }

    @Override
    public Bitmap getBitmap(String url) {
        Bitmap bitmap = memoryCache.getBitmap(url);
        if (bitmap == null) {
            bitmap = diskCache.getBitmap(url);
        }

        return bitmap;
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        memoryCache.putBitmap(url, bitmap);
        diskCache.putBitmap(url, bitmap);
    }
}
