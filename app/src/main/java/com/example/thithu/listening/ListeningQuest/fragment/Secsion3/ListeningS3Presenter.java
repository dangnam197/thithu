package com.example.thithu.listening.ListeningQuest.fragment.Secsion3;

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
import com.example.thithu.model.ListeningsSection3Question;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ListeningS3Presenter implements OnStringListener {
    UIApp.FragmentListeningS3 fragmentListeningS2;
    private Gson gson;
    private ApiData apiData;
    private Context context;
    private ArrayList<AnswerCheck> listAnswerCheck;
    private ArrayList<ListeningsSection3Question> list;
    private static String rootlinks1 = "data.listeningssection3question/sid=";
    private int id;
    private AppDataBase dataBase;
    private static final String TAG = "ReadingP1Presenter";

    public ListeningS3Presenter(Context context, UIApp.FragmentListeningS3 fragmentListeningS2) {
        this.context = context;
        this.fragmentListeningS2 = fragmentListeningS2;
        apiData = new ApiData();
        gson = new Gson();
        listAnswerCheck = new ArrayList<>();
        this.id = -1;
        dataBase = AppDataBase.getAppDatabase(context);

    }

    public void setData(int id) {
        String link = "";
        link = context.getString(R.string.rootLink) + rootlinks1 + id;
        apiData.getListData(link, this);
        this.id = id;
        Log.d(TAG, "setData: "+link);

    }

    @Override
    public void onSuccess(String result) {
//        listeningsSection2 = gson.fromJson(result, ListeningsSection2.class);
//        fragmentListeningS2.setImageView(context.getString(R.string.rootLink)+"file/image/"+listeningsSection2.getQuestion());
//        fragmentListeningS2.setDataRecyclerView(listeningsSection2);
//       // Log.d(TAG, "onSuccess: " + result);
    }

    @Override
    public void onListSuccess(String result) {
        TypeToken<List<ListeningsSection3Question>> token = new TypeToken<List<ListeningsSection3Question>>() {
        };
        list = gson.fromJson(result, token.getType());
        fragmentListeningS2.setDataRecyclerView(list);
        setListAnswerCheck(list.size());
        Log.d(TAG, "onListSuccess: "+result);
    }

    @Override
    public void onJSonArray(JSONArray jsonArray) {

    }

    public void setListAnswerCheck(int size) {
        for (int i = 0; i < size; i++) {
            listAnswerCheck.add(new AnswerCheck(i));
        }
    }

    public void updateAnswerCheck(int poisition, String text) {
        if (list != null) {

            if (text.toLowerCase().equals(list.get(poisition).getAnswer().toLowerCase())) {
                listAnswerCheck.get(poisition).setCheckAnswer(true);
            }

        }

    }

    public void clickSubmit() {
        int i = 0;
        for (AnswerCheck answerCheck : listAnswerCheck) {
            if (answerCheck.isCheckAnswer()) {
                i++;
            }
        }
        fragmentListeningS2.showDialogResult(i, listAnswerCheck.size());
        if (this.id != -1) {
            Mark mark = dataBase.MarkDao().getMarkSection(IType.LISTENINGS_SECTION3, this.id);
            if (mark != null) {
                if (i > mark.getPoint()) {
                    mark.setPoint(i);
                }
                mark.setMax(listAnswerCheck.size());
                dataBase.MarkDao().update(mark);
            } else {
                mark = new Mark(IType.LISTENINGS_SECTION3, this.id, i, listAnswerCheck.size());
                dataBase.MarkDao().insert(mark);
            }
        }
    }
}
