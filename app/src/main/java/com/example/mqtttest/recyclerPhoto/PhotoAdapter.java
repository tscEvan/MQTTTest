package com.example.mqtttest.recyclerPhoto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mqtttest.R;

import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    Context context;
    ArrayList<PhotoBean> arrayList;
    TextView txUserName;
    private boolean MATCH_PARENT = true;

    public PhotoAdapter(Context context, ArrayList<PhotoBean> arrayList, TextView txUserName) {
        this.context = context;
        this.arrayList = arrayList;
        this.txUserName = txUserName;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.photo_screen,viewGroup,false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder photoViewHolder, int i) {
        if (MATCH_PARENT) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            photoViewHolder.photoLayout.setLayoutParams(params);
        }else{
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            photoViewHolder.photoLayout.setLayoutParams(params);
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(arrayList.get(i).getBitmap(),0,arrayList.get(i).getBitmap().length);
        photoViewHolder.imgPhoto.setImageBitmap(bitmap);
        txUserName.setText(arrayList.get(i).id);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout photoLayout;
        ImageView imgPhoto;
        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.photoScreenPhoto);
            photoLayout = itemView.findViewById(R.id.photoScreenLayout);
        }
    }

    public void setMATCH_PARENT(boolean MATCH_PARENT) {
        this.MATCH_PARENT = MATCH_PARENT;
    }
}
