package com.example.test1;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.HomeAdapter;
import com.example.bean.BeanDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<BeanDao> list;

    private RecyclerView rcy;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        HomeAdapter homeAdapter = new HomeAdapter();
        list = new ArrayList<>();

    }

    private void initView() {
        rcy = (RecyclerView) findViewById(R.id.rcy);
        img = (ImageView) findViewById(R.id.img);

    }
}
