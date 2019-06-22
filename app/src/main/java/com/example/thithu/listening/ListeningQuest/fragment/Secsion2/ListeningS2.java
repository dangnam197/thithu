package com.example.thithu.listening.ListeningQuest.fragment.Secsion2;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thithu.R;
import com.example.thithu.UIApp;
import com.example.thithu.adapter.QuestListeningS2;
import com.example.thithu.model.AnswerCheck;
import com.example.thithu.model.EventCheckAnswer;
import com.example.thithu.model.ListeningsSection2;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * te
 */
public class ListeningS2 extends Fragment implements UIApp.FragmentListeningS2, View.OnClickListener, QuestListeningS2.OnClickListener {
    private ListeningS2Presenter presenter;
    private int id;
    private TextView tvDialogResult;
    private Dialog dialogResult;
    private RecyclerView recyclerViewSure;
    private LinearLayout btnSubmit;
    private Button btnCheck, btnFinish;
    private EventCheckAnswer eventCheckAnswer;
    private ImageView imageView;
    private RecyclerView recyclerView;
    private static final String TAG = "ReadingP1";
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listening_secsion2, container, false);
        init(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        presenter = new ListeningS2Presenter(getContext(), this);
        if (bundle != null) {
            id = bundle.getInt("ID");
            //Log.d(TAG, "onCreate: "+id);;
        }

    }

    private void init(View view) {
        initDilogResult();
        btnSubmit = view.findViewById(R.id.listening_s2_btn_submit);
        btnSubmit.setOnClickListener(this);
        recyclerView = view.findViewById(R.id.listening_s2_recycler);
        imageView = view.findViewById(R.id.listening_s2_image);
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
    public void setDataRecyclerView(ListeningsSection2 listeningsSection2) {

        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<5;i++){
            list.add(i);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        QuestListeningS2 questListeningS2 = new QuestListeningS2(getContext(),list,this);
        recyclerView.setAdapter(questListeningS2);
    }

    @Override
    public void showDialogResult(int correct, int total) {
        tvDialogResult.setText(correct + "/" + total);
        dialogResult.show();
    }

    @Override
    public void setImageView(String link) {
        Glide.with(getContext()).load(link).into(imageView);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.listening_s2_btn_submit:
                presenter.clickSubmit();
                break;
            case R.id.btn_dialog_result_check:
                eventCheckAnswer = new EventCheckAnswer(true);
                EventBus.getDefault().post(eventCheckAnswer);
                dialogResult.dismiss();
                break;
            case R.id.btn_dialog_result_finish:
                getActivity().onBackPressed();
                break;
        }
    }

    @Override
    public void itemText(int poisition, String text) {
        Log.d(TAG, "itemText: "+poisition);
        presenter.updateAnswerCheck(poisition,text);
    }
}
