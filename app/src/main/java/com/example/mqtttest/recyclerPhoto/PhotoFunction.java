package com.example.mqtttest.recyclerPhoto;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PhotoFunction {

    Context context;
    RecyclerView recyclerView;
    ArrayList<PhotoBean> arrayList;
    RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayout.HORIZONTAL,true);
    private final PhotoAdapter adapter;

    public PhotoFunction(Context context, RecyclerView recyclerView, ArrayList<PhotoBean> arrayList, int item, TextView txUserName) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.arrayList = arrayList;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new PhotoAdapter(context,arrayList,txUserName);
        linearLayoutManager.scrollToPosition(item);
//        linearLayoutManager.setReverseLayout(true);
        recyclerView.setAdapter(adapter);
    }

    public void setGridLayoutManager(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
        adapter.setMATCH_PARENT(false);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

}
