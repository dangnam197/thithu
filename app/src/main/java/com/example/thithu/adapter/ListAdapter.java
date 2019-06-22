package com.example.thithu.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.thithu.R;
import com.example.thithu.RListener;
import com.example.thithu.model.ListListening;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHoder> {
    private Context context;
    private RListener.ListeningList listeningList;
    private LayoutInflater layoutInflater;
    private ArrayList<ListListening> list;
    public ListAdapter(Context context, RListener.ListeningList listeningList, ArrayList<ListListening> list) {
        this.context = context;
        this.listeningList = listeningList;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_list,viewGroup,false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder viewHoder, int i) {
        viewHoder.poisition.setText((i+1)+"");
        viewHoder.progress.setText(list.get(i).getCurrent()+"/"+list.get(i).getDuration());
        viewHoder.title.setText(list.get(i).getTitle());
        viewHoder.progressBar.setMax(list.get(i).getDuration());
        viewHoder.progressBar.setProgress(list.get(i).getCurrent());
    }

    @Override
    public int getItemCount() {
        return (list==null)?0:list.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder{
        TextView poisition,title,progress;
        ProgressBar progressBar;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            poisition = itemView.findViewById(R.id.item_list_poisition);
            title = itemView.findViewById(R.id.item_list_title);
            progress = itemView.findViewById(R.id.item_list_progress);
            progressBar = itemView.findViewById(R.id.item_list_progressBar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listeningList.itemClick(getAdapterPosition(),list.get(getAdapterPosition()));
                }
            });
        }
    }
    public void addAll(ArrayList<ListListening> listListenings){
        list.addAll(0,listListenings);
        notifyDataSetChanged();
    }
}
