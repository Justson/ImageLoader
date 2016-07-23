package com.ucmap.imageutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.ucmap.imageutils.ImageUtils.DoubleCache;
import com.ucmap.imageutils.ImageUtils.ImageCache;
import com.ucmap.imageutils.ImageUtils.ImageLoader;

/**
 * 作者:Justson
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView image = (ImageView) this.findViewById(R.id.image);

        ImageCache cache = new DoubleCache(this);
        ImageLoader loader = new ImageLoader();
        loader.setImageCache(cache);

        loader.onDisplayImage(image, "http://a.hiphotos.baidu.com/image/pic/item/7dd98d1001e9390191637f187eec54e736d196b7.jpg");

    }
}
