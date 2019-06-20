package com.example.thithu.listening.ListeningList;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.example.thithu.IType;
import com.example.thithu.R;
import com.example.thithu.UIApp;
import com.example.thithu.adapter.ListAdapter;
import com.example.thithu.listening.ListeningQuest.test.ListeningQuestActivitytest;
import com.example.thithu.model.ListListening;

import java.util.ArrayList;

public class ListeningListActivity extends AppCompatActivity implements UIApp.IListeningListView {
    private Toolbar toolbar;
    private ListeningListPresenter presenter;
    private RecyclerView recyclerView;
    private static final String TAG = "ListeningListActivity";
    private ListAdapter listAdapter;
    private int type;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisening_list);
        presenter = new ListeningListPresenter(this,this);
        getDataInten();
        initView();
        init();
    }

    private void getDataInten(){
        Intent intent = getIntent();
        type = intent.getIntExtra("TYPE", IType.LISTENINGS_SECTION1);
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
                //Toast.makeText(ListeningActivity.this, "đã clcik", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
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
        Intent intent = new Intent(ListeningListActivity.this, ListeningQuestActivitytest.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("TYPE",type);
        intent.putExtra("ITEM",listListening);
        startActivity(intent);
    }


}
