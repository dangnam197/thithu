package com.example.thithu.listening.ListeningMain;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.thithu.IType;
import com.example.thithu.R;
import com.example.thithu.UIApp;
import com.example.thithu.listening.ListeningList.ListeningListActivity;
import com.example.thithu.main.MainPresenter;

public class ListeningActivity extends AppCompatActivity implements UIApp.IListeningView, View.OnClickListener {
    private MainPresenter mainPresenter;
    private Toolbar toolbar;
    private LinearLayout linearS1,linearS2,linearS3,linearS4;
    private ListeningPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening);
        presenter = new ListeningPresenter(this,this);
        initView();
        init();
        // mainPresenter.setData()

    }
    private void initView(){
        toolbar = (Toolbar) findViewById(R.id.listening_toolbar);
        linearS1 = findViewById(R.id.listening_s1);
        linearS2 = findViewById(R.id.listening_s2);
        linearS3 = findViewById(R.id.listening_s3);
        linearS4 = findViewById(R.id.listening_s4);
        linearS1.setOnClickListener(this);
        linearS2.setOnClickListener(this);
        linearS3.setOnClickListener(this);
        linearS4.setOnClickListener(this);

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

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.listening_s1:
                presenter.clickS1();
                break;
            case R.id.listening_s2:
                presenter.clickS2();
                break;
            case R.id.listening_s3:
                presenter.clickS3();
                break;
            case R.id.listening_s4:
                presenter.clickS4();
                break;
            case R.id.listening_toolbar:
                Toast.makeText(ListeningActivity.this, "đã clcik", Toast.LENGTH_SHORT).show();
                onBackPressed();
                break;
        }
    }

    @Override
    public void startActivityS1() {
        Intent intent = new Intent(ListeningActivity.this, ListeningListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("TYPE", IType.LISTENINGS_SECTION1);
        startActivity(intent);
    }

    @Override
    public void startActivityS2() {
        Intent intent = new Intent(ListeningActivity.this, ListeningListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("TYPE", IType.LISTENINGS_SECTION2);
        startActivity(intent);
    }

    @Override
    public void startActivityS3() {
        Intent intent = new Intent(ListeningActivity.this, ListeningListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("TYPE", IType.LISTENINGS_SECTION3);
        startActivity(intent);
    }

    @Override
    public void startActivityS4() {
        Intent intent = new Intent(ListeningActivity.this, ListeningListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("TYPE", IType.LISTENINGS_SECTION_4);
        startActivity(intent);
    }
}
