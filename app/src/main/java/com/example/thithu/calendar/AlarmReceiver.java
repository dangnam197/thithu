package com.example.thithu.calendar;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.widget.Toast;

import com.example.thithu.R;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "thongbao_id";

    @Override
    public void onReceive(Context context, Intent intent) {
       // Toast.makeText(context, "ALARM", Toast.LENGTH_LONG).show();
        createNotificationChannel(context);
    }
    private void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        final NotificationManager mNotific=
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        CharSequence name="Ragav";
        String desc="this is notific";
        int imp=NotificationManager.IMPORTANCE_HIGH;
        final String ChannelID="my_channel_01";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel mChannel = new NotificationChannel(ChannelID, name,
                    imp);
            mChannel.setDescription(desc);
            mChannel.setLightColor(Color.CYAN);
            mChannel.canShowBadge();
            mChannel.setShowBadge(true);
            mNotific.createNotificationChannel(mChannel);
        }

        final int ncode=101;

        String Body="Đã đến giờ học tập";

        Notification n= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            n = new Notification.Builder(context,ChannelID)
                    .setContentTitle("Nhắc lịch")
                    .setContentText(Body)
                    .setNumber(5)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setAutoCancel(true)
                    .build();
        }


        mNotific.notify(ncode, n);
    }
}