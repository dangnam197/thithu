package com.example.thithu.reading.ReadingQuest.fragment.Secsion3;

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
import com.example.thithu.model.EventCheckAnswer;
import com.example.thithu.model.ListeningsSection3Question;

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
public class ReadingP3 extends Fragment implements UIApp.FragmentListeningS3, View.OnClickListener, QuestListeningS3Adapter.OnClickListener {
    private ReadingP3Presenter presenter;
    private int id;
    private TextView tvDialogResult;
    private Dialog dialogResult;
    private LinearLayout btnSubmit;
    private Button btnCheck, btnFinish;
    private EventCheckAnswer eventCheckAnswer;
    private RecyclerView recyclerView;
    private static final String TAG = "ReadingP1";
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listening_secsion3, container, false);
        init(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        presenter = new ReadingP3Presenter(getContext(), this);
        if (bundle != null) {
            id = bundle.getInt("ID");
        }

    }

    private void init(View view) {
        initDilogResult();
        btnSubmit = view.findViewById(R.id.listening_s3_btn_submit);
        btnSubmit.setOnClickListener(this);
        recyclerView = view.findViewById(R.id.listening_s3_recycler);
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
    public void setDataRecyclerView(ArrayList<ListeningsSection3Question> list) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        QuestListeningS3Adapter questListeningS3 = new QuestListeningS3Adapter(getContext(),list,this);
        recyclerView.setAdapter(questListeningS3);
    }

    @Override
    public void showDialogResult(int correct, int total) {
        tvDialogResult.setText(correct + "/" + total);
        dialogResult.show();
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.listening_s3_btn_submit:
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
    public void itemText(int poisition, String text) {
        Log.d(TAG, "itemText: "+poisition);
        presenter.updateAnswerCheck(poisition,text);
    }
}
