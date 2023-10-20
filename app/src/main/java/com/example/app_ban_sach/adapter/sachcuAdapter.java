package com.example.app_ban_sach.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_ban_sach.R;
import com.example.app_ban_sach.activity.ThongTinCu_Activity;
import com.example.app_ban_sach.model.EventBus.EventSuaXoa;
import com.example.app_ban_sach.model.sachcu;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.List;

public class sachcuAdapter extends RecyclerView.Adapter<sachcuAdapter.MyViewHolder> {
    Context context;
    List<sachcu> array;

    public sachcuAdapter(Context context, List<sachcu> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach_cu, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        sachcu sachcu = array.get(position);
        holder.txtten.setText(sachcu.getTensach());
        holder.txttacgia.setText(sachcu.getTentacgia());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgia.setText("Giá: " + decimalFormat.format(sachcu.getGia()) + " VNĐ");
        // Lấy đường link cho ảnh ta sử dụng thư viện picasso
        Picasso.get().load(sachcu.getHinhanh())
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
            txtten = itemView.findViewById(R.id.itemsachcu_tensach);
            txttacgia = itemView.findViewById(R.id.itemsachcu_tacgia);
            txtgia = itemView.findViewById(R.id.itemsachcu_gia);
            imghinhanh = itemView.findViewById(R.id.itemsachcu_image);
            itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                    contextMenu.add(0,0, getAdapterPosition(),"Sửa");
                    contextMenu.add(0,1, getAdapterPosition(),"Xoá");
                    EventBus.getDefault().postSticky(new EventSuaXoa(array.get(getLayoutPosition())));
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ThongTinCu_Activity.class);
                    intent.putExtra("thongtinsachcu", array.get(getLayoutPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }

    }
}



























