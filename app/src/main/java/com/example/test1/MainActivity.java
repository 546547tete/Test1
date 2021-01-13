package com.example.test1;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
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
    private int login_type;
    private static final String LOGIN_TYPE = "login_type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                if (queryAllno.size() < 1) {
                    Toast.makeText(this, "您还没有添加过任务，请添加任务", Toast.LENGTH_SHORT).show();
                } else {
                    for (BeanDao beanDao : queryAllno) {
                        if (beanDao.getType().equals("1")) {
                            listno.add(beanDao);
                            homeAdapter.setData(listno);
                        }
                    }
                }
                break;
            case R.id.menu_yes:
                List<BeanDao> queryAllyes = DBHolper.getInstance().queryAll();
                ArrayList<BeanDao> listyes = new ArrayList<>();
                if (queryAllyes.size() < 1) {
                    Toast.makeText(this, "您还没有添加过任务，请添加任务", Toast.LENGTH_SHORT).show();
                } else {
                    for (BeanDao beanDao : queryAllyes) {
                        if (beanDao.getType().equals("2")) {
                            listyes.add(beanDao);
                            homeAdapter.setData(listyes);
                        }
                    }
                }
                break;
            case R.id.menu_delete:
                List<BeanDao> alldelete = DBHolper.getInstance().queryAll();
                List<BeanDao> beanDaosdelete = new ArrayList<>();
                if (alldelete.size() < 1) {
                    Toast.makeText(this, "您还没有添加过任务，请添加任务", Toast.LENGTH_SHORT).show();
                } else {
                    for (BeanDao beanDao : alldelete) {
                        if (beanDao.getType().equals("3")) {
                            beanDaosdelete.add(beanDao);
                            homeAdapter.setData(beanDaosdelete);
                        } else {
                            Toast.makeText(this, "没有删除过的任务", Toast.LENGTH_SHORT).show();
                            break;
                        }
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
                homeAdapter.setData(all);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        all = DBHolper.getInstance().queryAll();
        homeAdapter.setData(all);
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

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPop();
            }
        });

        //先实例化Callback
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(homeAdapter);
//用Callback构造ItemtouchHelper
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
//调用ItemTouchHelper的attachToRecyclerView方法建立联系
        touchHelper.attachToRecyclerView(rcy);
    }

    private void initPop() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_popup, null);
        final PopupWindow popupWindow = new PopupWindow(inflate, 600, 400);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(inflate, Gravity.CENTER, 0, 0);

        final EditText et_pop = inflate.findViewById(R.id.et_pop);
        Button btn_ok = inflate.findViewById(R.id.btn_ok);
        Button btn_no = inflate.findViewById(R.id.btn_no);
        alpha(0.5f);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = et_pop.getText().toString().trim();
                BeanDao beanDao = new BeanDao(null, trim, "1", false);
                DBHolper.getInstance().insert(beanDao);
                List<BeanDao> all = DBHolper.getInstance().queryAll();
                ArrayList<BeanDao> beanDaos = new ArrayList<>();
                for (BeanDao beanDaod : all) {
                    if (!beanDao.getType().equals("3")) {
                        beanDaos.add(beanDaod);
                    }
                }
                homeAdapter.setData(beanDaos);
                alpha(1);
                popupWindow.dismiss();
            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alpha(1);
                popupWindow.dismiss();
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                alpha(1);
            }
        });

    }

    public void showKeyboard(EditText editText) {
        if (editText != null) {
            //设置可获得焦点
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            //请求获得焦点
            editText.requestFocus();
            //调用系统输入法
            InputMethodManager inputManager = (InputMethodManager) editText
                    .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(editText, 0);
        }
    }

    public void alpha(float alpha) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = alpha;

        getWindow().setAttributes(attributes);
    }
}
