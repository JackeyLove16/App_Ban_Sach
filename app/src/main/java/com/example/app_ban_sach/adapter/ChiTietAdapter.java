package com.example.app_ban_sach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app_ban_sach.R;
import com.example.app_ban_sach.model.Item;

import java.util.List;

public class ChiTietAdapter extends RecyclerView.Adapter<ChiTietAdapter.MyViewHolder> {
    Context context;
    List<Item> itemList;

    public ChiTietAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lichsu_chitiet, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.txtTen.setText(item.getTensach() + "");
        holder.txtSoLuong.setText("Số lượng: " + item.getSoluong()+"");
        Glide.with(context).load(item.getHinhanh()).into(holder.imageChiTiet);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageChiTiet;
        TextView txtTen, txtSoLuong;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageChiTiet = itemView.findViewById(R.id.img_itemlichsuchitiet);
            txtTen = itemView.findViewById(R.id.tv_itemlichsuchitiet_tensach);
            txtSoLuong = itemView.findViewById(R.id.tv_itemlichsuchitiet_soluong);
        }
    }
}
