package com.example.thithu.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.thithu.R;
import com.example.thithu.model.ListeningsSection3Question;
import com.example.thithu.model.ReadingsPart1Question;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReadingP1Adapter extends RecyclerView.Adapter<ReadingP1Adapter.ViewHoder> {
    private Context context;
    private ArrayList<ReadingsPart1Question> list;
    private OnClickListener onClickListener;

    public ReadingP1Adapter(Context context, ArrayList<ReadingsPart1Question> list, OnClickListener onClickListener) {
        this.context = context;
        this.list = list;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_check,parent,false);

        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        holder.poisition.setText((position+1)+"");
        holder.text.setText(list.get(position).getQuestion());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        TextView poisition,text;
        CheckBox checkTrue,checkFalse,checkNotGiven;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            poisition = itemView.findViewById(R.id.item_check_position);
            text = itemView.findViewById(R.id.item_check_quesition);
            checkTrue = itemView.findViewById(R.id.item_check_true);
            checkFalse = itemView.findViewById(R.id.item_check_false);
            checkNotGiven = itemView.findViewById(R.id.item_check_na);
            checkNotGiven.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(list.get(getAdapterPosition()).getAnswer()==0){
                        onClickListener.itemText(getAdapterPosition(),true);
                    }else {
                        onClickListener.itemText(getAdapterPosition(),false);
                    }
                }
            });
            checkTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(list.get(getAdapterPosition()).getAnswer()==1){
                        onClickListener.itemText(getAdapterPosition(),true);
                    }else {
                        onClickListener.itemText(getAdapterPosition(),false);
                    }
                }
            });
            checkFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(list.get(getAdapterPosition()).getAnswer()==2){
                        onClickListener.itemText(getAdapterPosition(),true);
                    }else {
                        onClickListener.itemText(getAdapterPosition(),false);
                    }
                }
            });

        }


    }
    public interface OnClickListener{
        void itemText(int poisition, boolean answer);
    }
}
