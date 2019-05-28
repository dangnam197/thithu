package com.example.thithu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.thithu.R;
import com.example.thithu.model.ListeningsSection1Answers;

import java.util.ArrayList;

public class QuestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<ListeningsSection1Answers> listAnswers;
    private ClickListener clickListener;
    private int unClick = -1;
    private int click=-1;
    private boolean checkAnswers;
    private int clickPoisition;
    public QuestAdapter(Context context, ArrayList<ListeningsSection1Answers> listAnswers,ClickListener clickListener) {
        this.clickListener = clickListener;
        this.context = context;
        this.listAnswers = listAnswers;
        this.checkAnswers = false;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_button,viewGroup,false);
        return new ButtonViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ButtonViewHoder buttonViewHoder = (ButtonViewHoder) viewHolder;
        if (checkAnswers){
            buttonViewHoder.button.setText(listAnswers.get(i).getAnswer());
            if(click!=-1){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if(listAnswers.get(i).getValue()==1) {
                        buttonViewHoder.button.setBackground(context.getDrawable(R.drawable.button_background_click));
                        buttonViewHoder.button.setTextColor(Color.WHITE);
                    }
                    if(i==click&&listAnswers.get(i).getValue()!=1){
                        buttonViewHoder.button.setBackground(context.getDrawable(R.drawable.button_backgound_false));
                        buttonViewHoder.button.setTextColor(Color.WHITE);
                    }
                }
            }
        }else {
            buttonViewHoder.button.setText((char)(i+65)+"");
            if(click!=-1){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if(i==click) {
                        buttonViewHoder.button.setBackground(context.getDrawable(R.drawable.button_background_click));
                        buttonViewHoder.button.setTextColor(Color.WHITE);
                    }
                    if(i==unClick){
                        buttonViewHoder.button.setBackground(context.getDrawable(R.drawable.button_background_non));
                        buttonViewHoder.button.setTextColor(Color.BLACK);
                    }
                }
            }
        }

       // buttonViewHoder.button.setClickable();
    }

    @Override
    public int getItemCount() {
        return listAnswers.size();
    }
    public class ButtonViewHoder extends RecyclerView.ViewHolder{
        Button button;
        public ButtonViewHoder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.item_button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    clickListener.itemClick(getAdapterPosition(),listAnswers.get(getAdapterPosition()));
                    clickPoisition = getAdapterPosition();
                }
            });
        }
    }
    public interface ClickListener{
        void itemClick(int poisition,ListeningsSection1Answers answers);
    }
    public void update(int click){
        this.unClick = this.click;
        this.click = click;
        notifyDataSetChanged();
    }
    public void setCheckAnswers(boolean check){
        this.checkAnswers = check;
        notifyDataSetChanged();
    }
}
