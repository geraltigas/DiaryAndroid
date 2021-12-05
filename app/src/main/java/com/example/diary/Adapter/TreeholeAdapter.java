package com.example.diary.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diary.R;
import com.example.diary.entity.Chat;
import com.example.diary.entity.Diary;

import java.util.List;

public class TreeholeAdapter extends RecyclerView.Adapter<TreeholeAdapter.VHTreehole>{

    private List<Chat> dataList;

    public TreeholeAdapter(List<Chat> dataList) {
        this.dataList =dataList;
    }

    @NonNull
    @Override
    public VHTreehole onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VHTreehole(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview_chat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VHTreehole holder, int position) {
        Chat chat = dataList.get(position);
        holder.userTV.setText(chat.user);
        holder.realUserTV.setText(chat.realUser);
        holder.contentTV.setText(chat.content);
        holder.dateTV.setText(chat.date);
        holder.imageIV.setImageResource(R.drawable.profile);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    protected static class VHTreehole extends RecyclerView.ViewHolder {
        TextView userTV;
        TextView realUserTV;
        TextView contentTV;
        TextView dateTV;
        ImageView imageIV;
        public VHTreehole(@NonNull View itemView) {
            super(itemView);
            userTV = itemView.findViewById(R.id.user);
            realUserTV = itemView.findViewById(R.id.real_profile);
            contentTV = itemView.findViewById(R.id.content_treehole);
            dateTV = itemView.findViewById(R.id.date_treehole);
            imageIV = itemView.findViewById(R.id.imageView);
        }
    }
}
