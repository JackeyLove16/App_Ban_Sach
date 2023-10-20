package com.example.app_ban_sach.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_ban_sach.R;
import com.example.app_ban_sach.adapter.loaisachAdapter;
import com.example.app_ban_sach.adapter.sachmoiAdapter;
import com.example.app_ban_sach.model.loaisach;
import com.example.app_ban_sach.model.sachmoi;
import com.example.app_ban_sach.model.shoppingCart;
import com.example.app_ban_sach.ultil.checkConnect;
import com.example.app_ban_sach.ultil.sever;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main_Activity extends AppCompatActivity{
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewManHinhChinh;
    NavigationView navigationView;
    ListView listViewManHinhChinh;
    DrawerLayout drawerLayout;
    ArrayList<loaisach> mangloaisach;
    loaisachAdapter loaisachAdapter;
    int id = 0;
    String tenloaisach = "", hinhanhloaisach = "";
    ArrayList<sachmoi> mangsachmoi;
    com.example.app_ban_sach.adapter.sachmoiAdapter sachmoiAdapter;
    public static ArrayList<shoppingCart> mangshoppingCart;
    NotificationBadge badge;
    ImageView img_menu, img_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();

        //Nếu như có kết nối internet ta mới thực hiện
        if(checkConnect.haveNetWorkConnection(Main_Activity.this)){ //getApplicationContext gọi đến màn hình này
            // Bắt sự kiện cho Toolbar
            ActionBar();
            // Hiển thị quảng cáo
            ActionViewFlipper();
            GetDuLieuLoaiSach();
            GetDuLieuSachMoi();
            CatchOnItemMenu();
            clickMenu();
            badge.setText(String.valueOf(Main_Activity.mangshoppingCart.size()));
        }
        else { //ngược lại ta thông báo không có internet
            checkConnect.ShowToast(Main_Activity.this, "Kiểm tra lại kết nối Internet!");
            finish();
        }
    }

    private void clickMenu(){
        img_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ShoppingCart_Activity.class);
                startActivity(intent);
                badge.setText(String.valueOf(Main_Activity.mangshoppingCart.size()));
            }
        });
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TimKiem_Activity.class);
                startActivity(intent);
            }
        });
    }

    //Sự kiện chuyển màn hình cho menu
    private void CatchOnItemMenu(){
        listViewManHinhChinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        if (checkConnect.haveNetWorkConnection(getApplicationContext())){
                            Intent intent = new Intent(Main_Activity.this, Main_Activity.class);
                            startActivity(intent);
                        }
                        else {
                            checkConnect.ShowToast(getApplicationContext(),"Kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (checkConnect.haveNetWorkConnection(getApplicationContext())){
                            Intent intent = new Intent(Main_Activity.this, KinhTe_Activity.class);
                            intent.putExtra("idtheloai", mangloaisach.get(1).getIdtheloai());
                            startActivity(intent);
                        }
                        else {
                            checkConnect.ShowToast(getApplicationContext(),"Kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if (checkConnect.haveNetWorkConnection(getApplicationContext())){
                            Intent intent = new Intent(Main_Activity.this, CongNghe_Activity.class);
                            intent.putExtra("idtheloai", mangloaisach.get(2).getIdtheloai());
                            startActivity(intent);
                        }
                        else {
                            checkConnect.ShowToast(getApplicationContext(),"Kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if (checkConnect.haveNetWorkConnection(getApplicationContext())){
                            Intent intent = new Intent(Main_Activity.this, TamLy_Activity.class);
                            intent.putExtra("idtheloai", mangloaisach.get(3).getIdtheloai());
                            startActivity(intent);
                        }
                        else {
                            checkConnect.ShowToast(getApplicationContext(),"Kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if (checkConnect.haveNetWorkConnection(getApplicationContext())){
                            Intent intent = new Intent(Main_Activity.this, Truyen_Activity.class);
                            intent.putExtra("idtheloai", mangloaisach.get(4).getIdtheloai());
                            startActivity(intent);
                        }
                        else {
                            checkConnect.ShowToast(getApplicationContext(),"Kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if (checkConnect.haveNetWorkConnection(getApplicationContext())){
                            Intent intent = new Intent(Main_Activity.this, TimKiem_Activity.class);
                            startActivity(intent);
                        }
                        break;
                    case 6:
                        if (checkConnect.haveNetWorkConnection(getApplicationContext())){
                            Intent intent = new Intent(Main_Activity.this, Manager_Activity.class);
                            startActivity(intent);
                        }
                        break;
                    case 7:
                        if (checkConnect.haveNetWorkConnection(getApplicationContext())){
                            Intent intent = new Intent(Main_Activity.this, LichSu_Activity.class);
                            startActivity(intent);
                        }
                        break;
                    case 8:
                        if (checkConnect.haveNetWorkConnection(getApplicationContext())){
                            Intent intent = new Intent(Main_Activity.this, Login_Activity.class);
                            startActivity(intent);
                        }
                        break;
                }
            }
        });
    }

    private void GetDuLieuSachMoi() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(sever.duongdan_SachMoi, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    int id = 0;
                    String tensach ="";
                    String tentacgia = "";
                    int soluong = 0;
                    String theloai = "";
                    Integer gia = 0;
                    String hinhanh = "";
                    String mota = "";
                    for (int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tensach = jsonObject.getString("tensach");
                            tentacgia = jsonObject.getString("tentacgia");
                            soluong = jsonObject.getInt("soluong");
                            theloai = jsonObject.getString("theloai");
                            gia = jsonObject.getInt("gia");
                            hinhanh = jsonObject.getString( "hinhanh");
                            mota = jsonObject.getString("mota");
                            mangsachmoi.add(new sachmoi(id, tensach, tentacgia, soluong, theloai, gia, hinhanh, mota));
                            sachmoiAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                checkConnect.ShowToast(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    private void GetDuLieuLoaiSach() {
        //Đọc nội dung trên location
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(sever.duongdan_LoaiSach, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){                                                   // Nếu như có dữ liệu json thì mới thực hiện
                    for (int i = 0; i < response.length(); i++){
                        //Sử dụng json object để đọc dữ liệu json của từng object
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("idtheloai");
                            tenloaisach = jsonObject.getString("theloai");
                            hinhanhloaisach = jsonObject.getString("hinhanh");
                            mangloaisach.add(new loaisach(id, tenloaisach, hinhanhloaisach));
                            loaisachAdapter.notifyDataSetChanged();
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mangloaisach.add(new loaisach(5, "Tìm Kiếm","https://cdn.pixabay.com/photo/2015/12/14/20/35/magnifier-1093183_640.png" ));
                    mangloaisach.add(new loaisach(6, "Sách Cũ","https://appfinance.vn/wp-content/uploads/2021/04/vay-tra-gop-30-trieu-ngan-hang-vietcombank-626x550.jpg" ));
                    mangloaisach.add(new loaisach(7, "Lịch Sử Đơn Hàng","https://icon-library.com/images/history-icon/history-icon-24.jpg"));
                    mangloaisach.add(new loaisach(8, "Đăng Xuất", "https://cdn-icons-png.flaticon.com/512/56/56805.png"));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Thông báo khi gặp lỗi
                checkConnect.ShowToast(getApplicationContext(),error.toString());
            }
        });
        // Thực hiện requestQueue
        requestQueue.add(jsonArrayRequest);
    }
    // TODO: Hiển thị quảng cáo
    private void ActionViewFlipper() {
        //Mảng lưu đường dẫn của các tấm hình
        ArrayList<Integer> advertissement = new ArrayList<>();
        advertissement.add(R.drawable.banner_1);
        advertissement.add(R.drawable.banner_3);
        advertissement.add(R.drawable.banner_4);
        advertissement.add(R.drawable.banner_5);
        advertissement.add(R.drawable.banner_6);
        //Gán link vào imageview
        for (int i = 0; i < advertissement.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(advertissement.get(i)).into(imageView);
            //Căn vừa ảnh với khung
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //Ép ImageView vào viewFlipper
            viewFlipper.addView(imageView);
        }
        //Tạo sự kiện viewFlipper tự chạy, với thời gian 5s
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        //Truyền 2 hiệu ứng từ xml vào-Tạo đối tượng view động
        Animation animation_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        //Truyền 2 animatin vào viewflipper
        viewFlipper.setInAnimation(animation_in);
        viewFlipper.setOutAnimation(animation_out);
    }
    private void ActionBar(){
        //Hàm hỗ trợ toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        //Tạo icon menu
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        //Mở ra menu
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START); //Nhảy menu ra giữa
            }
        });
    }
    private void AnhXa(){
        img_menu = (ImageView) findViewById(R.id.img_main_menu);
        img_search = (ImageView) findViewById(R.id.img_main_search);
        toolbar = (Toolbar) findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        recyclerViewManHinhChinh = (RecyclerView) findViewById(R.id.recyclerview);
        navigationView = (NavigationView) findViewById(R.id.navigationview);
        listViewManHinhChinh = findViewById(R.id.listviewmanhinhchinh);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mangloaisach = new ArrayList<>();
        //Ánh xạ cho toolbar
        mangloaisach.add(new loaisach(0, "Trang Chủ","https://i.pinimg.com/originals/b3/cc/d5/b3ccd57b054a73af1a0d281265b54ec8.jpg"));
        loaisachAdapter = new loaisachAdapter(mangloaisach, getApplicationContext());
        listViewManHinhChinh.setAdapter(loaisachAdapter);
        mangsachmoi = new ArrayList<>();
        sachmoiAdapter = new sachmoiAdapter(getApplicationContext(),mangsachmoi);
        recyclerViewManHinhChinh.setHasFixedSize(true);
        recyclerViewManHinhChinh.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewManHinhChinh.setAdapter(sachmoiAdapter);
        badge = findViewById(R.id.menu_sl);
        if (mangshoppingCart != null){

        }
        else {
            mangshoppingCart = new ArrayList<>();
        }
    }

}



















