package com.example.test1;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.HomeAdapter;
import com.example.bean.BeanDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rcy;
    private ImageView img;
    private HomeAdapter homeAdapter;
    private List<BeanDao> all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initData() {
        BeanDao dao1 = new BeanDao(0,"扫地","1",false);
        BeanDao dao2 = new BeanDao(1,"洗衣服","1",false);
        BeanDao dao3 = new BeanDao(2,"做饭","1",false);
        BeanDao dao4 = new BeanDao(3,"洗澡","1",false);
        DBHolper.getInstance().insert(dao1);
        DBHolper.getInstance().insert(dao2);
        DBHolper.getInstance().insert(dao3);
        DBHolper.getInstance().insert(dao4);
        all = DBHolper.getInstance().queryAll();
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        rcy = (RecyclerView) findViewById(R.id.rcy);
        rcy.setLayoutManager(new LinearLayoutManager(this));
        rcy.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        homeAdapter = new HomeAdapter(this, all);
        rcy.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
    }
}
