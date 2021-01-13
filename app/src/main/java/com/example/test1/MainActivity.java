package com.example.test1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.HomeAdapter;
import com.example.bean.BeanDao;

import java.util.ArrayList;
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_no:
                List<BeanDao> queryAllno = DBHolper.getInstance().queryAll();
                ArrayList<BeanDao> listno = new ArrayList<>();
                for (BeanDao beanDao : queryAllno) {
                    if (beanDao.getType().equals("1")){
                        listno.add(beanDao);
                       homeAdapter.setData(listno);
                    }
                }
                break;
            case R.id.menu_yes:
                List<BeanDao> queryAllyes = DBHolper.getInstance().queryAll();
                ArrayList<BeanDao> listyes = new ArrayList<>();
                for (BeanDao beanDao : queryAllyes) {
                    if (beanDao.getType().equals("2")){
                        listyes.add(beanDao);
                        homeAdapter.setData(listyes);
                    }
                }
                break;
            case R.id.menu_delete:
                List<BeanDao> alldelete = DBHolper.getInstance().queryAll();
                ArrayList<BeanDao> beanDaosdelete = new ArrayList<>();
                for (BeanDao beanDao : alldelete) {
                    if (beanDao.getType().equals("3")){
                        beanDaosdelete.add(beanDao);
                        homeAdapter.setData(beanDaosdelete);
                    }else {
                        Toast.makeText(this, "没有删除过的任务", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                break;
            case R.id.menu_all:
                List<BeanDao> all = DBHolper.getInstance().queryAll();
                ArrayList<BeanDao> beanDaos = new ArrayList<>();
                for (BeanDao beanDao : all) {
                    if (!beanDao.getType().equals("3")){
                        beanDaos.add(beanDao);
                    }
                }
                homeAdapter.setData(beanDaos);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        all = DBHolper.getInstance().queryAll();

        if (all.size()<1){
            BeanDao dao1 = new BeanDao(0,"扫地","1",false);
            BeanDao dao2 = new BeanDao(1,"洗衣服","1",false);
            BeanDao dao3 = new BeanDao(2,"做饭","1",false);
            BeanDao dao4 = new BeanDao(3,"洗澡","1",false);
            DBHolper.getInstance().insert(dao1);
            DBHolper.getInstance().insert(dao2);
            DBHolper.getInstance().insert(dao3);
            DBHolper.getInstance().insert(dao4);
        }

    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        rcy = (RecyclerView) findViewById(R.id.rcy);
        rcy.setLayoutManager(new LinearLayoutManager(this));
        rcy.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        homeAdapter = new HomeAdapter(this);
        homeAdapter.setData(all);
        rcy.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
    }
}
