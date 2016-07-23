package com.ucmap.imageutils.ImageUtils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Administrator on 2016/7/23.
 * Justson  实现 内存缓存
 */
public class MemoryCache implements ImageCache {
    private static final LruCache<String, Bitmap> lruCache;

    static {
        lruCache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory() / 8)) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return lruCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        lruCache.put(url, bitmap);
    }
}
