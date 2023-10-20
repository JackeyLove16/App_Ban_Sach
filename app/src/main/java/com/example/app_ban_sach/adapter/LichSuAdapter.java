package com.example.app_ban_sach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_ban_sach.R;
import com.example.app_ban_sach.model.DonHang;

import java.util.List;

public class LichSuAdapter extends RecyclerView.Adapter<LichSuAdapter.MyViewHolder> {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    Context context;
    List<DonHang> listDonHang;

    public LichSuAdapter(Context context, List<DonHang> listDonHang) {
        this.context = context;
        this.listDonHang = listDonHang;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lichsu, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DonHang donHang = listDonHang.get(position);
        holder.txtLichSu.setText("Mã đơn hàng: "+donHang.getId());
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.recyclerViewChiTiet.getContext(), LinearLayoutManager.VERTICAL,false);
        layoutManager.setInitialPrefetchItemCount(donHang.getItem().size());
        ChiTietAdapter chiTietAdapter = new ChiTietAdapter(context,donHang.getItem());
        holder.recyclerViewChiTiet.setLayoutManager(layoutManager);
        holder.recyclerViewChiTiet.setAdapter(chiTietAdapter);
        holder.recyclerViewChiTiet.setRecycledViewPool(viewPool);

    }

    @Override
    public int getItemCount() {
        return listDonHang.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtLichSu;
        RecyclerView recyclerViewChiTiet;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLichSu = itemView.findViewById(R.id.tv_itemlichsu_iddonhang);
            recyclerViewChiTiet = itemView.findViewById(R.id.recyclerview_itemlichsu);
        }
    }

}









