package com.example.thithu.writting.ReadingList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.thithu.IType;
import com.example.thithu.R;
import com.example.thithu.UIApp;
import com.example.thithu.adapter.ListAdapter;
import com.example.thithu.model.ListListening;
import com.example.thithu.writting.ReadingQuest.test.WritingAcitivity;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WrittingListActivity extends AppCompatActivity implements UIApp.IListeningListView {
    private Toolbar toolbar;
    private WrittingListPresenter presenter;
    private RecyclerView recyclerView;
    private static final String TAG = "WrittingListActivity";
    private ListAdapter listAdapter;
    private int type;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisening_list);
        presenter = new WrittingListPresenter(this,this);
        getDataInten();
        initView();
        init();
    }

    private void getDataInten(){
        Intent intent = getIntent();
        type = IType.WRITINGS;
    }
    private void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar_listening_list);
        recyclerView = findViewById(R.id.listening_list_recycler);



    }
    private void init(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        presenter.setData(type);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ReadingActivity.this, "đã clcik", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
        @SuppressLint("WrongConstant") LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
//        listAdapter = new ListAdapter(this,presenter,null);
//        recyclerView.setAdapter(listAdapter);


    }

    @Override
    public void setAdapterRecycler(ArrayList<ListListening> list) {
       // Log.d(TAG, "setAdapterRecycler: "+list.get(0).getTitle());
        listAdapter = new ListAdapter(this,presenter,list);
        recyclerView.setAdapter(listAdapter);


    }

    @Override
    public void startActivity(int type, ListListening listListening) {
        Intent intent = new Intent(WrittingListActivity.this, WritingAcitivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("TYPE",type);
        intent.putExtra("ITEM",listListening);
        startActivity(intent);
    }


}
