package com.example.diary.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.diary.Adapter.DiaryAdapter;
import com.example.diary.Adapter.TreeholeAdapter;
import com.example.diary.R;
import com.example.diary.entity.Chat;
import com.example.diary.entity.Diary;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FragmentTreehole extends Fragment {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private Context context;
    private List<Chat> dataList;
    private LayoutInflater brige;
    private ViewGroup container;

    private void showDialog() {
        View view = LayoutInflater.from(this.context).inflate(R.layout.dialog_chat,null,false);

        final AlertDialog dialog = new AlertDialog.Builder(this.context).setView(view).create();

        TextInputEditText content;

        Button commit;
        Button cancel;

        content = view.findViewById(R.id.input_content);
        commit = view.findViewById(R.id.commit_button);
        cancel = view.findViewById(R.id.cancel_button);

        commit.setOnClickListener(v -> {
            String date = null;
            Date dates = new Date();
            DateFormat formater = new SimpleDateFormat("yyyy.MM.dd");
            Chat temp = new Chat("Geraltigas","@jiangbo", content.getText().toString(),formater.format(dates),"null");
            dataList.add(temp);
            String buffer = JSON.toJSONString(dataList);
            SharedPreferences.Editor editor= context.getSharedPreferences("chat",Context.MODE_PRIVATE).edit();
            editor.putString("data_chat",buffer);
            editor.commit();
            onResume();
            dialog.dismiss();
        });
        cancel.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.container = container;
        brige = inflater;

        View view = inflater.inflate(R.layout.fragment_treehole,container,false);

        context = view.getContext();

        // create data process
        {
            ImageButton view_float = view.findViewById(R.id.floating_action_button);
            view_float.setOnClickListener(v -> showDialog());
        }

        SharedPreferences sharedPreferences = context.getSharedPreferences("chat",Context.MODE_PRIVATE);

        String buffer = sharedPreferences.getString("data_chat","");

        dataList = JSON.parseArray(buffer,Chat.class);

        if (dataList == null) {
            dataList = new ArrayList<>();
        }

        mAdapter = new TreeholeAdapter(dataList);

        RecyclerView recyclerView = view.findViewById(R.id.recycle_treehole);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL, false));

        return view;
    }
}
