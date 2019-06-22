package com.example.thithu.reading.ReadingQuest.fragment.Secsion1;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thithu.R;
import com.example.thithu.UIApp;
import com.example.thithu.adapter.QuestListeningS3Adapter;
import com.example.thithu.adapter.ReadingP1Adapter;
import com.example.thithu.model.EventCheckAnswer;
import com.example.thithu.model.ListeningsSection3Question;
import com.example.thithu.model.ReadingsPart1;
import com.example.thithu.model.ReadingsPart1Question;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * te
 */
public class ReadingP1 extends Fragment implements UIApp.FragmentReadingP1, View.OnClickListener, ReadingP1Adapter.OnClickListener {
    private ReadingP1Presenter presenter;
    private int id;
    private TextView tvDialogResult,readP1text;
    private Dialog dialogResult;
    private LinearLayout btnSubmit;
    private Button btnCheck, btnFinish;
    private EventCheckAnswer eventCheckAnswer;
    private RecyclerView recyclerView;
    private static final String TAG = "ReadingP1";
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reading_p1, container, false);
        init(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        presenter = new ReadingP1Presenter(getContext(), this);
        if (bundle != null) {
            id = bundle.getInt("ID");
        }

    }

    private void init(View view) {
        initDilogResult();
        btnSubmit = view.findViewById(R.id.reading_p1_btn_submit);
        btnSubmit.setOnClickListener(this);
        recyclerView = view.findViewById(R.id.reading_p1_recycler);
        readP1text = view.findViewById(R.id.reading_p1_text);
        presenter.setData(id);

    }

    private void initDilogResult() {
        dialogResult = new Dialog(getContext());
        dialogResult.setContentView(R.layout.dialog_result);
        dialogResult.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tvDialogResult = dialogResult.findViewById(R.id.tv_dialog_result);
        btnCheck = dialogResult.findViewById(R.id.btn_dialog_result_check);
        btnFinish = dialogResult.findViewById(R.id.btn_dialog_result_finish);

        btnFinish.setOnClickListener(this);
        btnCheck.setOnClickListener(this);

    }




    @Override
    public void setDataRecyclerView(ArrayList<ReadingsPart1Question> listReadingP1) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ReadingP1Adapter readingP1Adapter = new ReadingP1Adapter(getContext(),listReadingP1,this);
        recyclerView.setAdapter(readingP1Adapter);
    }

    @Override
    public void showDialogResult(int correct, int total) {
        tvDialogResult.setText(correct + "/" + total);
        dialogResult.show();
    }

    @Override
    public void setTextReading(String text) {
        readP1text.setText(text);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.reading_p1_btn_submit:
                presenter.clickSubmit();
                break;
            case R.id.btn_dialog_result_check:
                eventCheckAnswer = new EventCheckAnswer(true);
                EventBus.getDefault().post(eventCheckAnswer);
                dialogResult.dismiss();
                break;
        }
    }
    @Override
    public void itemText(int poisition, boolean answer) {
        presenter.updateAnswerCheck(poisition,answer);
    }
}
