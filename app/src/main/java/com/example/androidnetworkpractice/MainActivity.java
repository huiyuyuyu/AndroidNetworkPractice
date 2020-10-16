package com.example.androidnetworkpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
            "Gallery加载网络图片"
    };
    Class[] classes = new Class[]{
            GalleryActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
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