package com.example.app_ban_sach.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_ban_sach.R;
import com.example.app_ban_sach.adapter.LichSuAdapter;
import com.example.app_ban_sach.retrofit.ApiBookApp;
import com.example.app_ban_sach.retrofit.RetrofitBookApp;
import com.example.app_ban_sach.ultil.sever;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LichSu_Activity extends AppCompatActivity {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBookApp apiBookApp;
    RecyclerView recyclerViewLichSu;
    androidx.appcompat.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lichsu);
        AnhXa();
        ActionToolbar();
        getOrder();
    }

    private void getOrder() {
        compositeDisposable.add(apiBookApp.lichSuDonHang(sever.user_current.getId())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        donHangModel -> {
                            LichSuAdapter adapter = new LichSuAdapter(getApplicationContext(), donHangModel.getResult());
                            recyclerViewLichSu.setAdapter(adapter);
                        },
                        throwable -> {

                        }
                ));
    }

    private void ActionToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void AnhXa() {
        apiBookApp = RetrofitBookApp.getInstance(sever.BASE_URL).create(ApiBookApp.class);
        recyclerViewLichSu = findViewById(R.id.recyclerview_lichsu);
        toolbar = findViewById(R.id.toolbar_lichsu);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewLichSu.setLayoutManager(layoutManager);
    }
    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

}

















