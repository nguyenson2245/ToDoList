package com.example.todolist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.MainActivityToDoList;
import com.example.todolist.R;
import com.example.todolist.model.CongViec;

import java.util.List;

public class CongViec_Adapter extends BaseAdapter {

    private MainActivityToDoList context;
    private int layout;
    private List<CongViec> congViecList;

    public CongViec_Adapter(MainActivityToDoList context, int layout, List<CongViec> congViecList) {
        this.context = context;
        this.layout = layout;
        this.congViecList = congViecList;
    }

    @Override
    public int getCount() {
        if (congViecList != null) {
            return congViecList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView txtTen;
        ImageView img_Deleter, img_Edit;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.txtTen = (TextView) view.findViewById(com.example.todolist.R.id.textViewTen);
            holder.img_Deleter = (ImageView) view.findViewById(R.id.imageView_deleter);
            holder.img_Edit    = (ImageView) view.findViewById(R.id.imageView_edit);

            view.setTag(holder);    // settag để nó ánh xạ truyền vào holder
        }else {
            holder = (ViewHolder) view.getTag();
        }
        CongViec congViec= congViecList.get(i);
        holder.txtTen.setText(congViec.getTen_CV());

        holder.img_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.Dialog_Sua_CongViec(congViec.getTen_CV(),congViec.getIdC_V());
                Toast.makeText(context,"Sửa "+congViec.getTen_CV(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.img_Deleter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.Dialog_xoa_CV(congViec.getTen_CV(),congViec.getIdC_V());
            }
        });

        return view;
    }


}
