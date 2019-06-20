package com.example.thithu.listening.ListeningQuest.fragment.Secsion1;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.widget.Toast;

import com.example.thithu.IType;
import com.example.thithu.R;
import com.example.thithu.UIApp;
import com.example.thithu.api.ApiData;
import com.example.thithu.api.OnStringListener;
import com.example.thithu.database.database.AppDataBase;
import com.example.thithu.database.entity.Mark;
import com.example.thithu.model.AnswerCheck;
import com.example.thithu.model.ListeningsSection1Question;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ListeningS1Presenter implements OnStringListener {
    UIApp.FragmentListeningS1 fragmentListeningS1;
    private Gson gson;
    private ArrayList<ListeningsSection1Question> list;
    private ApiData apiData;
    private Context context;
    private ArrayList<Fragment> listFragments;
    private ArrayList<AnswerCheck> listAnswerCheck;
    private static String rootlinks1 = "data.listeningssection1question/sid=";
    private int id;
    private AppDataBase dataBase;
    public ListeningS1Presenter(Context context, UIApp.FragmentListeningS1 fragmentListeningS1) {
        this.context = context;
        this.fragmentListeningS1 = fragmentListeningS1;
        apiData = new ApiData();
        gson = new Gson();
        listAnswerCheck = new ArrayList<>();
        this.id = -1;
        dataBase = AppDataBase.getAppDatabase(context);
    }
    public void setData(int id){
        String link = "";
        link = context.getString(R.string.rootLink) + rootlinks1+id;
        apiData.getListData(link,this);
        apiData.getData(context.getString(R.string.rootLink)+"data.listeningssection1/"+id,this);
        this.id = id;
    }
    @Override
    public void onSuccess(String result) {

    }
    @Override
    public void onListSuccess(String result) {
        TypeToken<List<ListeningsSection1Question>> token = new TypeToken<List<ListeningsSection1Question>>() {
        };
        list = gson.fromJson(result, token.getType());

        listFragments = new ArrayList<>();
        Toast.makeText(context, ""+list.size(), Toast.LENGTH_SHORT).show();
        for (int i=0;i<list.size();i++){
            Bundle bundle = new Bundle();
            bundle.putSerializable("data",list.get(i));
            bundle.putInt("poisition",i+1);
            bundle.putInt("max",list.size());
            QuesterPager questerPager = new QuesterPager();
            questerPager.setArguments(bundle);
            listFragments.add(questerPager);
        }
        fragmentListeningS1.setPageAdapter(listFragments);
        setListAnswerCheck(list.size());
    }

    @Override
    public void onJSonArray(JSONArray jsonArray) {

    }
    public void setListAnswerCheck(int size){
        for(int i=0;i<size;i++){
            listAnswerCheck.add(new AnswerCheck(i));
        }
    }
    public void updateAnswerCheck(AnswerCheck answerCheck){
        listAnswerCheck.set(answerCheck.getPoisition(),answerCheck);
    }
    public void clickSure(){
        ArrayList<AnswerCheck> listAnswerCheckSureDialog = new ArrayList<>();
        for(AnswerCheck answerCheck:listAnswerCheck){
            if(answerCheck.isCheckSure()){
                listAnswerCheckSureDialog.add(answerCheck);
            }
        }
        fragmentListeningS1.showDialogSure(listAnswerCheckSureDialog);
    }
    public void clickSubmit(){
        int i=0;
        for(AnswerCheck answerCheck:listAnswerCheck){
            if(answerCheck.isCheckAnswer()){
                i++;
            }
        }
        fragmentListeningS1.showDialogResult(i,listAnswerCheck.size());
        if (this.id!=-1){
            Mark mark = dataBase.MarkDao().getMarkSection(IType.LISTENINGS_SECTION1,this.id);
            if(mark!=null){
                if(i>mark.getPoint()){
                    mark.setPoint(i);
                }
                mark.setMax(listAnswerCheck.size());
                dataBase.MarkDao().update(mark);
            }else {
                mark = new Mark(IType.LISTENINGS_SECTION1,this.id,i,listAnswerCheck.size());
                dataBase.MarkDao().insert(mark);
            }
        }
    }
}
