package com.example.test1;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.PopupWindow;

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


    int out = 0;
    private RecyclerView rcy;
    private ImageView img;
    private HomeAdapter homeAdapter;
    private List<BeanDao> all;
    private BeanDao dao1;
    private int login_type = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SpUtil.setParam("login_type", login_type);
        login_type++;
        initView();
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_no:
                List<BeanDao> queryAllno = DBHolper.getInstance().queryAll();
                ArrayList<BeanDao> listno = new ArrayList<>();
                for (BeanDao beanDao : queryAllno) {
                    if (beanDao.getType().equals("1")) {
                        listno.add(beanDao);
                        homeAdapter.setData(listno);
                    }
                }
                break;
            case R.id.menu_yes:
                List<BeanDao> queryAllyes = DBHolper.getInstance().queryAll();
                ArrayList<BeanDao> listyes = new ArrayList<>();
                for (BeanDao beanDao : queryAllyes) {
                    if (beanDao.getType().equals("2")) {
                        listyes.add(beanDao);
                        homeAdapter.setData(listyes);
                    }
                }
                break;
            case R.id.menu_delete:
                List<BeanDao> alldelete = DBHolper.getInstance().queryAll();
                List<BeanDao> beanDaosdelete = new ArrayList<>();
                for (BeanDao beanDao : alldelete) {
                    if (beanDao.getType().equals("3")) {
                        beanDaosdelete.add(beanDao);
                        homeAdapter.setData(beanDaosdelete);
                    } else {
//                        BeanDao dao2 = new BeanDao((long) 999,"没有删除过的任务","3",false);
//                        beanDaosdelete.add(dao2);
                        Toast.makeText(this, "没有删除过的任务", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                break;
            case R.id.menu_all:
                List<BeanDao> all = DBHolper.getInstance().queryAll();
                ArrayList<BeanDao> beanDaos = new ArrayList<>();
                for (BeanDao beanDao : all) {
                    if (!beanDao.getType().equals("3")) {
                        beanDaos.add(beanDao);
                    }
                }
                homeAdapter.setData(beanDaos);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        int type1 = (int) SpUtil.getParam("login_type", 0);
        Long aLong = Long.valueOf(0);
        List<String> arrayList = new ArrayList<>();
        arrayList.add("扫地");
        arrayList.add("洗衣服");
        arrayList.add("做饭");
        arrayList.add("洗澡");

//        if (type1 == 1) {
//            for (int i = 0; i < arrayList.size(); i++) {
//                dao1 = new BeanDao((long) out, arrayList.get(i), "1", false);
//                DBHolper.getInstance().insert(dao1);
//                out++;
//            }
//        }
        all = DBHolper.getInstance().queryAll();
        homeAdapter.setData(all);
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        rcy = (RecyclerView) findViewById(R.id.rcy);
        rcy.setLayoutManager(new LinearLayoutManager(this));
        rcy.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        homeAdapter = new HomeAdapter(this);
        rcy.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPop();
            }
        });
    }

    private void initPop() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_popup, null);
        final PopupWindow popupWindow = new PopupWindow(inflate, 600, 500);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(inflate, Gravity.CENTER, 0, 0);

        final EditText et_pop = inflate.findViewById(R.id.et_pop);
        Button btn_ok = inflate.findViewById(R.id.btn_ok);
        Button btn_no = inflate.findViewById(R.id.btn_no);


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = et_pop.getText().toString().trim();
                out++;
                BeanDao beanDao = new BeanDao((long) out, trim, "1", false);
                DBHolper.getInstance().insert(beanDao);
                popupWindow.dismiss();
            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }
}
