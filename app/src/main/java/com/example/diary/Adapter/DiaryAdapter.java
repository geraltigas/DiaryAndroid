package com.example.diary.Adapter;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diary.R;
import com.example.diary.entity.Diary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.VHDiary>{

    private List<Diary> dataList;

    public DiaryAdapter(List<Diary> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public VHDiary onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VHDiary(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview_diary, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VHDiary holder, int position) {
        Diary diary = dataList.get(position);
        holder.titleTV.setText(diary.title);
        holder.contentTV.setText(diary.content);
        holder.dateTV.setText(diary.date);
        holder.imageIV.setImageURI(Uri.fromFile(new File(diary.image)));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    protected static class VHDiary extends RecyclerView.ViewHolder {
        ImageView imageIV;
        TextView titleTV;
        TextView contentTV;
        TextView dateTV;

        public VHDiary(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.title);
            contentTV = itemView.findViewById(R.id.content);
            dateTV = itemView.findViewById(R.id.date);
            imageIV = itemView.findViewById(R.id.image);
        }
    }
}
