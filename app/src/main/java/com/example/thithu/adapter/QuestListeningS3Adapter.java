package com.example.thithu.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.thithu.R;
import com.example.thithu.model.ListeningsSection3Question;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuestListeningS3Adapter extends RecyclerView.Adapter<QuestListeningS3Adapter.ViewHoder> {
    private Context context;
    private ArrayList<ListeningsSection3Question> list;
    private OnClickListener onClickListener;

    public QuestListeningS3Adapter(Context context, ArrayList<ListeningsSection3Question> list, OnClickListener onClickListener) {
        this.context = context;
        this.list = list;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_input_quest,parent,false);

        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        holder.poisition.setText((position+1)+" "+list.get(position).getQuestion());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        EditText editText;
        TextView poisition;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.item_input_quest);
            poisition = itemView.findViewById(R.id.item_input_quest_text);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    onClickListener.itemText(getAdapterPosition(),s.toString());
                }
            });
        }
    }
    public interface OnClickListener{
        void itemText(int poisition,String text);
    }
}
