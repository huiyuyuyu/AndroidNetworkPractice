package com.example.androidnetworkpractice;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GalleryActivity extends Activity {
    private Gallery myGallery01;
    /* 地址栏字符串 */
    private String[] myImageURL = new String[]{
            "http://b236.photo.store.qq.com/psb?/"
                    + "V14NjoEt1VPfcS/vJ1LfTwqLKvNO2BB0PinvINjCSdCV3RcE4BhdhAD3Z4!/b/dFr6toyNCAAA&bo=GgNIAgAAAAABB3M!&rf=viewer_4",
            "http://b241.photo.store.qq.com/psb?/"
                    + "V14NjoEt1VPfcS/*vD8rcyIXKGzlpNd8UPb45YdQrZy5DlNh*OWgtMAsDs!/b/dJvMqI8ACAAA&bo=*gKAAgAAAAABB14!&rf=viewer_4",
            "http://b239.photo.store.qq.com/psb?/"
                    + "V14NjoEt1VPfcS/Xm942*QSSEV2kCwETcv9e4PHFtRcWrs1OnaTomAR.jM!/b/dHu8gI58CAAA&bo=IANYAgAAAAABB1k!&rf=viewer_4",
            "http://b27.photo.store.qq.com/http_imgload.cgi?/"
                    + "rurl4_b=2a9dcf1fd909a7ed3ce8951f73860898bb7ff57a8cb7747c9f0eb6a02124850b709c0b86f086a4ba5653eeb71dd4b01e4a58f407e2eec9433cd8d4bc0b88fda56260c2c8beb34ebab77b610c7131393f82e774ef&a=27&b=27",
            "http://b27.photo.store.qq.com/http_imgload.cgi?/"
                    + "rurl4_b=2a9dcf1fd909a7ed3ce8951f73860898158d252489f84e7d2a83d44c01b7bb12b2c19ca0efdd555dba788407fd01e9de45524b11a9793f532624197bc8d14c84ae78ddebafe4357e4eedc60e9e510224367490bf&a=27&b=27"
    };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        myGallery01 = (Gallery) findViewById(R.id.gallery);
        myGallery01.setAdapter(new myInternetGalleryAdapter(this));
    }

    /* 用BaseAdapter */
    public class myInternetGalleryAdapter extends BaseAdapter {
        /* 类成员myContext Context对象 */
        private Context myContext;

        /*构造器自由一个参数，即要存储的Context */
        public myInternetGalleryAdapter(Context c) {
            this.myContext = c;
        }

        /* 返回全部已定义图片的总量 */
        public int getCount() {
            return myImageURL.length;
        }

        /* 使用getItem方法获取当前容器中图像数的数组ID */
        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        /* 根据中央位移量，利用getScale返回views的大小(0.0f to 1.0f) */
        public float getScale(boolean focused, int offset) {
            /* Formula: 1 / (2 ^ offset) */
            return Math.max(0, 1.0f / (float) Math.pow(2, Math
                    .abs(offset)));
        }

        /* 获取当前要显示的图像View，传入数组ID值使之读取并成像处理 */
        @Override
        public View getView(int position, View convertView,
                            ViewGroup parent) {
            // TODO Auto-generated method stub
            /* 创建ImageView对象*/

            final ImageView imageView = new ImageView(this.myContext);
            /* new URL将对象网址传入 */

            final int position1 = position;

            new Thread() {
                @Override
                public void run() {
                    try {
                        final URL aryURI = new URL(myImageURL[position1]);
                        /* 获取连接 */
                        URLConnection conn = aryURI.openConnection();
                        conn.connect();
                        /* 获取返回的InputStream */
                        InputStream is = conn.getInputStream();
                        /* 将InputStream变为Bitmap */
                        final Bitmap bm = BitmapFactory.decodeStream(is);
                        /* 关闭InputStream */
                        is.close();
                        /* 设置Bitmap到ImageView中 */
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bm);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            /* 设置ImageView的宽和高，单位是dip 。*/
            imageView.setLayoutParams(new Gallery.LayoutParams(800, 600));
            return imageView;
        }
    }
}