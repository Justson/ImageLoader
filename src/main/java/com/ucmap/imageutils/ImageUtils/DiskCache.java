package com.ucmap.imageutils.ImageUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2016/7/23.
 * Justson
 */
public class DiskCache implements ImageCache {


    public DiskCache(Context context) {
        diskUri = context.getExternalCacheDir().getAbsolutePath();
        Log.i("Info", "disUrl:" + diskUri);
    }

    private static String diskUri;

    @Override
    public Bitmap getBitmap(String url) {
        Log.i("Info", "Url:" + diskUri + File.separator + url);
        return BitmapFactory.decodeFile(Uri.decode(diskUri + File.separator + url));
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        Log.i("Info", "Url:" + diskUri + File.separator + url);
        File file = null;
        OutputStream os = null;
        try {
            file = new File(diskUri, url);
            file.createNewFile();
            os = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
