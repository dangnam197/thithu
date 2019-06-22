package com.example.thithu.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.thithu.R;
import com.example.thithu.api.ApiData;
import com.example.thithu.api.OnStringListener;
import com.example.thithu.main.MainActivity;
import com.example.thithu.model.AudioFileName;
import com.example.thithu.model.StartAudio;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


public class MediaServiceAll extends Service implements OnStringListener {
    public static String MAIN_ACTION = "com.example.dangn.music.Service.MediaService.action.main";
    public static String PREV_ACTION = "com.example.dangn.music.Service.MediaService.action.prev";
    public static String PLAY_ACTION = "com.example.dangn.music.Service.MediaService.action.play";
    public static String NEXT_ACTION = "com.example.dangn.music.Service.MediaService.action.next";
    public static String SEEKTO_ACTION = "com.example.dangn.music.Service.MediaService.action.seekto";
    public static String UPDATE_ACTION = "com.example.dangn.music.Service.MediaService.action.update";
    public static String PATH_ACTION = "com.example.dangn.music.Service.MediaService.action.path";
    public static String TITLE_ACTION = "com.example.dangn.music.Service.MediaService.action.title";
    public static String ARTIST_ACTION = "com.example.dangn.music.Service.MediaService.action.artist";
    public static String DURATION_ACTION = "com.example.dangn.music.Service.MediaService.action.duration";
    public static String STARTMEDIASERVER_ACTION = "com.example.dangn.music.Service.MediaService.action.startforeground";
    public static String STOPMEDIASERVER_ACTION = "com.example.dangn.music.Service.MediaService.action.stopforeground";
    public static String MEDIAINIT_ACTION = "com.example.dangn.music.Service.MediaService.action.mediainit";
    public static String POISITIONSONGTO_ACTION = "com.example.dangn.music.Service.MediaService.action.positionsongto";
    public static String POISITIONSONG_ACTION = "com.example.dangn.music.Service.MediaService.action.positionsong";
    public static String UPDATEPAUSE_ACTION = "com.example.dangn.music.Service.MediaService.action.updatepause";
    public static String UPDATESTART_ACTION = "com.example.dangn.music.Service.MediaService.action.updatestart";
    public static String UPDATEBUTTON_ACTION = "com.example.dangn.music.Service.MediaService.action.updatebutton";
    public static Boolean isPlay = true;
    public static final int NOTIFICATION_ID_FOREGROUND_SERVICE = 8466503;
    private MediaPlayer mediaPlayer;
    private int positionSong = 0;
    private NotificationManager notificationManager;
    private Boolean isRuning = true;
    private LocalBroadcastManager localBroadcastManager;
    private int seekto = 0;
    private AtomicBoolean isUpdateTime ;
    private static final String TAG = "MediaService";
    private ApiData apiData ;
    private ArrayList<AudioFileName> listAudio;
    @Override
    public void onCreate() {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        isUpdateTime = new AtomicBoolean();
        isUpdateTime.set(true);
        mediaPlayer = new MediaPlayer();
        listAudio = new ArrayList<>();
        mediaComple();
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent.getAction().equals(STARTMEDIASERVER_ACTION)) {
            if (isRuning)
            {
                String link = getApplicationContext().getString(R.string.rootLink)+"file/audio/all";
                isRuning = false;
                apiData = new ApiData();
                apiData.getDataJSonArray(getApplicationContext().getString(R.string.rootLink)+"file/audio/all",this);
                startForeground(1, prepareNotification(intent));
                Log.d(TAG, "onStartCommand: "+link);
            }else {
               // mediaInit();
            }
        }
        else if(intent.getAction().equals(NEXT_ACTION)){
            next();
            notificationManager.notify(1,prepareNotification(intent));
        }
        else if(intent.getAction().equals(PLAY_ACTION)){
            if (mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                isUpdateTime.set(true);
            }else {
                mediaPlayer.start();
                isUpdateTime.set(false);
                synchronized (isUpdateTime){
                    isUpdateTime.notifyAll();
                }
            }
            notificationManager.notify(1,prepareNotification(intent));
        }
        else if(intent.getAction().equals(PREV_ACTION)){
            prev();
            notificationManager.notify(1,prepareNotification(intent));
        }
        else if(intent.getAction().equals(STOPMEDIASERVER_ACTION)){
            stopForeground(true);
            stopSelf();
            mediaPlayer.stop();
            updatePause();
        }
        else if(intent.getAction().equals(SEEKTO_ACTION)){
            seekto = intent.getIntExtra(SEEKTO_ACTION,0);
            mediaPlayer.seekTo(seekto);
        }
        else if(intent.getAction().equals(POISITIONSONGTO_ACTION)){
            int poisition = intent.getIntExtra(POISITIONSONGTO_ACTION,0);
            setMediaData(positionSong);
            updateStart();
            mediaPlayer.start();
            notificationManager.notify(1,prepareNotification(intent));
        }
        else if(intent.getAction().equals(UPDATESTART_ACTION)){
            if(mediaPlayer.isPlaying())
            {
                updateStart();
            }
        }
        else if(intent.getAction().equals(UPDATEPAUSE_ACTION)){
            updatePause();
        }
        else if(intent.getAction().equals(POISITIONSONG_ACTION)){
            sendPoisitionSong();
        }

