package com.example.thithu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thithu.R;
import com.example.thithu.model.AnswerCheck;

import java.util.ArrayList;

public class SureAdapter extends RecyclerView.Adapter<SureAdapter.ViewHolder> {
    private static int CHECK_TRUE = 1;
    private static int CHECK_FALSE = 0;
    private Context context;
    private ArrayList<AnswerCheck> list;
    private SureAdapter.OnClickListener onClickListener;
    public SureAdapter(Context context, ArrayList<AnswerCheck> list,OnClickListener onClickListener) {
        this.context = context;
        this.list = list;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public SureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);

            View view = inflater.inflate(R.layout.item_sure, viewGroup, false);
            return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SureAdapter.ViewHolder viewHolder, int i) {
        if(viewHolder!=null){
            viewHolder.textView.setText(list.get(i).getName());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position).isCheckSure()){
            return  1;
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_sure);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.clickItem(list.get(getAdapterPosition()).getPoisition());
                }
            });
        }

    }
    public interface OnClickListener{
        void clickItem(int poisition);
    }
}
