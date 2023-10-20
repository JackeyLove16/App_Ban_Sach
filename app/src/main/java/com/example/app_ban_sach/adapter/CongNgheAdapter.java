package com.example.app_ban_sach.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_ban_sach.R;
import com.example.app_ban_sach.model.sachmoi;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CongNgheAdapter extends BaseAdapter{

    Context context;
    ArrayList<sachmoi> arrayCongNghe;

    public CongNgheAdapter(Context context, ArrayList<sachmoi> arrayCongNghe) {
        this.context = context;
        this.arrayCongNghe = arrayCongNghe;
    }

    @Override
    public int getCount() {
        return arrayCongNghe.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayCongNghe.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        public TextView txt_tenCN,txt_tacgiaCN, txt_giaCN, txt_motaCN;
        public ImageView img_hinhanhCN;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CongNgheAdapter.ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new CongNgheAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_cong_nghe, null);
            viewHolder.txt_tenCN = (TextView) view.findViewById(R.id.tv_congnghe_ten);
            viewHolder.txt_tacgiaCN = (TextView) view.findViewById(R.id.tv_congnghe_tacgia);
            viewHolder.txt_giaCN = (TextView) view.findViewById(R.id.tv_congnghe_gia);
            viewHolder.txt_motaCN = (TextView) view.findViewById(R.id.tv_congnghe_mota);
            viewHolder.img_hinhanhCN = (ImageView) view.findViewById(R.id.image_congnghe);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (CongNgheAdapter.ViewHolder) view.getTag();
        }
        sachmoi sachmoi = (sachmoi) getItem(i);
        viewHolder.txt_tenCN.setText(sachmoi.getTensach());
        viewHolder.txt_tacgiaCN.setText(sachmoi.getTentacgia());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txt_giaCN.setText(decimalFormat.format(sachmoi.getGia())+ " VNĐ");
        viewHolder.txt_motaCN.setMaxLines(2);
        viewHolder.txt_motaCN.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txt_motaCN.setText(sachmoi.getMotasach());
        Picasso.get().load(sachmoi.getHinhanh())
                .placeholder(R.drawable.ic_load)            // Khi đang loat
                .error(R.drawable.ic_load_img_erro)             // Nếu lỗi không ra ảnh
                .into(viewHolder.img_hinhanhCN);                // Khi loat xong
        return view;
    }
}
















