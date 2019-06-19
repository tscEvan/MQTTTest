package com.example.mqtttest.recyclerPhoto;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mqtttest.R;

import java.util.ArrayList;

public class PhotoDialog extends Dialog {
    Context context;
    ArrayList<PhotoBean> arrayList;
    int item;
    private PhotoFunction photoFunction;

    public PhotoDialog(@NonNull Context context,int themeResId, ArrayList<PhotoBean> arrayList, int item) {
        super(context,themeResId);
        this.context = context;
        this.arrayList = arrayList;
        this.item = item;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(attributes);
        setCancelable(true);

        RecyclerView recyclerView = findViewById(R.id.recyclerPhoto);
        TextView txUserNmae = findViewById(R.id.photoScreenUser);
        ImageView imgClose = findViewById(R.id.photoScreenClose);
        imgClose.setOnClickListener(closeListener);
        ImageView imgAllPhoto = findViewById(R.id.photoScreenAllPhoto);
        imgAllPhoto.setOnClickListener(setLayoutManager);

        photoFunction = new PhotoFunction(context,recyclerView,arrayList,item,txUserNmae);
    }

    View.OnClickListener closeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    View.OnClickListener setLayoutManager = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            photoFunction.setGridLayoutManager();
        }
    };
}
