package com.example.app_ban_sach.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_ban_sach.R;
import com.example.app_ban_sach.model.sachcu;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class TimKiemAdapter extends RecyclerView.Adapter<TimKiemAdapter.MyViewHolder> {
    Context context;
    List<sachcu> array;

    public TimKiemAdapter(Context context, List<sachcu> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kinh_te, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        sachcu sachcu = array.get(position);
        holder.txt_tenKT.setText(sachcu.getTensach());
        holder.txt_tacgiaKT.setText(sachcu.getTentacgia());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txt_giaKT.setText(decimalFormat.format(sachcu.getGia())+ " VNĐ");
        holder.txt_motaKT.setMaxLines(2);
        holder.txt_motaKT.setEllipsize(TextUtils.TruncateAt.END);
        holder.txt_motaKT.setText(sachcu.getMota());
        Picasso.get().load(sachcu.getHinhanh())
                .placeholder(R.drawable.ic_load)                // Khi đang loat
                .error(R.drawable.ic_load_img_erro)             // Nếu lỗi không ra ảnh
                .into(holder.img_anhKT);                        // Khi loat xong
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_tenKT,txt_tacgiaKT, txt_giaKT, txt_motaKT;
        ImageView img_anhKT;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_tenKT = itemView.findViewById(R.id.tv_kinhte_ten);
            txt_tacgiaKT = itemView.findViewById(R.id.tv_kinhte_tacgia);
            txt_giaKT = itemView.findViewById(R.id.tv_kinhte_gia);
            txt_motaKT = itemView.findViewById(R.id.tv_kinhte_mota);
            img_anhKT = itemView.findViewById(R.id.image_kinhte);
        }
    }
}


























