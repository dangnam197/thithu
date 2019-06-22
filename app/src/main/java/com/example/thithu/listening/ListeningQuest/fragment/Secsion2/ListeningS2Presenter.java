package com.example.thithu.listening.ListeningQuest.fragment.Secsion2;

import android.content.Context;
import android.util.Log;

import com.example.thithu.IType;
import com.example.thithu.R;
import com.example.thithu.UIApp;
import com.example.thithu.api.ApiData;
import com.example.thithu.api.OnStringListener;
import com.example.thithu.database.database.AppDataBase;
import com.example.thithu.database.entity.Mark;
import com.example.thithu.model.AnswerCheck;
import com.example.thithu.model.ListeningsSection2;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

public class ListeningS2Presenter implements OnStringListener {
    UIApp.FragmentListeningS2 fragmentListeningS2;
    private Gson gson;
    private ApiData apiData;
    private Context context;
    private ArrayList<AnswerCheck> listAnswerCheck;
    private static String rootlinks1 = "data.listeningssection2/";
    private int id;
    private AppDataBase dataBase;
    private static final String TAG = "ReadingP1Presenter";
    private ListeningsSection2 listeningsSection2;
    public ListeningS2Presenter(Context context, UIApp.FragmentListeningS2 fragmentListeningS2) {
        this.context = context;
        this.fragmentListeningS2 = fragmentListeningS2;
        apiData = new ApiData();
        gson = new Gson();
        listAnswerCheck = new ArrayList<>();
        this.id = -1;
        dataBase = AppDataBase.getAppDatabase(context);
        setListAnswerCheck(5);
    }
    public void setData(int id){
        String link = "";
        link = context.getString(R.string.rootLink) + rootlinks1+id;
        apiData.getData(link,this);
        this.id = id;

    }
    @Override
    public void onSuccess(String result) {
        listeningsSection2 = gson.fromJson(result, ListeningsSection2.class);
        fragmentListeningS2.setImageView(context.getString(R.string.rootLink)+"file/image/"+listeningsSection2.getQuestion());
        fragmentListeningS2.setDataRecyclerView(listeningsSection2);
       // Log.d(TAG, "onSuccess: " + result);
    }
    @Override
    public void onListSuccess(String result) {

    }

    @Override
    public void onJSonArray(JSONArray jsonArray) {

    }
    public void setListAnswerCheck(int size){
        for(int i=0;i<size;i++){
            listAnswerCheck.add(new AnswerCheck(i));
        }
    }
    public void updateAnswerCheck(int poisition,String text){
        if(listeningsSection2!=null) {
            switch (poisition) {
                case 0:
                    if (text.toLowerCase().equals(listeningsSection2.getQ1().toLowerCase())) {
                        listAnswerCheck.get(poisition).setCheckAnswer(true);
                    }
                    break;
                case 1:
                    if (text.toLowerCase().equals(listeningsSection2.getQ2().toLowerCase())) {
                        listAnswerCheck.get(poisition).setCheckAnswer(true);
                    }
                    break;
                case 2:
                    if (text.toLowerCase().equals(listeningsSection2.getQ3().toLowerCase())) {
                        listAnswerCheck.get(poisition).setCheckAnswer(true);
                    }
                    break;
                case 3:
                    if (text.toLowerCase().equals(listeningsSection2.getQ4().toLowerCase())) {
                        listAnswerCheck.get(poisition).setCheckAnswer(true);
                    }
                    break;
                case 4:
                    if (text.toLowerCase().equals(listeningsSection2.getQ5().toLowerCase())) {
                        listAnswerCheck.get(poisition).setCheckAnswer(true);
                    }
                    break;

            }
        }

    }

    public void clickSubmit(){
        int i=0;
        for(AnswerCheck answerCheck:listAnswerCheck){
            if(answerCheck.isCheckAnswer()){
                i++;
            }
        }
        fragmentListeningS2.showDialogResult(i,listAnswerCheck.size());
        if (this.id!=-1){
            Mark mark = dataBase.MarkDao().getMarkSection(IType.LISTENINGS_SECTION2,this.id);
            if(mark!=null){
                if(i>mark.getPoint()){
                    mark.setPoint(i);
                }
                mark.setMax(listAnswerCheck.size());
                dataBase.MarkDao().update(mark);
            }else {
                mark = new Mark(IType.LISTENINGS_SECTION2,this.id,i,listAnswerCheck.size());
                dataBase.MarkDao().insert(mark);
            }
        }
    }
}
