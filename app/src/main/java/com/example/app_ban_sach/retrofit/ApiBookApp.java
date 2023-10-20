package com.example.app_ban_sach.retrofit;

import com.example.app_ban_sach.model.DonHangModel;
import com.example.app_ban_sach.model.UserModel;
import com.example.app_ban_sach.model.messengerModel;
import com.example.app_ban_sach.model.sachcuModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiBookApp {

    @GET("select_oldbook.php")
    Observable<sachcuModel> getSachCu();

    @POST("dangki.php")
    @FormUrlEncoded
    Observable<UserModel> dangKi(
            @Field("email") String email,
            @Field("pass") String pass,
            @Field("username") String username,
            @Field("mobile") String mobile
    );

    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<UserModel> dangNhap(
            @Field("email") String email,
            @Field("pass") String pass
    );

    @POST("donhang.php")
    @FormUrlEncoded
    Observable<UserModel> createrOrder(
            @Field("email") String email,
            @Field("sdt") String sdt,
            @Field("tongtien") String tongtien,
            @Field("iduser") int iduser,
            @Field("diachi") String diachi,
            @Field("soluongtong") int soluongtong,
            @Field("chitiet") String chitiet
    );

    @POST("timkiem.php")
    @FormUrlEncoded
    Observable<sachcuModel> search(
            @Field("search") String search
    );


    @POST("lichsu.php")
    @FormUrlEncoded
    Observable<DonHangModel> lichSuDonHang(
            @Field("iduser") int id
    );

    @POST("insert_sach.php")
    @FormUrlEncoded
    Observable<messengerModel> insertSach(
            @Field("tensach") String tensach,
            @Field("tentacgia") String tentacgia,
            @Field("idtheloai") int idtheloai,
            @Field("soluong") String soluong,
            @Field("gia") String gia,
            @Field("hinhanh") String hinhanh,
            @Field("mota") String mota
    );

    @POST("delete_oldbook.php")
    @FormUrlEncoded
    Observable<sachcuModel> xoaSach(
            @Field("id") int id
    );

    @POST("update_sach.php")
    @FormUrlEncoded
    Observable<messengerModel> updateSach(
            @Field("tensach") String tensach,
            @Field("tentacgia") String tentacgia,
            @Field("idtheloai") int idtheloai,
            @Field("soluong") String soluong,
            @Field("gia") String gia,
            @Field("hinhanh") String hinhanh,
            @Field("mota") String mota,
            @Field("id") int id
    );
}
