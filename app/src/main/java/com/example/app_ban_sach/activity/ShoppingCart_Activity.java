package com.example.app_ban_sach.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_ban_sach.R;
import com.example.app_ban_sach.adapter.ShoppingCartAdapter;
import com.example.app_ban_sach.ultil.checkConnect;
import com.example.app_ban_sach.ultil.sever;

import java.text.DecimalFormat;

public class ShoppingCart_Activity extends AppCompatActivity {
    TextView tvThongbaoCart;
    static TextView tvTongTienCart;
    Button btMuangayCart, btThemmoiCart;
    ListView listCart;
    androidx.appcompat.widget.Toolbar toolbarCart;
    ShoppingCartAdapter adapterCart;
    long thanhtoan_tongtien;
    public static void EvenUltil() {
        long tongtiensp = 0;
        for (int i = 0; i < sever.mangmuahang.size(); i++){
            tongtiensp += sever.mangmuahang.get(i).getGiasach();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvTongTienCart.setText(decimalFormat.format(tongtiensp) + " VNĐ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_shopping_cart);
        AnhXaCart();
        ActionToolbar();
        CheckCart();
        XoaSp();
        EvenUltil();
        ClickButton();
    }


    private void ClickButton(){
        btThemmoiCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main_Activity.class);
                startActivity(intent);
            }
        });
        btMuangayCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sever.mangmuahang.size() > 0){
                    thanhtoan_tongtien = 0;
                    for (int i = 0; i < sever.mangmuahang.size();i++){
                        thanhtoan_tongtien += sever.mangmuahang.get(i).getGiasach();
                    }
                    Intent intent = new Intent(getApplicationContext(), ThanhToan_Activity.class);
                    intent.putExtra("thanhtoan_tongtien", thanhtoan_tongtien);
                    Main_Activity.mangshoppingCart.clear();
                    startActivity(intent);
                }
                else {
                    checkConnect.ShowToast(getApplicationContext(), "Giỏ hàng của bạn đang trống");
                }
            }
        });
    }

    //    public void XoaSach(){
//        btDeleteCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCart_Activity.this);
//                builder.setTitle("Thông Báo");
//                builder.setMessage("Bạn có chắc chắn xoá sản phẩm này?");
//                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        if(Main_Activity.mangshoppingCart.size() <= 0){
//                            tvThongbaoCart.setVisibility(View.VISIBLE);
//                        }
//                        else {
//                            //Bắt vị trí theo listview
//                            Main_Activity.mangshoppingCart.remove(i);
//                            //Cập nhật lại màn hình
//                            adapterCart.notifyDataSetChanged();
//                            //Cập nhật lại tổng tiền
//                            EvenUltil();
//                            //Sau khi xoá xong nếu dữ liệu trống ta hiện thị giỏ hàng trống
//                            if(Main_Activity.mangshoppingCart.size() <= 0){
//                                tvThongbaoCart.setVisibility(View.VISIBLE);
//                            }
//                            else {
//                                tvThongbaoCart.setVisibility(View.INVISIBLE);
//                                adapterCart.notifyDataSetChanged();
//                                EvenUltil();
//                            }
//                        }
//                    }
//                });
//                builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        finish();
//                    }
//                });
//                builder.create().show();
//            }
//        });
//    }
    public void XoaSp(){
        listCart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCart_Activity.this);
                builder.setTitle("Thông Báo");
                builder.setMessage("Bạn có chắc chắn xoá sản phẩm này?");
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(Main_Activity.mangshoppingCart.size() <= 0){
                            tvThongbaoCart.setVisibility(View.VISIBLE);
                        }
                        else {
                            //Bắt vị trí theo listview
                            Main_Activity.mangshoppingCart.remove(position);
                            //Cập nhật lại màn hình
                            adapterCart.notifyDataSetChanged();
                            //Cập nhật lại tổng tiền
                            EvenUltil();
                            //Sau khi xoá xong nếu dữ liệu trống ta hiện thị giỏ hàng trống
                            if(Main_Activity.mangshoppingCart.size() <= 0){
                                tvThongbaoCart.setVisibility(View.VISIBLE);
                            }
                            else {
                                tvThongbaoCart.setVisibility(View.INVISIBLE);
                                adapterCart.notifyDataSetChanged();
                                EvenUltil();
                            }
                        }
                    }
                });
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapterCart.notifyDataSetChanged();
                        EvenUltil();
                    }
                });
                builder.create().show();
                return true;
            }
        });
    }


    //Kiểm tra dữ liệu bên trong giỏ hàng
    private void CheckCart(){
        //nếu như dữ liệu trống, ta cho hiện thị câu thông báo giỏ hàng trống
        if (Main_Activity.mangshoppingCart.size() <= 0){
            //Cập nhật lại adapter, cho thông báo hiện ra và ẩn list view
            adapterCart.notifyDataSetChanged();
            tvThongbaoCart.setVisibility(View.VISIBLE);
            listCart.setVisibility(View.INVISIBLE);
        }
        else {
            adapterCart.notifyDataSetChanged();
            tvThongbaoCart.setVisibility(View.INVISIBLE);
            listCart.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolbar(){
        setSupportActionBar(toolbarCart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarCart.setNavigationIcon(R.drawable.ic_back);
        toolbarCart.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sever.mangmuahang.clear();
                finish();
            }
        });
    }

    private void AnhXaCart() {
        toolbarCart = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar_ShoppingCart);
        tvThongbaoCart = (TextView) findViewById(R.id.tv_ShoppingCart_thongbao);
        tvTongTienCart = (TextView) findViewById(R.id.tv_ShoppingCart_tonggia);
        listCart = (ListView) findViewById(R.id.listview_ShoppingCart);
        btMuangayCart = (Button) findViewById(R.id.bt_ShoppingCart_muangay);
        btThemmoiCart = (Button) findViewById(R.id.bt_ShoppingCart_TiepTuc);
        adapterCart = new ShoppingCartAdapter(ShoppingCart_Activity.this, Main_Activity.mangshoppingCart);
        listCart.setAdapter(adapterCart);
    }
}