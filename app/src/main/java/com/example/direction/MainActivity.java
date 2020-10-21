package com.example.direction;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public TextView Latitude;
    public TextView Longtitude;
    public Button button;
    public LocationManager locationManager;
    public String commadStr;
    public static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                MY_PERMISSION_ACCESS_COARSE_LOCATION);/*開啟手機定位權限*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        commadStr = LocationManager.GPS_PROVIDER; /*使用GPS定位*/
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(commadStr, 1000, 0, locationListener);
                /*事件的條件設定為時間間隔1秒，距離改變0米，設定監聽位置變化*/
                Location location = locationManager.getLastKnownLocation(commadStr);/*取得當下的位置資料*/

                if (location != null) {
                    Longtitude.setText("經度:" + location.getLongitude());
                    Latitude.setText("緯度:" + location.getLatitude());
                } else {
                    Longtitude.setText("定位中");
                    Latitude.setText("定位中");
                }
            }
        });
    }


    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Longtitude.setText("經度:" + location.getLongitude());
            Latitude.setText("緯度:" + location.getLatitude());
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
        @Override
        public void onProviderEnabled(String provider) {
        }
        @Override
        public void onProviderDisabled(String provider) {
        }
    };
}
