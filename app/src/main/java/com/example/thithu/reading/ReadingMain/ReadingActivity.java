package com.example.thithu.reading.ReadingMain;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.thithu.IType;
import com.example.thithu.R;
import com.example.thithu.UIApp;
import com.example.thithu.main.MainPresenter;
import com.example.thithu.reading.ReadingList.ReadingListActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ReadingActivity extends AppCompatActivity implements UIApp.IListeningView, View.OnClickListener {
    private MainPresenter mainPresenter;
    private Toolbar toolbar;
    private LinearLayout linearS1,linearS2,linearS3,linearS4;
    private ReadingPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        presenter = new ReadingPresenter(this,this);
        initView();
        init();
        // mainPresenter.setData()

    }
    private void initView(){
        toolbar = (Toolbar) findViewById(R.id.reading_toolbar);
        linearS1 = findViewById(R.id.reading_s1);
        linearS2 = findViewById(R.id.reading_s2);
        linearS3 = findViewById(R.id.reading_s3);
        linearS4 = findViewById(R.id.reading_s4);
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
                Toast.makeText(ReadingActivity.this, "đã clcik", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.reading_s1:
                presenter.clickS1();
                break;
            case R.id.reading_s2:
                presenter.clickS2();
                break;
            case R.id.reading_s3:
                presenter.clickS3();
                break;
            case R.id.reading_s4:
                presenter.clickS4();
                break;
            case R.id.reading_toolbar:
                Toast.makeText(ReadingActivity.this, "đã clcik", Toast.LENGTH_SHORT).show();
                onBackPressed();
                break;
        }
    }

    @Override
    public void startActivityS1() {
        Intent intent = new Intent(ReadingActivity.this, ReadingListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("TYPE", IType.READINGS_PART1);
        startActivity(intent);
    }

    @Override
    public void startActivityS2() {
        Intent intent = new Intent(ReadingActivity.this, ReadingListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("TYPE", IType.READINGS_PART2);
        startActivity(intent);
    }

    @Override
    public void startActivityS3() {
        Intent intent = new Intent(ReadingActivity.this, ReadingListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("TYPE", IType.READINGS_PART3);
        startActivity(intent);
    }

    @Override
    public void startActivityS4() {
//        Intent intent = new Intent(ReadingActivity.this, WrittingListActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        intent.putExtra("TYPE", IType.LISTENINGS_SECTION_4);
//        startActivity(intent);
    }
}
