package com.example.androidnetworkpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class IntentTestActivity extends AppCompatActivity {
    Button button;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_test);

        initView();
    }

    private void initView() {
        button = findViewById(R.id.button);
    }

    /**
     * 打开设置
     * @param view
     */
    public void onClick(View view) {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        // 在启动之前，需要进行查询
        // 方式一
        startActivity(intent);
    }

    /**
     * 打开图库
     * @param view
     */
    public void onClick1(View view) {
        Intent intent = new Intent();
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivity(intent);
    }

    /**
     * 打开相机拍照
     * @param view
     */
    public void onClick2(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    /**
     * 打开支付宝
     * https://blog.csdn.net/qq_30875213/article/details/95373480
     * @param view
     */
    public void onClick3(View view) {
        //调起支付宝
        String aliPayPackageName = "com.eg.android.AlipayGphone";
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        ComponentName cn = new ComponentName(aliPayPackageName, "com.alipay.mobile.quinox.splash.ShareDispenseActivity");
        intent.setComponent(cn);
        startActivity(intent);
        /**
        if(AppUtils.isApplicationAvilible(getSelfActivity(),aliPayPackageName)){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName(aliPayPackageName, "com.alipay.mobile.quinox.splash.ShareDispenseActivity");
            intent.setComponent(cn);
            startActivity(intent);
        }else {
            Toast.makeText(getSelfActivity(), "您没有安装支付宝，请先安装", Toast.LENGTH_SHORT);
            //ToastUtils.show("您没有安装支付宝，请先安装");
        }*/
    }

    private Context getSelfActivity() {
        return this;
    }

    /**
     * 启动另外一个应用
     * @param view
     */
    public void onClick4(View view) {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.example.applicationb",
                "com.example.applicationb.MainActivity2");
        intent.setComponent(componentName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * 如何获取pkg和cls
     * https://blog.csdn.net/u010844304/article/details/52950909
     * Android 打开本地第三方应用，如QQ,微博，微信等
     * @param view
     */
    public void onClick5(View view) {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager mPackageManager = this.getPackageManager();
        List<ResolveInfo> mAllApps = mPackageManager.queryIntentActivities(mainIntent, 0);
        //按包名排序
        Collections.sort(mAllApps, new ResolveInfo.DisplayNameComparator(mPackageManager));

        for(ResolveInfo res : mAllApps){
            //该应用的包名和主Activity
            String pkg = res.activityInfo.packageName;
            String cls = res.activityInfo.name;
            Log.d("pkg_cls","pkg---" +pkg +"  cls---" + cls );
        }
    }
}