package com.example.app_ban_sach.ultil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class checkConnect {
    public static boolean haveNetWorkConnection(Context context){
        // Khởi tạo 2 giá trị Wifi và Mobile với giá trị false
        boolean haveConnectdWifi = false;
        boolean haveConnectdMobile = false;

        // Xác định màn hình nào
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = cm.getAllNetworkInfo();
        // Sử dụng vòng lặp for lặp cho đến khi bắt được tính hiệu
        for (NetworkInfo ni : networkInfos){
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectdWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if(ni.isConnected())
                    haveConnectdMobile = true;
        }
        return haveConnectdWifi || haveConnectdMobile;
    }

    public static void ShowToast(Context context, String thongbao){
        Toast.makeText(context, thongbao, Toast.LENGTH_SHORT).show();
    }

}
