package com.example.app_ban_sach.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_ban_sach.R;
import com.example.app_ban_sach.adapter.sachcuAdapter;
import com.example.app_ban_sach.model.EventBus.EventSuaXoa;
import com.example.app_ban_sach.model.sachcu;
import com.example.app_ban_sach.retrofit.ApiBookApp;
import com.example.app_ban_sach.retrofit.RetrofitBookApp;
import com.example.app_ban_sach.ultil.sever;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Manager_Activity extends AppCompatActivity {
    ImageView btn_them;
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBookApp apiBookApp;
    List<sachcu> list;
    sachcuAdapter adapter;
    sachcu sachcuSuaXoa;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        apiBookApp = RetrofitBookApp.getInstance(sever.BASE_URL).create(ApiBookApp.class);
        AnhXa();
        ControlBtn();
        getSachCu();
        ActionToolbar();
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

    private void getSachCu() {
        compositeDisposable.add(apiBookApp.getSachCu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        sachcuModel -> {
                            if (sachcuModel.isSuccess()){
                                list = sachcuModel.getResult();
                                adapter = new sachcuAdapter(getApplicationContext(), list);
                                recyclerView.setAdapter(adapter);
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Không kết nối với sever "+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                ));
    }

    private void ControlBtn() {
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Manager_Them_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbar_man);
        btn_them = findViewById(R.id.img_man_add);
        recyclerView = findViewById(R.id.recyclerviewMan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().equals("Sửa")){
            Intent intent = new Intent(getApplicationContext(), Manager_Them_Activity.class);
            intent.putExtra("update", sachcuSuaXoa);
            startActivity(intent);
        }
        if (item.getTitle().equals("Xoá")){
            compositeDisposable.add(apiBookApp.xoaSach(sachcuSuaXoa.getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(
                            sachcuModel -> {
                                if (sachcuModel.isSuccess()){
                                    Toast.makeText(getApplicationContext(), sachcuModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    getSachCu();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), sachcuModel.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            },
                            throwable -> {
                                Log.d("log", throwable.getMessage());
                            }
                    ));
        }
        return super.onContextItemSelected(item);
    }


    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void evenSuaXoa(EventSuaXoa event){
        if (event != null){
            sachcuSuaXoa = event.getSachcu();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}















