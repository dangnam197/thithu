package com.example.thithu.listening.ListeningQuest.fragment.Secsion1;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thithu.R;
import com.example.thithu.UIApp;
import com.example.thithu.adapter.QuestPagerAdapter;
import com.example.thithu.adapter.SureAdapter;
import com.example.thithu.model.AnswerCheck;
import com.example.thithu.model.EventCheckAnswer;
import com.example.thithu.model.SetPagerEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * te
 */
public class ListeningS1 extends Fragment implements UIApp.FragmentListeningS1, SureAdapter.OnClickListener, View.OnClickListener {
    private ArrayList<Fragment> listFragment;
    private FragmentManager fragmentManager;
    private ViewPager viewPager;
    private ListeningS1Presenter presenter;
    private int id;
    private TextView tvDialogResult;
    private Dialog dialogSure, dialogResult;
    private RecyclerView recyclerViewSure;
    private LinearLayout btnSure, btnSubmit;
    private Button btnCheck, btnFinish;
    private EventCheckAnswer eventCheckAnswer;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listening_secsion1, container, false);
        init(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        presenter = new ListeningS1Presenter(getContext(), this);
        if (bundle != null) {
            id = bundle.getInt("ID");
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void init(View view) {
        initDilogResult();
        initDilogSure();
        listFragment = new ArrayList<>();
        viewPager = view.findViewById(R.id.listening_quest_pager);
        btnSure = view.findViewById(R.id.btn_sure);
        btnSubmit = view.findViewById(R.id.btn_submit);
        btnSure.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        fragmentManager = getChildFragmentManager();
        presenter.setData(id);

    }

    private void initDilogSure() {
        dialogSure = new Dialog(getContext());
        dialogSure.setContentView(R.layout.dialog_sure);
        dialogSure.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        recyclerViewSure = dialogSure.findViewById(R.id.recycler_sure);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewSure.setLayoutManager(layoutManager);
        recyclerViewSure.setAdapter(new SureAdapter(getContext(), null, this));
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
    public void setPageAdapter(ArrayList<Fragment> listFragment) {
        FragmentManager fragmentManager = getChildFragmentManager();
        QuestPagerAdapter questPagerAdapter = new QuestPagerAdapter(fragmentManager, listFragment);
        viewPager.setAdapter(questPagerAdapter);
    }

    public void setPager(int poisition) {
        viewPager.setCurrentItem(poisition);
    }

    @Subscribe
    public void onSetPagerEvent(SetPagerEvent setPagerEvent) {
        viewPager.setCurrentItem(setPagerEvent.getPoisition());
    }
    @Subscribe
    public void onAnswerCheckEvent(AnswerCheck answerCheck) {
        presenter.updateAnswerCheck(answerCheck);
    }
    @Override
    public void showDialogSure(ArrayList<AnswerCheck> listAnswerCheck) {
        recyclerViewSure.setAdapter(new SureAdapter(getContext(), listAnswerCheck, this));
        dialogSure.show();
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
            case R.id.btn_sure:
                presenter.clickSure();
                break;
            case R.id.btn_submit:
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
    public void clickItem(int poisition) {
        setPager(poisition);
        dialogSure.dismiss();
    }
}
