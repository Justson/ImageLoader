package com.ucmap.imageutils.ImageUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/7/23.
 * 作者:Justson
 */
public class ImageLoader {

    private Executor executor = Executors.newCachedThreadPool();
    //imageCache
    private ImageCache imageCache;
    //UI线程的Handler
    private static final Handler handler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x33 && msg.obj instanceof MessengerBean) {
                MessengerBean messengerBean = (MessengerBean) msg.obj;
                messengerBean.getImageView().setImageBitmap(messengerBean.getBitmap());
            }
        }
    };

    public void setImageCache(ImageCache imageCache) {
        this.imageCache = imageCache;
    }

    public void onDisplayImage(ImageView imageView, String url) {

        Bitmap bitmap = imageCache.getBitmap(MD5Manager.getMD5(url)); //从缓存中获取Bitmap
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            Log.i("Info", "缓存中存在Bitmap");
        } else { //如果缓存中不存在这张图片， 从网络中下载这张图片
            onLoadBitmap(url, imageView);
            Log.i("Info", "缓存中不存在Bitmap");
        }
    }

    // 下载图片
    private void onLoadBitmap(final String url, final ImageView imageView) {
        executor.execute(new Runnable() {
            InputStream is = null;

            @Override
            public void run() {
                try {

                    URL tUrl = new URL(url);
                    HttpURLConnection httpUrlConnection = (HttpURLConnection) tUrl.openConnection();
                    httpUrlConnection.setRequestMethod("GET");
                    httpUrlConnection.setConnectTimeout(3000);
                    httpUrlConnection.setDoInput(true);
                    httpUrlConnection.connect();
                    is = httpUrlConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    if (bitmap == null)
                        return;
                    imageCache.putBitmap(MD5Manager.getMD5(url), bitmap);
                    Message message = handler.obtainMessage();
                    message.what = 0x33;
                    MessengerBean messengerBean = new MessengerBean();
                    messengerBean.setImageView(imageView);
                    messengerBean.setBitmap(bitmap);
                    message.obj = messengerBean;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        if (is != null)
                            is.close();
                    } catch (Exception ex) {

                    }
                }
            }
        });
    }
}
