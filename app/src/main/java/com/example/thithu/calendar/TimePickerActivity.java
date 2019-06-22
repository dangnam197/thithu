package com.example.thithu.calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.thithu.R;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TimePickerActivity extends AppCompatActivity {
    private TimePicker timePicker;
    private static final String TAG = "TimePickerActivity";
    private int hour;
    private int minu;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hengiotest);
        initView();
    }

    private void initView() {
        timePicker = findViewById(R.id.time_picker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Log.d(TAG, "onTimeChanged: "+hourOfDay+" "+minute);
                hour = hourOfDay;
                minu = minute;
            }
        });
        button = findViewById(R.id.save_time);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
                Log.d(TAG, "onClick: "+"đã lưu" + minu);
            }
        });
    }
    public void start() {
        PendingIntent pendingIntent;
        AlarmManager manager = (AlarmManager) getSystemService(this.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minu);
        calendar.set(Calendar.SECOND,0);

// setRepeating() lets you specify a precise custom interval--in this case,
// 20 minutes.

        Intent myIntent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                24*60*60*1000, pendingIntent);
        Toast.makeText(this, "Đã lưu", Toast.LENGTH_SHORT).show();

    }
}
