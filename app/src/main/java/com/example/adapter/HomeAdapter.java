package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bean.BeanDao;
import com.example.test1.DBHolper;
import com.example.test1.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<BeanDao> list;

    public HomeAdapter(Context context) {
        this.context = context;

    }

    public void setData(List<BeanDao> list){
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rcy, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final BeanDao bean = list.get(position);
        final ViewHolder holder1= (ViewHolder) holder;
        holder1.tv_rcy_name.setText(bean.getName());
        holder1.img_rcy_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.setChick(!bean.getChick());
                if (bean.getChick()){
                    BeanDao beanDao = list.get(position);
                    beanDao.setType("2");
                    DBHolper.getInstance().upData(beanDao);
                    notifyDataSetChanged();
                    Glide.with(context).load(R.drawable.yes).into(holder1.img_rcy_check);
                }else {
                    BeanDao beanDao2 = list.get(position);
                    beanDao2.setType("1");
                    DBHolper.getInstance().upData(beanDao2);
                    notifyDataSetChanged();
                    Glide.with(context).load(R.drawable.eye).into(holder1.img_rcy_check);
                }
            }
        });
        if (bean.getChick()){
            Glide.with(context).load(R.drawable.yes).into(holder1.img_rcy_check);
        }else {
            Glide.with(context).load(R.drawable.eye).into(holder1.img_rcy_check);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_rcy_name;
        public ImageView img_rcy_check;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_rcy_name = (TextView) rootView.findViewById(R.id.tv_rcy_name);
            this.img_rcy_check = (ImageView) rootView.findViewById(R.id.img_rcy_check);
        }

    }
}
