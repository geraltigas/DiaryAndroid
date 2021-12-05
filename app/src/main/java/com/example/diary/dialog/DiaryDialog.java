package com.example.diary.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.diary.R;
import com.google.android.material.textfield.TextInputLayout;

public class DiaryDialog extends Dialog {

    Activity context;
    Dialog myself;

    TextInputLayout title;
    TextInputLayout content;
    String image_url;

    Button commit;
    Button cancel;
    Button upload_image;

    View.OnClickListener commit_listener;
    View.OnClickListener cancel_listener;
    View.OnClickListener image_listener;

    public void initLisener() {
        commit_listener = v -> System.out.println("commit now.....");

        cancel_listener = v -> myself.cancel();

        image_listener = v -> System.out.println("upload image new.....");
    }

    public DiaryDialog(@NonNull Activity context) {
        super(context,R.style.MyDialog);
        this.context = context;
        this.myself = this;
        initLisener();
    }

    public DiaryDialog(Activity context, int theme) {
        super(context,R.style.MyDialog);
        this.context = context;
        this.myself = this;
        initLisener();
    }

    @Override
    protected void onCreate(Bundle instant) {
        super.onCreate(instant);
        this.setContentView(R.layout.dialog_diary);

        title = findViewById(R.id.input_title);
        content = findViewById(R.id.input_content);
        image_url = null;
        commit = findViewById(R.id.commit_button);
        cancel = findViewById(R.id.cancel_button);
        upload_image = findViewById(R.id.add_image);

        Window dialogWindow = context.getWindow();

        WindowManager windowManager = dialogWindow.getWindowManager();
        Display display = windowManager.getDefaultDisplay();

        WindowManager.LayoutParams params = dialogWindow.getAttributes();
        params.width = (int) (display.getWidth()*0.8);
        params.height = (int) (display.getHeight()*0.8);
        dialogWindow.setAttributes(params);
        setCanceledOnTouchOutside(true);

        this.setCancelable(true);
    }
}
