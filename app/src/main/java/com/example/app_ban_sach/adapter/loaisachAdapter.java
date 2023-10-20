package com.example.app_ban_sach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_ban_sach.R;
import com.example.app_ban_sach.model.loaisach;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class loaisachAdapter extends BaseAdapter {
    // Biến giao diện
    ArrayList<loaisach> arrLoaiSach = null;
    Context context;                                    //Màn hình nào

    public loaisachAdapter(ArrayList<loaisach> arrLoaiSach, Context context) {
        this.arrLoaiSach = arrLoaiSach;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrLoaiSach.size();                      // Muốn lấy hết dữ liệu trong mảng
    }

    @Override
    public Object getItem(int i) {
        return arrLoaiSach.get(i);                        // Lấy ra từng thuộc tính trong mảng
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    // Gán trực tiếp giá trị vào layout
    public class ViewHolder{
        ImageView imgloaisach;
        TextView tvloaisach;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Khai báo class viewholder
        ViewHolder viewHolder = null;
        // Nếu như view rỗng
        if(view == null){
            // gọi lại viewholder để sử dụng các thuộc tính bên trong
            viewHolder = new ViewHolder();
            // Lấy ra layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // Lấy view theo layout_loaisanpham
            view = inflater.inflate(R.layout.item_loaisach,null);
            // Gán id cho các thuộc tính layout
            viewHolder.tvloaisach = (TextView) view.findViewById(R.id.tv_loaisach);
            viewHolder.imgloaisach = (ImageView) view.findViewById(R.id.img_loaisach);
            // Gán id vào lại viewholder
            view.setTag(viewHolder);
        }
        else {
            // Khi chạy lạy lần 2 ta không cần lấy lại id và chỉ việc chạy thôi, giúp nhanh hơn và giảm thiểu bộ nhớ sử dụng
            viewHolder = (ViewHolder) view.getTag(); //Lấy lại các tag đã gán vào
        }
        // lấy dữ liệu theo loai san pham
        loaisach loaiSanPham = (loaisach) getItem(i);
        // Xét những dữ liệu đã có trong mảng
        viewHolder.tvloaisach.setText(loaiSanPham.getTheloai());
        // Lấy hình ảnh bằng picasso
        Picasso.get().load(loaiSanPham.getHinhanh())
                .placeholder(R.drawable.ic_load)            // Khi đang loat
                .error(R.drawable.ic_load_img_erro)             // Nếu lỗi không ra ảnh
                .into(viewHolder.imgloaisach);                  // Khi loat xong
        return view;
    }
}
