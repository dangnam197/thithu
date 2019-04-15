package com.example.thithu.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.thithu.Listening.ListeningActivity;
import com.example.thithu.R;
import com.example.thithu.UIApp;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, UIApp.IMainActivity {
    private MainPresenter mainPresenter;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private LinearLayout linearLayoutListening,linearLayoutSpeaking,linearLayoutReading,linearLayoutWriting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        init();
       // mainPresenter.setData();

    }
    private void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        linearLayoutListening = findViewById(R.id.line_listening);
        linearLayoutReading  = findViewById(R.id.line_reading);
        linearLayoutSpeaking = findViewById(R.id.line_speaking);
        linearLayoutWriting = findViewById(R.id.line_writing);

        linearLayoutListening.setOnClickListener(this);
        linearLayoutReading.setOnClickListener(this);
        linearLayoutSpeaking.setOnClickListener(this);
        linearLayoutWriting.setOnClickListener(this);
    }
    private void init(){
        setSupportActionBar(toolbar);
        mainPresenter = new MainPresenter(this);
    }

    @Override
    public void setData(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startListeningActivity() {
        Intent intent = new Intent(MainActivity.this, ListeningActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    @Override
    public void starReadingActivity() {

    }

    @Override
    public void starWritingActivity() {

    }

    @Override
    public void starSpeakingActivity() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.line_listening:
                mainPresenter.ListeningClick();
                break;
        }
    }
}
