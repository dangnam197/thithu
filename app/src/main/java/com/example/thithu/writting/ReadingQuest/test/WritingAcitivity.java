package com.example.thithu.writting.ReadingQuest.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.thithu.R;
import com.example.thithu.UIApp;
import com.example.thithu.model.ListListening;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WritingAcitivity extends AppCompatActivity implements UIApp.Writting {
    private ListListening listListening;
    private WritingPresenter presenter;
    private TextView tvQuest, tvSample;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writting);
        init();
        getDataInten();
    }

    private void init() {
        getSupportActionBar().setTitle("");
        presenter = new WritingPresenter(this,this);
        tvQuest = findViewById(R.id.writting_quest);
        tvSample = findViewById(R.id.writing_sample);
    }

    private void getDataInten(){
        Intent intent = getIntent();
        listListening = (ListListening)intent.getSerializableExtra("ITEM");
        presenter.getData(listListening.getId());
    }

    @Override
    public void setTvQuest(String quest) {
        tvQuest.setText(quest);
    }

    @Override
    public void setTvSample(String sample) {
        tvSample.setText(sample);

    }
}
