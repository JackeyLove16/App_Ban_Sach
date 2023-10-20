package com.example.app_ban_sach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_ban_sach.R;
import com.example.app_ban_sach.activity.Main_Activity;
import com.example.app_ban_sach.activity.ShoppingCart_Activity;
import com.example.app_ban_sach.model.shoppingCart;
import com.example.app_ban_sach.ultil.sever;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ShoppingCartAdapter extends BaseAdapter {

    Context context;
    ArrayList<shoppingCart> arrayshoppingCart;
    int soluongmoinhat;

    public ShoppingCartAdapter(Context context, ArrayList<shoppingCart> arrayshoppingCart) {
        this.context = context;
        this.arrayshoppingCart = arrayshoppingCart;
    }

    @Override
    public int getCount() {
        return arrayshoppingCart.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayshoppingCart.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView tvTenCart, tvGiaCart, btSoluongCart;
        Button btTruCart, btCongCart;
        ImageView imageAnhCart;
        CheckBox checkBox;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.items_shoppingcart, null);
            viewHolder.tvTenCart = (TextView) view.findViewById(R.id.tv_items_shoppingcart_ten);
            viewHolder.tvGiaCart = (TextView) view.findViewById(R.id.tv_items_shoppingcart_gia);
            viewHolder.btSoluongCart = (TextView) view.findViewById(R.id.tv_items_shoppingcart_soluong);
            viewHolder.btTruCart = (Button) view.findViewById(R.id.btn_items_shoppingcart_tru);
            viewHolder.btCongCart = (Button) view.findViewById(R.id.btn_items_shoppingcart_cong);
            viewHolder.imageAnhCart = (ImageView) view.findViewById(R.id.image_items_shoppingcart);
            viewHolder.checkBox = view.findViewById(R.id.checkbok_items_shoppingcart);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        shoppingCart gioHang = (shoppingCart) getItem(i);
        viewHolder.tvTenCart.setText(gioHang.getTensach());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.tvGiaCart.setText(decimalFormat.format(gioHang.getGiasach()) + " VNĐ");
        Picasso.get().load(gioHang.getHinhanhsach())
                .placeholder(R.drawable.ic_load)
                .error(R.drawable.ic_load_img_erro)
                .into(viewHolder.imageAnhCart);
        viewHolder.btSoluongCart.setText(gioHang.getSoluongsach() + "");
        //Lấy số lượng có trong tv số lượng sản phẩm mua và so sánh
        int soluongsanpham = Integer.parseInt(viewHolder.btSoluongCart.getText().toString());
        // Nếu lớn >= 10 thì ẩn nút cộng, <= 1 thì ẩn nút trừ
        if(soluongsanpham >= 10){
            viewHolder.btCongCart.setVisibility(view.INVISIBLE);
            viewHolder.btTruCart.setVisibility(view.VISIBLE);
        }
        else if(soluongsanpham <= 1){
            viewHolder.btCongCart.setVisibility(view.VISIBLE);
            viewHolder.btTruCart.setVisibility(view.INVISIBLE);
        }
        else {
            viewHolder.btCongCart.setVisibility(view.VISIBLE);
            viewHolder.btTruCart.setVisibility(view.VISIBLE);
        }
        ViewHolder finalViewHolder = viewHolder;
        soluongmoinhat = soluongsanpham;

        viewHolder.btCongCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy ra số lượng mới nhất
                 soluongmoinhat += 1;
                // Lấy ra số lượng hiện tại
                int soluonghientai = Main_Activity.mangshoppingCart.get(i).getSoluongsach();
                // Lấy ra giá hiện tại
                long giahientai = Main_Activity.mangshoppingCart.get(i).getGiasach();
                // Ép lại cho mảng số lượng mới nhất
                Main_Activity.mangshoppingCart.get(i).setSoluongsach(soluongmoinhat);
                // Sử dụng công thức. Lấy số lượng mới nhất * giá hiện tại chia cho số lượng hiện tại thì ta có được giá tiền mới nhất
                long giamoinhat = (giahientai * soluongmoinhat)/soluonghientai;
                Main_Activity.mangshoppingCart.get(i).setGiasach(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.tvGiaCart.setText(decimalFormat.format(gioHang.getGiasach()) + " VNĐ");
                finalViewHolder.btSoluongCart.setText(String.valueOf(soluongmoinhat));
                // Update tổng tiền
                com.example.app_ban_sach.activity.ShoppingCart_Activity.EvenUltil();
                if (soluongmoinhat >= 10){
                    finalViewHolder.btCongCart.setVisibility(view.INVISIBLE);
                    finalViewHolder.btTruCart.setVisibility(view.VISIBLE);
                }
                else {
                    finalViewHolder.btCongCart.setVisibility(view.VISIBLE);
                    finalViewHolder.btTruCart.setVisibility(view.VISIBLE);
                }
            }
        });

        viewHolder.btTruCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lấy ra số lượng mới nhất
                soluongmoinhat -= 1;
                //Lấy ra số lượng hiện tại
                int soluonghientai = Main_Activity.mangshoppingCart.get(i).getSoluongsach();
                //Lấy ra giá hiện tại
                long giahientai = Main_Activity.mangshoppingCart.get(i).getGiasach();
                //Ép lại cho mảng số lượng mới nhất
                Main_Activity.mangshoppingCart.get(i).setSoluongsach(soluongmoinhat);
                // Sử dụng công thức. Lấy số lượng mới nhất * giá hiện tại chia cho số lượng hiện tại thì ta có được giá tiền mới nhất
                long giamoinhat = (giahientai * soluongmoinhat)/soluonghientai;
                Main_Activity.mangshoppingCart.get(i).setGiasach(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.tvGiaCart.setText(decimalFormat.format(gioHang.getGiasach()) + " VNĐ");
                finalViewHolder.btSoluongCart.setText(String.valueOf(soluongmoinhat));
                // Update tổng tiền
                ShoppingCart_Activity.EvenUltil();
                if (soluongmoinhat < 2){
                    finalViewHolder.btTruCart.setVisibility(view.INVISIBLE);
                    finalViewHolder.btCongCart.setVisibility(view.VISIBLE);
                }
                else {
                    finalViewHolder.btTruCart.setVisibility(view.VISIBLE);
                    finalViewHolder.btCongCart.setVisibility(view.VISIBLE);
                }
            }
        });
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    sever.mangmuahang.add(gioHang);
                    com.example.app_ban_sach.activity.ShoppingCart_Activity.EvenUltil();
                }
                else {
                    for (int i = 0; i<sever.mangmuahang.size();i++){
                        if(sever.mangmuahang.get(i).getIdsach() == gioHang.getIdsach()){
                            sever.mangmuahang.remove(i);
                        }
                    }
                    com.example.app_ban_sach.activity.ShoppingCart_Activity.EvenUltil();
                }
            }
        });
        return view;
    }
}




















