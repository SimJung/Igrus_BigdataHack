package com.company.igrus.jungsim.googlemapapiprac0606_01;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback {
        ButtonRectangle btn1;
        ButtonRectangle btn2;
        ButtonRectangle btn3;

        String title;
        String text;
    private static final String NOTIFICATION_CHANNEL_ID = "channel1_ID";
    private static final String NOTIFICATION_CHANNEL_NAME = "channel1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(final GoogleMap map) {


        btn1 = (ButtonRectangle)findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = "서울";
                text = "현 위치";
                //showNotification(title, text);
                LatLng SEOUL = new LatLng(37.53, 126.98);

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(SEOUL);
                markerOptions.title(title);
                markerOptions.snippet(text);
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                map.addMarker(markerOptions);

                map.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
                map.animateCamera(CameraUpdateFactory.zoomTo(10));
            }
        });

        btn2 = (ButtonRectangle)findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "데이터 수신 중", Toast.LENGTH_LONG).show();


                LatLng Hongimoon = new LatLng(37.600836, 126.967331);
                LatLng Namsan = new LatLng(37.549732, 126.996496);
                LatLng Maebong = new LatLng(37.490712, 127.048493);
                LatLng Sangdo = new LatLng(37.504553, 126.949555);
                LatLng SEOUL = new LatLng(37.53, 126.98);


                SystemClock.sleep(3000);

                map.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
                map.animateCamera(CameraUpdateFactory.zoomTo(10));

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(Hongimoon);
                markerOptions.title("주의!");
                markerOptions.snippet("홍지문 터널 교통량 주의");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                map.addMarker(markerOptions);


                MarkerOptions markerOptions1 = new MarkerOptions();
                markerOptions1.position(Namsan);
                markerOptions1.title("경고!!");
                markerOptions1.snippet("남산 1호 터널 교통량 경고");
                markerOptions1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                map.addMarker(markerOptions1);
                showNotification("경고!", "남산 1호 터널");

                MarkerOptions markerOptions2 = new MarkerOptions();
                markerOptions2.position(Maebong);
                markerOptions2.title("주의!");
                markerOptions2.snippet("매봉 터널 교통량 주의");
                markerOptions2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                map.addMarker(markerOptions2);

                MarkerOptions markerOptions3 = new MarkerOptions();
                markerOptions3.position(Sangdo);
                markerOptions3.title("주의!");
                markerOptions3.snippet("상도 터널 교통량 주의");
                markerOptions3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                map.addMarker(markerOptions3);
            }
        });


    }

    public void showNotification(String title_string, String text_string) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_MAX;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, importance);

            notificationManager.createNotificationChannel(notificationChannel);
        }

        int notificationId = 0;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID);

        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentTitle(title_string);
        builder.setContentText(text_string);
        builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
        notificationManager.notify(notificationId, builder.build());
    }

}

