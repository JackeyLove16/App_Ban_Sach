package com.example.app_ban_sach.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.app_ban_sach.R;
import com.example.app_ban_sach.databinding.ActivityManagerThemBinding;
import com.example.app_ban_sach.model.sachcu;
import com.example.app_ban_sach.retrofit.ApiBookApp;
import com.example.app_ban_sach.retrofit.RetrofitBookApp;
import com.example.app_ban_sach.ultil.sever;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Manager_Them_Activity extends AppCompatActivity {
    Spinner spinner;
    int theloai = 0;
    ActivityManagerThemBinding binding;
    ApiBookApp apiBookApp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    String mediaPath;
    sachcu suasach;
    boolean kiemtra = false;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManagerThemBinding.inflate(getLayoutInflater());
        apiBookApp = RetrofitBookApp.getInstance(sever.BASE_URL).create(ApiBookApp.class);
        setContentView(binding.getRoot());
        AnhXa();
        initData();
        ActionToolbar();

        Intent intent = getIntent();
        suasach = (com.example.app_ban_sach.model.sachcu) intent.getSerializableExtra("update");
        if (suasach == null){
            kiemtra = false;
        }
        else {
            kiemtra = true;
            binding.btnThem.setText("update");
            binding.toolbarThemsach.setTitle("Sửa thông tin sách");
            binding.edtManAddTensach.setText(suasach.getTensach()+"");
            binding.edtManAddTentacgia.setText(suasach.getTentacgia()+"");
            binding.edtManAddGia.setText(suasach.getGia()+"");
            binding.edtManAddSoluong.setText(suasach.getSoluong()+"");
            binding.edtManAddHinhanh.setText(suasach.getHinhanh()+"");
            binding.edtManAddMota.setText(suasach.getMota()+"");
        }
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

    private void initData() {
        List<String> stringList = new ArrayList<>();
        stringList.add("Thể loại sách");
        stringList.add("Kinh Tế");
        stringList.add("Công Nghệ");
        stringList.add("Tâm Lý");
        stringList.add("Truyện");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, stringList);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                theloai = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        binding.btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (suasach == null){
                    themsach();
                }
                else {
                    updsach();
                }
            }
        });
        binding.imgThemAnhsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(Manager_Them_Activity.this)
                        .crop()	    			                //Crop image(Optional), Check Customization for more option
                        .compress(1024)			                //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });
    }
    private void updsach() {
        String str_tensach = binding.edtManAddTensach.getText().toString().trim();
        String str_tentacgia = binding.edtManAddTentacgia.getText().toString().trim();
        String str_soluong = binding.edtManAddSoluong.getText().toString().trim();
        String str_gia = binding.edtManAddGia.getText().toString().trim();
        String str_hinhanh = binding.edtManAddHinhanh.getText().toString().trim();
        String str_mota = binding.edtManAddMota.getText().toString().trim();

        compositeDisposable.add(apiBookApp.updateSach(str_tensach,str_tentacgia,(theloai),str_soluong,str_gia,str_hinhanh,str_mota,suasach.getId())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        messengerModel -> {
                            if (messengerModel.isSuccess()){
                                Toast.makeText(getApplicationContext(), messengerModel.getMessage(), Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), messengerModel.getMessage(), Toast.LENGTH_LONG).show();
                            }},
                        throwable -> {
                            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }));

    }

    private void themsach() {
        String str_tensach = binding.edtManAddTensach.getText().toString().trim();
        String str_tentacgia = binding.edtManAddTentacgia.getText().toString().trim();
        String str_soluong = binding.edtManAddSoluong.getText().toString().trim();
        String str_gia = binding.edtManAddGia.getText().toString().trim();
        String str_hinhanh = binding.edtManAddHinhanh.getText().toString().trim();
        String str_mota = binding.edtManAddMota.getText().toString().trim();
        compositeDisposable.add(apiBookApp.insertSach(str_tensach,str_tentacgia,(theloai),str_soluong,str_gia,str_hinhanh,str_mota)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        messengerModel -> {
                            if (messengerModel.isSuccess()){
                                Toast.makeText(getApplicationContext(), messengerModel.getMessage(), Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), messengerModel.getMessage(), Toast.LENGTH_LONG).show();
                            }},
                        throwable -> {
                            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }));

    }

    private void AnhXa() {
        spinner = findViewById(R.id.spinner_theloai);
        toolbar = findViewById(R.id.toolbar_themsach);
    }
    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}















