package com.example.app_ban_sach.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.app_ban_sach.R;
import com.example.app_ban_sach.retrofit.ApiBookApp;
import com.example.app_ban_sach.retrofit.RetrofitBookApp;
import com.example.app_ban_sach.ultil.sever;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Login_Activity extends AppCompatActivity {
    TextView txtdangki;
    EditText email, pass;
    AppCompatButton btndangnhap;
    ApiBookApp apiBookApp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    boolean isLogin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        anhxa();
        controll();
    }

    private void controll() {
        txtdangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Register_Activity.class);
                startActivity(intent);
            }
        });
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_email = email.getText().toString().trim();
                String str_pass = pass.getText().toString().trim();
                if(TextUtils.isEmpty(str_email)){
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập Email", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(str_pass)){
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập Mật Khẩu", Toast.LENGTH_SHORT).show();
                }
                else {
                    Paper.book().write("email", str_email);
                    Paper.book().write("pass", str_pass);
                    compositeDisposable.add(apiBookApp.dangNhap(str_email, str_pass)
                            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    userModel -> {
                                        if (userModel.isSuccess()){
                                            sever.user_current = userModel.getResult().get(0);
                                            Intent intent = new Intent(getApplicationContext(), Main_Activity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    },
                                    throwable -> {
                                        Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                            ));
                }
            }
        });
    }

    private void anhxa() {
        Paper.init(this);
        apiBookApp = RetrofitBookApp.getInstance(sever.BASE_URL).create(ApiBookApp.class);
        txtdangki = findViewById(R.id.tv_dn_dk);
        email = findViewById(R.id.edt_login_email);
        pass = findViewById(R.id.edt_login_pass);
        btndangnhap = findViewById(R.id.btn_dangnhap);

        if(Paper.book().read("email") != null && Paper.book().read("pass") != null){
            email.setText(Paper.book().read("email"));
            pass.setText(Paper.book().read("pass"));
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}