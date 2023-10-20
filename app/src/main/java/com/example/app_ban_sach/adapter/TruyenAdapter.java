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

public class TruyenAdapter extends BaseAdapter{
    Context context;
    ArrayList<sachmoi> arrayTruyen;

    public TruyenAdapter(Context context, ArrayList<sachmoi> arrayTruyen) {
        this.context = context;
        this.arrayTruyen = arrayTruyen;
    }
    @Override
    public int getCount() {
        return arrayTruyen.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayTruyen.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txt_ten, txt_tacgia,  txt_gia, txt_mota;
        public ImageView img_anh;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_truyen, null);
            viewHolder.txt_ten = (TextView) view.findViewById(R.id.tv_truyen_ten);
            viewHolder.txt_tacgia = (TextView) view.findViewById(R.id.tv_truyen_tacgia);
            viewHolder.txt_gia = (TextView) view.findViewById(R.id.tv_truyen_gia);
            viewHolder.txt_mota = (TextView) view.findViewById(R.id.tv_truyen_mota);
            viewHolder.img_anh = (ImageView) view.findViewById(R.id.image_truyen);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        sachmoi sachmoi = (sachmoi) getItem(i);
        viewHolder.txt_ten.setText(sachmoi.getTensach());
        viewHolder.txt_tacgia.setText(sachmoi.getTentacgia());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txt_gia.setText(decimalFormat.format(sachmoi.getGia())+ " VNĐ");
        viewHolder.txt_mota.setMaxLines(2);
        viewHolder.txt_mota.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txt_mota.setText(sachmoi.getMotasach());
        Picasso.get().load(sachmoi.getHinhanh())
                .placeholder(R.drawable.ic_load)            // Khi đang loat
                .error(R.drawable.ic_load_img_erro)             // Nếu lỗi không ra ảnh
                .into(viewHolder.img_anh);                      // Khi loat xong
        return view;
    }
}
