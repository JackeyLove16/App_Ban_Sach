package com.example.app_ban_sach.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app_ban_sach.R;
import com.example.app_ban_sach.activity.ThongTin_Activity;
import com.example.app_ban_sach.model.sachmoi;
import com.example.app_ban_sach.ultil.checkConnect;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class sachmoiAdapter extends RecyclerView.Adapter<sachmoiAdapter.MyViewHolder> {

    Context context;
    List<sachmoi> array;

    public sachmoiAdapter(Context context, List<sachmoi> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach_moi, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        sachmoi sachmoi = array.get(position);
        holder.txtten.setText(sachmoi.getTensach());
        holder.txttacgia.setText(sachmoi.getTentacgia());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgia.setText("Giá: " + decimalFormat.format(sachmoi.getGia()) + " VNĐ");
        // Lấy đường link cho ảnh ta sử dụng thư viện picasso
        Picasso.get().load(sachmoi.getHinhanh())
                .placeholder(R.drawable.ic_load)                                    // Khi ảnh đang loat
                .error(R.drawable.ic_load_img_erro)                                 // Khi không loat được
                .into(holder.imghinhanh);                                           // Khi loat được
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtten, txttacgia, txtgia;
        ImageView imghinhanh;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.itemsach_tensach);
            txttacgia = itemView.findViewById(R.id.itemsach_tacgia);
            txtgia = itemView.findViewById(R.id.itemsach_gia);
            imghinhanh = itemView.findViewById(R.id.itemsach_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ThongTin_Activity.class);
                    intent.putExtra("thongtinsach", array.get(getLayoutPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    checkConnect.ShowToast(context, array.get(getLayoutPosition()).getTensach());
                    context.startActivity(intent);
                }
            });
        }
    }
}



















