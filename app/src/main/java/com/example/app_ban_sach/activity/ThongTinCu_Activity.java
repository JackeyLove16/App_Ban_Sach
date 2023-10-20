package com.example.app_ban_sach.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.app_ban_sach.R;
import com.example.app_ban_sach.model.sachcu;
import com.example.app_ban_sach.model.shoppingCart;
import com.example.app_ban_sach.ultil.checkConnect;
import com.nex3z.notificationbadge.NotificationBadge;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ThongTinCu_Activity extends AppCompatActivity {
    Toolbar toolbar_ChitietSach;
    ImageView image_ChitetSach;
    TextView tv_TenChitietSach,tv_TacGiaChiTietSach, tv_GiaChitietSach;
    TextView tv_TenChitietSachCT,tv_TacGiaChiTietSachCT, tv_GiaChitietSachCT, tv_MotaChitietSach, tv_SoluongChiTietSachCT, tv_TheLoaiChiTietSachCT;
    ImageView img_menu;
    Spinner spinner_ChitietSach;
    Button bt_ChitietSach_Mua;
    int id = 0, gia_chitietsach = 0, soluong_chitietsach = 0;
    String ten_chitietsach = "", tacgia_chitietssach = "", anh_chitietsach = "", mota_chitietsach = "", theloai_chitietsach = "";
    NotificationBadge badge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thongtinsach);

        AnhXa_Chitietsach();
        if( checkConnect.haveNetWorkConnection(getApplicationContext())){
            ActionToolBar();
            GetThongTinSachCu();
            CatchEvenSpinner();                               // Hàm giới hạn số lượng được mua
            EvenButton();
            clickMenu();
        }
        else {
            checkConnect.ShowToast(getApplicationContext(), "Bạn chưa kết nối Internet");
        }
    }

    private void clickMenu(){
        img_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ShoppingCart_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void EvenButton() {
        bt_ChitietSach_Mua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kiểm tra dữ liệu mảng
                int soluong = Integer.parseInt(spinner_ChitietSach.getSelectedItem().toString());
                // Kiểm tra nếu không tìm được id trùng
                boolean exits = false;
                if(Main_Activity.mangshoppingCart.size() != 0){
                    for (int i = 0; i < Main_Activity.mangshoppingCart.size(); i++){
                        if(Main_Activity.mangshoppingCart.get(i).getIdsach() == id){            // Nếu id của mảng trùng id ban đầu
                            Main_Activity.mangshoppingCart.get(i).setSoluongsach(Main_Activity.mangshoppingCart.get(i).getSoluongsach() + soluong);
                            // Nếu số lượng >=  10 thì set số lượng max = 10
                            if (Main_Activity.mangshoppingCart.get(i).getSoluongsach() >= 10){
                                Main_Activity.mangshoppingCart.get(i).setSoluongsach(10);
                            }
                            // Update giá dựa trên số lượng sách mới
                            Main_Activity.mangshoppingCart.get(i).setGiasach(gia_chitietsach * Main_Activity.mangshoppingCart.get(i).getSoluongsach());
                            badge.setText(String.valueOf(Main_Activity.mangshoppingCart.size()));
                            exits = true;
                        }
                    }
                    // Nếu như không tìm được id trùng thì ép giá trị mới
                    if (exits == false){
                        long GiaMoi = soluong * gia_chitietsach;
                        Main_Activity.mangshoppingCart.add(new shoppingCart(id, ten_chitietsach, GiaMoi, anh_chitietsach, soluong));
                        badge.setText(String.valueOf(Main_Activity.mangshoppingCart.size()));
                    }
                }
                // Nếu không có dữ liệu sẽ add dữ liệu mới
                else {
                    long GiaMoi = soluong * gia_chitietsach;
                    Main_Activity.mangshoppingCart.add(new shoppingCart(id, ten_chitietsach, GiaMoi, anh_chitietsach, soluong));
                    badge.setText(String.valueOf(Main_Activity.mangshoppingCart.size()));
                }
                Intent intent = new Intent(getApplicationContext(), ShoppingCart_Activity.class);
                startActivity(intent);
            }
        });

    }

    private void CatchEvenSpinner(){
        Integer[] soluong = new Integer[]{1, 2, 3, 4, 5, 6, 7,8, 9, 10};
        //Layout đặc trưng của Spinner simple_spinner_dropdown_item
        ArrayAdapter<Integer> arrayAdapteSpinner = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, soluong);
        spinner_ChitietSach.setAdapter(arrayAdapteSpinner);
    }
    private void GetThongTinSachCu() {
        sachcu sachcu = (sachcu) getIntent().getSerializableExtra("thongtinsachcu");
        id = sachcu.getId();
        ten_chitietsach = sachcu.getTensach();
        tacgia_chitietssach = sachcu.getTentacgia();
        soluong_chitietsach = sachcu.getSoluong();
        theloai_chitietsach = sachcu.getTheloai();
        gia_chitietsach = sachcu.getGia();
        anh_chitietsach = sachcu.getHinhanh();
        mota_chitietsach = sachcu.getMota();

        //Gán dữ liệu lên cho layout
        tv_TenChitietSach.setText(ten_chitietsach);
        tv_TenChitietSachCT.setText(ten_chitietsach);
        tv_TacGiaChiTietSach.setText(tacgia_chitietssach);
        tv_TacGiaChiTietSachCT.setText(tacgia_chitietssach);
        tv_SoluongChiTietSachCT.setText(soluong_chitietsach+"");
        tv_TheLoaiChiTietSachCT.setText(theloai_chitietsach);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tv_GiaChitietSach.setText(decimalFormat.format(gia_chitietsach) + " VNĐ");
        tv_GiaChitietSachCT.setText(decimalFormat.format(gia_chitietsach) + " VNĐ");
        tv_MotaChitietSach.setText(mota_chitietsach);
        Picasso.get().load(anh_chitietsach)
                .placeholder(R.drawable.ic_load)
                .error(R.drawable.ic_load_img_erro)
                .into(image_ChitetSach);
    }


    private void ActionToolBar() {
        setSupportActionBar(toolbar_ChitietSach);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_ChitietSach.setNavigationIcon(R.drawable.ic_back);
        toolbar_ChitietSach.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void AnhXa_Chitietsach() {
        img_menu = (ImageView) findViewById(R.id.img_thongtinsach_menu);
        toolbar_ChitietSach = (Toolbar) findViewById(R.id.toolbar_thongtinsach);
        image_ChitetSach = (ImageView) findViewById(R.id.image_thongtinsach);
        tv_TenChitietSach = (TextView) findViewById(R.id.tv_thongtinsach_ten);
        tv_GiaChitietSach = (TextView) findViewById(R.id.tv_thongtinsach_gia);
        tv_TacGiaChiTietSach = (TextView) findViewById(R.id.tv_thongtinsach_tacgia);
        spinner_ChitietSach = (Spinner) findViewById(R.id.spinner_thongtinsach_sl);
        bt_ChitietSach_Mua = (Button) findViewById(R.id.btn_thongtinsach_mua);
        tv_TenChitietSachCT = (TextView) findViewById(R.id.tv_thongtinsach_tenct);
        tv_TacGiaChiTietSachCT = (TextView) findViewById(R.id.tv_thongtinsach_tacgiact);
        tv_MotaChitietSach = (TextView) findViewById(R.id.tv_thongtinsach_motachitiet);
        tv_SoluongChiTietSachCT = (TextView) findViewById(R.id.tv_thongtinsach_soluongct);
        tv_TheLoaiChiTietSachCT = (TextView) findViewById(R.id.tv_thongtinsach_theloaict);
        tv_GiaChitietSachCT = (TextView) findViewById(R.id.tv_thongtinsach_giact);
        badge = findViewById(R.id.menu_sl);
    }
}
