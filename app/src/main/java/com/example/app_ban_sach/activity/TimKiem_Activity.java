package com.example.app_ban_sach.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_ban_sach.R;
import com.example.app_ban_sach.adapter.TimKiemAdapter;
import com.example.app_ban_sach.model.sachcu;
import com.example.app_ban_sach.retrofit.ApiBookApp;
import com.example.app_ban_sach.retrofit.RetrofitBookApp;
import com.example.app_ban_sach.ultil.sever;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TimKiem_Activity extends AppCompatActivity {
    Toolbar toolbar_timkiem;
    RecyclerView recyclerView;
    EditText edtsearch;
    com.example.app_ban_sach.adapter.TimKiemAdapter adapterTK;
    List<sachcu> sachcuList;
    ApiBookApp apiBookApp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_timkiem);
        AnhXa();
        ActionBar();
    }
    private void AnhXa() {
        apiBookApp = RetrofitBookApp.getInstance(sever.BASE_URL).create(ApiBookApp.class);
        edtsearch = findViewById(R.id.edt_timkiem);
        toolbar_timkiem = findViewById(R.id.toolbar_timkiem);
        recyclerView = findViewById(R.id.recyclerView_timkiem);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                String str_search;
                str_search = edtsearch.getText().toString().trim();
                compositeDisposable.add(apiBookApp.search(str_search).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                   sachcuModel -> {
                       if (sachcuModel.isSuccess()){
                           adapterTK = new TimKiemAdapter(getApplicationContext(), sachcuModel.getResult());
                           recyclerView.setAdapter(adapterTK);
                       }
                   }, throwable -> {
                            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                ));
            }
        });
    }

    private void ActionBar(){
        setSupportActionBar(toolbar_timkiem);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_timkiem.setNavigationIcon(R.drawable.ic_back);
        toolbar_timkiem.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
