        return START_NOT_STICKY;
    }
    private void updateStart(){
        isUpdateTime.set(false);
        synchronized (isUpdateTime){
            isUpdateTime.notifyAll();
        }
    }
    private void updatePause(){
        isUpdateTime.set(true);
    }
    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
    }
    public void sendPoisitionSong(){
        Intent intent = new Intent(POISITIONSONG_ACTION);
        intent.putExtra(POISITIONSONG_ACTION,positionSong);
        localBroadcastManager.sendBroadcast(intent);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private Notification prepareNotification(Intent intent){
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        Intent previousIntent = new Intent(this, MediaServiceAll.class);
        previousIntent.setAction(PREV_ACTION);
        PendingIntent ppreviousIntent = PendingIntent.getService(this, 0,
                previousIntent, PendingIntent.FLAG_CANCEL_CURRENT);


        Intent playIntent = new Intent(this, MediaServiceAll.class);
        playIntent.setAction(PLAY_ACTION);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                playIntent, PendingIntent.FLAG_CANCEL_CURRENT);


        Intent nextIntent = new Intent(this, MediaServiceAll.class);
        nextIntent.setAction(NEXT_ACTION);
        PendingIntent pnextIntent = PendingIntent.getService(this, 0,
                nextIntent, PendingIntent.FLAG_CANCEL_CURRENT);


        Intent stopIntent = new Intent(this, MediaServiceAll.class);
        stopIntent.setAction(STOPMEDIASERVER_ACTION);
        PendingIntent pstopIntent = PendingIntent.getService(this, 0,
                stopIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        RemoteViews remoteViews = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            remoteViews = new RemoteViews(getPackageName(), R.layout.notification_n);
            remoteViews.setOnClickPendingIntent(R.id.notification_previous,ppreviousIntent);
            remoteViews.setOnClickPendingIntent(R.id.notification_play_pause,pplayIntent);
            remoteViews.setOnClickPendingIntent(R.id.notification_next,pnextIntent);
            remoteViews.setOnClickPendingIntent(R.id.notification_close,pstopIntent);
            if(mediaPlayer.isPlaying()){
                remoteViews.setImageViewResource(R.id.notification_play_pause,R.drawable.ic_pause);
            }
            else {
                remoteViews.setImageViewResource(R.id.notification_play_pause,R.drawable.ic_play);
            }
//            remoteViews.setTextViewText(R.id.notification_title,list.get(positionSong).getTrack_name());
//            remoteViews.setTextViewText(R.id.notification_text,list.get(positionSong).getArtist());
            remoteViews.setImageViewResource(R.id.notification_previous,R.drawable.ic_skip_previous);
            remoteViews.setImageViewResource(R.id.notification_next,R.drawable.ic_skip_next);
            remoteViews.setImageViewResource(R.id.notification_close,R.drawable.ic_close);
        }else {
            remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
            remoteViews.setOnClickPendingIntent(R.id.noti_prev,ppreviousIntent);
            remoteViews.setOnClickPendingIntent(R.id.noti_play_pause,pplayIntent);
            remoteViews.setOnClickPendingIntent(R.id.noti_next,pnextIntent);
            remoteViews.setOnClickPendingIntent(R.id.noti_close,pstopIntent);
           // remoteViews.setTextViewText(R.id.noti_title,list.get(positionSong).getTrack_name());
            //remoteViews.setTextViewText(R.id.noti_info,list.get(positionSong).getArtist());
            if(mediaPlayer.isPlaying()){
                remoteViews.setImageViewResource(R.id.noti_play_pause,R.drawable.ic_pause);
            }
            else {
                remoteViews.setImageViewResource(R.id.noti_play_pause,R.drawable.ic_play);
            }
        }
            
        
//
//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                String NOTIFICATION_CHANNEL_ID = "com.example.thithu.Service.Id";
                NotificationChannel channel = notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID);
                if (channel == null){
                    channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,"dangnam",NotificationManager.IMPORTANCE_MIN);
                    channel.setSound(null,null);
                    notificationManager.createNotificationChannel(channel);
                }
                Notification noti = new Notification.Builder(this,NOTIFICATION_CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_android_black_24dp)
                        .setContentIntent(pendingIntent)
                        .setCustomContentView(remoteViews)
                        .setStyle(new Notification.DecoratedMediaCustomViewStyle())
                        .build();
                noti.flags |= Notification.FLAG_FOREGROUND_SERVICE;
                return noti;
            }else {
                Notification noti = new Notification.Builder(this)
                        .setSmallIcon(R.drawable.ic_android_black_24dp)
                        .setContentIntent(pendingIntent)
                        .setCustomContentView(remoteViews)
                        .setStyle(new Notification.DecoratedMediaCustomViewStyle())
                        .build();
                noti.flags |= Notification.FLAG_FOREGROUND_SERVICE;
                return noti;
            }
        }
        else {

                Notification notification;
                notification = new Notification.Builder(this)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.ic_android_black_24dp)
                        .setContent(remoteViews)
                        .setContentIntent(pendingIntent)
                        .setPriority(Notification.PRIORITY_MAX)
                        .build();
                return notification;
        }
    }
    private void setMediaData(int progress){
        if (mediaPlayer!=null) {
            mediaPlayer.stop();
        }
       mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(getApplicationContext().getString(R.string.rootLink)+"file/audio/"+listAudio.get(progress).getFileNam()));

    }
    public void mediaComple(){
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if(positionSong<listAudio.size()-1) {
                    next();
                }
            }
        });
    }
    private void setMediaData(String url,int poisition){
        if (mediaPlayer!=null) {
            mediaPlayer.stop();
        }
             mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(getApplicationContext().getString(R.string.rootLink)+"file/audio/"+listAudio.get(poisition).getFileNam());
            mediaPlayer.prepare(); // might take long! (for buffering, etc)
            mediaPlayer.start();
            EventBus.getDefault().post(new StartAudio(true,mediaPlayer.getDuration()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void next(){
        positionSong = (positionSong == listAudio.size()-1)? 0:positionSong+1;
        setMediaData(positionSong);
        mediaPlayer.start();

    }
    private  void prev(){
        positionSong = (positionSong == 0)? (listAudio.size()-1):positionSong-1;
        setMediaData(positionSong);
        mediaPlayer.start();

    }

    @Override
    public void onSuccess(String result) {

    }

    @Override
    public void onListSuccess(String result) {
        Gson gson = new Gson();
        TypeToken<List<AudioFileName>> token = new TypeToken<List<AudioFileName>>() {
        };
        listAudio = gson.fromJson(result, token.getType());
        setMediaData(positionSong);
        Log.d(TAG, "filename: "+listAudio.get(0).getFileNam());
    }
    @Override
    public void onJSonArray(JSONArray jsonArray) {
        try {
            for(int i = 0; i < jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                listAudio.add(new AudioFileName(jsonObject.getString("name")));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setMediaData(0);
        Log.d(TAG, "onJSonArray: "+listAudio.get(0).getFileNam());
    }
}