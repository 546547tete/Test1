package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.BeanDao;
import com.example.test1.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<BeanDao> list;

    public HomeAdapter(Context context, List<BeanDao> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rcy, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BeanDao dao = list.get(position);
        ViewHolder holder1= (ViewHolder) holder;
        holder1.tv_rcy_name.setText(dao.getName());
        holder1.img_rcy_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
