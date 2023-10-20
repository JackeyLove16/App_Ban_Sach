package com.example.app_ban_sach.activity;

import static com.example.app_ban_sach.activity.ShoppingCart_Activity.EvenUltil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com.example.app_ban_sach.R;
import com.example.app_ban_sach.retrofit.ApiBookApp;
import com.example.app_ban_sach.retrofit.RetrofitBookApp;
import com.example.app_ban_sach.ultil.sever;
import com.google.gson.Gson;

import java.text.DecimalFormat;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ThanhToan_Activity extends AppCompatActivity {
    TextView txttongtien, txtsdt, txtemail;
    Toolbar toolbar;
    EditText editdiachi;
    AppCompatButton btndathang;
    long tongtien;
    int totalItem;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBookApp apiBookApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thanhtoan);
        AnhXa();
        DemSl();
        Control();
    }

    private void DemSl() {
        totalItem = 0;
        for (int i = 0; i< sever.mangmuahang.size(); i++){
            totalItem += sever.mangmuahang.get(i).getSoluongsach();
        }
    }

    private void Control() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tongtien = getIntent().getLongExtra("thanhtoan_tongtien",0);
        EvenUltil();
        txttongtien.setText(decimalFormat.format(tongtien)+" VND");
        txtemail.setText(sever.user_current.getEmail());
        txtsdt.setText(sever.user_current.getMobile());
        btndathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_diachi =editdiachi.getText().toString().trim();
                if (TextUtils.isEmpty(str_diachi)){
                    Toast.makeText(getApplicationContext(),"Bạn chưa nhập địa chỉ", Toast.LENGTH_SHORT).show();
                }
                else {
                    String str_email = sever.user_current.getEmail();
                    String str_sdt = sever.user_current.getMobile();
                    int id = sever.user_current.getId();
                    Log.d("chitiet", new Gson().toJson(sever.mangmuahang));
                    compositeDisposable.add(apiBookApp.createrOrder(str_email,str_sdt,String.valueOf(tongtien),id ,str_diachi,totalItem, new Gson().toJson(sever.mangmuahang))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                                    userModel -> {
                                        Toast.makeText(getApplicationContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Main_Activity.class);
                                        startActivity(intent);
                                        sever.mangmuahang.clear();
                                        finish();
                                    },
                                    throwable -> {
                                        Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                            ));
                }
            }
        });
    }

    private void AnhXa() {
        apiBookApp = RetrofitBookApp.getInstance(sever.BASE_URL).create(ApiBookApp.class);
        toolbar = findViewById(R.id.toolbar_thanhtoan);
        txttongtien = findViewById(R.id.tv_thanhtoan_tongtien);
        txtsdt = findViewById(R.id.tv_thanhtoan_sdt);
        txtemail = findViewById(R.id.tv_thanhtoan_email);
        editdiachi = findViewById(R.id.ed_thanhtoan_diachi);
        btndathang = findViewById(R.id.bt_thanhtoan);
    }
    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}