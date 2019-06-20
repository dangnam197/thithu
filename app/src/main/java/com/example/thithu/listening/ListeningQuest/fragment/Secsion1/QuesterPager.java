package com.example.thithu.listening.ListeningQuest.fragment.Secsion1;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thithu.R;
import com.example.thithu.adapter.QuestAdapter;
import com.example.thithu.api.ApiData;
import com.example.thithu.api.OnStringListener;
import com.example.thithu.model.AnswerCheck;
import com.example.thithu.model.EventCheckAnswer;
import com.example.thithu.model.ListeningsSection1Answers;
import com.example.thithu.model.ListeningsSection1Question;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class QuesterPager extends Fragment implements OnStringListener, QuestAdapter.ClickListener, CompoundButton.OnCheckedChangeListener {
    private RecyclerView recyclerView;
    private int poisition,max;
    private ListeningsSection1Question question;
    private TextView tvPoisition,tvQuester;
    private ImageView imageView;
    private ApiData apiData;
    private ArrayList<ListeningsSection1Answers> list;
    private Gson gson;
    private QuestAdapter questAdapter;
    private CheckBox checkBoxSure;
    private static final String TAG = "QuesterPager";
    private int click = -1;
    private boolean check = false;
    private boolean checkAnswer = false;
    private AnswerCheck answerCheck;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle!=null) {
            question = (ListeningsSection1Question) bundle.getSerializable("data");
            poisition = bundle.getInt("poisition", 0);
            max = bundle.getInt("max", 0);
            answerCheck = new AnswerCheck(poisition-1);
        }
        apiData = new ApiData();
        gson = new Gson();
        list = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_quest,container,false);
        initVIew(view);
        if(savedInstanceState==null) {
            init();
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initVIew(View view){
        recyclerView = view.findViewById(R.id.pager_quest_recycleview);
        tvPoisition = view.findViewById(R.id.pager_quest_poisition);
        tvQuester = view.findViewById(R.id.pager_quest_text_quest);
        imageView = view.findViewById(R.id.pager_quest_image_quest);
        checkBoxSure = view.findViewById(R.id.pager_quest_checksure);
        setData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        QuestAdapter questAdapter = new QuestAdapter(getContext(),list,this);
        recyclerView.setAdapter(questAdapter);
        checkBoxSure.setOnCheckedChangeListener(this);

    }
    private void setData(){
        tvPoisition.setText("Question "+poisition+"/"+max);
        if(question.getQuestion()!=null){
            Glide.with(getContext()).load(getContext().getString(R.string.rootLink)+"file/image/"+question.getQuestion()).into(imageView);
        }
    }
    private void init(){
        apiData.getListData(getContext().getString(R.string.rootLink)+"data.listeningssection1answers/qid="+question.getId(),this);
        String link = getContext().getString(R.string.rootLink)+"data.listeningssection1answers/qid="+question.getId();
        Log.d(TAG, "init: "+link);
    }
    @Subscribe
    public void onEventCheckAnswerEvent(EventCheckAnswer checkAnswer){
        this.checkAnswer = checkAnswer.isCheck();
        questAdapter.setCheckAnswers(checkAnswer.isCheck());
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("list",list);
        outState.putInt("click",click);
        outState.putBoolean("check",check);
        outState.putSerializable("data",question);
        outState.putInt("poisition",poisition);
        outState.putInt("max",max);
        outState.putBoolean("checkanswer",checkAnswer);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState!=null){
            question = (ListeningsSection1Question) savedInstanceState.getSerializable("data");
            poisition = savedInstanceState.getInt("poisition", 0);
            max = savedInstanceState.getInt("max", 0);
            list = (ArrayList<ListeningsSection1Answers>)savedInstanceState.getSerializable("list");
            click = savedInstanceState.getInt("click",-1);
            check = savedInstanceState.getBoolean("check",false);
            checkAnswer = savedInstanceState.getBoolean("checkanswer",false);
            setData();
            setDataRecyclerView(list);
            questAdapter.update(click);
            questAdapter.setCheckAnswers(checkAnswer);
        }
    }

    @Override
    public void onSuccess(String result) {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onListSuccess(String result) {
        TypeToken<List<ListeningsSection1Answers>> token = new TypeToken<List<ListeningsSection1Answers>>() {
        };
        list = gson.fromJson(result, token.getType());
//        list.add(new ListeningsSection1Answers(1,"A",(short)0,1));
//        list.add(new ListeningsSection1Answers(1,"B",(short)0,1));
//        list.add(new ListeningsSection1Answers(1,"C",(short)0,1));
//        list.add(new ListeningsSection1Answers(1,"D",(short)0,1));
        setDataRecyclerView(list);
        Log.d(TAG, "onListSuccess: "+list.size());

    }
    private void setDataRecyclerView(ArrayList<ListeningsSection1Answers> list){
        questAdapter = new QuestAdapter(getContext(),list,this);
        recyclerView.setAdapter(questAdapter);
    }
    @Override
    public void onJSonArray(JSONArray jsonArray) {

    }

    @Override
    public void itemClick(int poisition, ListeningsSection1Answers answers) {
        this.click = poisition;
        questAdapter.update(poisition);
        if (answers.getValue()==1){
            answerCheck.setCheckAnswer(true);
        }else {
            answerCheck.setCheckAnswer(false);
        }
        EventBus.getDefault().post(answerCheck);
        Log.d(TAG, "itemClick: "+answers.toString());
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        answerCheck.setCheckSure(isChecked);
        EventBus.getDefault().post(answerCheck);
    }
}
