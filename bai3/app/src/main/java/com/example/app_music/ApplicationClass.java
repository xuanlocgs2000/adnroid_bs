package com.example.app_music;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class ApplicationClass extends Application {
    public static final String CHANNEL_ID_1="CHANNEL_1";
    public static final String CHANNEL_ID_2="CHANNEL_2";
    public static final String ACTION_NEXT="NEXT";
    public static final String ACTION_PREV="PREVIOUS";
    public static final String ACTION_PLAY="PLAY";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChanel();
    }

    private void createNotificationChanel() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel1 = new NotificationChannel(CHANNEL_ID_1,
                    "channel(1)", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel1.setDescription("CHANNEL1_DES");
            NotificationChannel notificationChannel2 = new NotificationChannel(CHANNEL_ID_2,
                    "channel(2)", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel1.setDescription("CHANNEL2_DES");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel1);
            notificationManager.createNotificationChannel(notificationChannel2);
            }
        }
    }

