package com.example.thithu.Listening;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.thithu.R;
import com.example.thithu.UIApp;
import com.example.thithu.main.MainPresenter;

public class ListeningActivity extends AppCompatActivity {
    private MainPresenter mainPresenter;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening);

        initView();
        init();
        // mainPresenter.setData()

    }
    private void initView(){
        toolbar = (Toolbar) findViewById(R.id.listening_toolbar);


    }
    private void init(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListeningActivity.this, "đã clcik", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
//        toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }
}
