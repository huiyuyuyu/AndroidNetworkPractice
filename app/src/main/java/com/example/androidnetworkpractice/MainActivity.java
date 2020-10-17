package com.example.androidnetworkpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    String[] strings = new String[]{
            "Gallery加载网络图片", "Intent的测试"
    };
    Class[] classes = new Class[]{
            GalleryActivity.class, IntentTestActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        //button_click();
    }

    private void button_click() {
        Intent intent = new Intent();
        intent.setAction("ddd");
        intent.addCategory("ddd");
        intent.setDataAndType(Uri.parse("ddd"), "ddd");
        intent.setComponent(new ComponentName(this, GalleryActivity.class));
        startActivity(intent);

        // 查询
        getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
    }

    private void initView() {
        listView = findViewById(R.id.listView);
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, classes[position]));
            }
        });
    }
}