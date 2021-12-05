package com.example.diary.fragment;

import static com.example.diary.utils.UriUtils.getFileAbsolutePath;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.style.DynamicDrawableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.diary.Adapter.DiaryAdapter;
import com.example.diary.R;
import com.example.diary.dialog.DiaryDialog;
import com.example.diary.entity.Chat;
import com.example.diary.entity.Diary;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FragmentDiary extends Fragment {


    Uri imageTemp;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Diary> dataList;
    private Context context;
    private Fragment myself;

    private void showDialog() {
        View view = LayoutInflater.from(this.context).inflate(R.layout.dialog_diary,null,false);

        final AlertDialog dialog = new AlertDialog.Builder(this.context).setView(view).create();

        TextInputEditText title;
        TextInputEditText content;
        String image_url;



        Button commit;
        Button cancel;
        Button upload_image;

        title = view.findViewById(R.id.input_title);
        content = view.findViewById(R.id.input_content);
        commit = view.findViewById(R.id.commit_button);
        cancel = view.findViewById(R.id.cancel_button);
        upload_image = view.findViewById(R.id.add_image);

        commit.setOnClickListener(v -> {
            String date = null;
            Date dates = new Date();
            DateFormat formater = new SimpleDateFormat("yyyy.MM.dd");
            String imageLocate = getFileAbsolutePath(context,imageTemp);
            Diary temp = new Diary(title.getText().toString(),content.getText().toString(),formater.format(dates),imageLocate);
            dataList.add(temp);
            String buffer = JSON.toJSONString(dataList);
            SharedPreferences.Editor editor= context.getSharedPreferences("diary",Context.MODE_PRIVATE).edit();
            editor.putString("data_diary",buffer);
            editor.commit();
            onResume();
            dialog.dismiss();
        });
        cancel.setOnClickListener(v -> dialog.dismiss());
        upload_image.setOnClickListener(v -> {
            getImage();
        });
        dialog.show();
    }

    private void getImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, 2);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 2) {
            if (data != null) {
                imageTemp = data.getData();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_diary,container,false);

        context = view.getContext();

        // create data process
        {
            ImageButton view_float = view.findViewById(R.id.floating_action_button);
            view_float.setOnClickListener(v -> showDialog());
        }


        SharedPreferences sharedPreferences = context.getSharedPreferences("diary",Context.MODE_PRIVATE);

        String buffer = sharedPreferences.getString("data_diary","");

        dataList = JSON.parseArray(buffer,Diary.class);

        if (dataList == null) {
            dataList = new ArrayList<>();
        }

        mAdapter = new DiaryAdapter(dataList);

        RecyclerView recyclerView = view.findViewById(R.id.recycle);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL, false));

        return view;
    }
}


