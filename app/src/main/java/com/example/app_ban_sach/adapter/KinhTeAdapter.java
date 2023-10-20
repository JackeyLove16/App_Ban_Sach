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

public class KinhTeAdapter extends BaseAdapter{
    Context context;
    ArrayList<sachmoi> arrayKinhTe;

    public KinhTeAdapter(Context context, ArrayList<sachmoi> arrayKinhTe) {
        this.context = context;
        this.arrayKinhTe = arrayKinhTe;
    }

    @Override
    public int getCount() {
        return arrayKinhTe.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayKinhTe.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        public TextView txt_tenKT,txt_tacgiaKT, txt_giaKT, txt_motaKT;
        public ImageView img_anhKT;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_kinh_te, null);
            viewHolder.txt_tenKT = (TextView) view.findViewById(R.id.tv_kinhte_ten);
            viewHolder.txt_tacgiaKT = (TextView) view.findViewById(R.id.tv_kinhte_tacgia);
            viewHolder.txt_giaKT = (TextView) view.findViewById(R.id.tv_kinhte_gia);
            viewHolder.txt_motaKT = (TextView) view.findViewById(R.id.tv_kinhte_mota);
            viewHolder.img_anhKT = (ImageView) view.findViewById(R.id.image_kinhte);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        sachmoi sachmoi = (sachmoi) getItem(i);
        viewHolder.txt_tenKT.setText(sachmoi.getTensach());
        viewHolder.txt_tacgiaKT.setText(sachmoi.getTentacgia());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txt_giaKT.setText(decimalFormat.format(sachmoi.getGia())+ " VNĐ");
        viewHolder.txt_motaKT.setMaxLines(2);
        viewHolder.txt_motaKT.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txt_motaKT.setText(sachmoi.getMotasach());
        Picasso.get().load(sachmoi.getHinhanh())
                .placeholder(R.drawable.ic_load)            // Khi đang loat
                .error(R.drawable.ic_load_img_erro)             // Nếu lỗi không ra ảnh
                .into(viewHolder.img_anhKT);                    // Khi loat xong
        return view;
    }
}



















