package com.example.app_ban_sach.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.app_ban_sach.R;
import com.example.app_ban_sach.retrofit.ApiBookApp;
import com.example.app_ban_sach.retrofit.RetrofitBookApp;
import com.example.app_ban_sach.ultil.sever;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Register_Activity extends AppCompatActivity {
    EditText email, pass, repass, mobile, username;
    AppCompatButton button_DK, button_backDN;
    ApiBookApp apiBookApp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        AnhXa();
        Controll();
    }

    private void Controll() {
        button_DK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangKi();
            }
        });
        button_backDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void dangKi(){
        String str_email = email.getText().toString().trim();
        String str_pass = pass.getText().toString().trim();
        String str_repass = repass.getText().toString().trim();
        String str_username = username.getText().toString().trim();
        String str_mobile = mobile.getText().toString().trim();
        if (TextUtils.isEmpty(str_email)){
            Toast.makeText(getApplicationContext(), "Bạn Chưa Nhập Email", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(str_pass)){
            Toast.makeText(getApplicationContext(), "Hãy nhập mật khẩu", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(str_repass)){
            Toast.makeText(getApplicationContext(), "Nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(str_username)){
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập tên của mình", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(str_mobile)){
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập số điện thoại", Toast.LENGTH_SHORT).show();
        }
        else {
            if (str_pass.equals(str_repass)){
                // lấy data
                compositeDisposable.add(apiBookApp.dangKi(str_email,str_pass,str_username,str_mobile)
                        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                userModel -> {
                                    if (userModel.isSuccess()){
                                        sever.user_current.setEmail(str_email);
                                        sever.user_current.setPass(str_pass);
                                        Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                },
                                throwable -> {
                                    Toast.makeText(getApplicationContext(),throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        ));
            }
            else {
                Toast.makeText(getApplicationContext(), "Không trùng mật khẩu", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void AnhXa() {
        apiBookApp = RetrofitBookApp.getInstance(sever.BASE_URL).create(ApiBookApp.class);
        email = findViewById(R.id.edt_register_email);
        pass = findViewById(R.id.edt_register_pass);
        repass = findViewById(R.id.edt_register_repass);
        mobile = findViewById(R.id.edt_register_mobile);
        username = findViewById(R.id.edt_register_user);
        button_DK = findViewById(R.id.btn_dangky);
        button_backDN = findViewById(R.id.btn_backlogin);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}