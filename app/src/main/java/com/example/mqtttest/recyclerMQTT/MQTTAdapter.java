package com.example.mqtttest.recyclerMQTT;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mqtttest.MainActivity;
import com.example.mqtttest.R;
import com.example.mqtttest.recyclerPhoto.PhotoBean;
import com.example.mqtttest.recyclerPhoto.PhotoDialog;

import java.util.ArrayList;

public class MQTTAdapter extends RecyclerView.Adapter<MQTTAdapter.MQTTHolder> {

    Context context;
    ArrayList<MQTTBean> arrayList;
    String myClientId;
    RecyclerView recyclerView;

    ArrayList<PhotoBean> photoArrayList = new ArrayList<>();

    public MQTTAdapter(Context context, ArrayList<MQTTBean> arrayList, String myClientId, RecyclerView recyclerView) {
        this.context = context;
        this.arrayList = arrayList;
        this.myClientId = myClientId;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public MQTTHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.chatroom_layout, viewGroup, false);
        return new MQTTHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MQTTHolder mqttHolder, int i) {
        Log.d("TAG", "onBindViewHolder: "+i);
        switch (arrayList.get(i).type) {
            case MainActivity.TEXT:
                mqttHolder.txMessage.setText(arrayList.get(i).getMessage());
                mqttHolder.messageImg.setVisibility(View.GONE);
                break;
            case MainActivity.PHOTO:
                mqttHolder.txMessage.setVisibility(View.GONE);
                mqttHolder.messageImg.setVisibility(View.VISIBLE);
                byte[] decodeByte = Base64.decode(arrayList.get(i).getMessage().getBytes(),Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(decodeByte,0,decodeByte.length);
                mqttHolder.messageImg.setImageBitmap(bitmap);
                mqttHolder.messageImg.setOnClickListener(showImage);
                int photoArrayListItemNum = photoArrayList.size();
                mqttHolder.messageImg.setTag(photoArrayListItemNum);
                Log.d("TAG", "onBindViewHolderPHOTO: "+photoArrayListItemNum);
                photoArrayList.add(new PhotoBean(decodeByte,arrayList.get(i).getId(), photoArrayListItemNum));
                break;
        }
        if (arrayList.get(i).getId().equals(myClientId)) {
            mqttHolder.itemLayout.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
            mqttHolder.imgOtherUser.setVisibility(View.GONE);
            mqttHolder.imgUser.setVisibility(View.VISIBLE);
        } else {
            mqttHolder.imgOtherUser.setVisibility(View.VISIBLE);
            mqttHolder.imgUser.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MQTTHolder extends RecyclerView.ViewHolder {
        LinearLayout itemLayout;
        TextView txMessage;
        ImageView imgUser, imgOtherUser, messageImg;

        public MQTTHolder(@NonNull View itemView) {
            super(itemView);
            txMessage = itemView.findViewById(R.id.chatroomMessage);
            messageImg = itemView.findViewById(R.id.chatroomImg);
            imgUser = itemView.findViewById(R.id.chatroomUser);
            imgOtherUser = itemView.findViewById(R.id.chatroomOtherUser);
            itemLayout = itemView.findViewById(R.id.chatroomLayout);
        }
    }

    View.OnClickListener showImage = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent photoPage = new Intent(context, PhotoActivity.class);
//            int item = (int) v.getTag();
//            photoPage.putExtra("PHOTO_SCREEN_ITEM_NUM", item);
//            photoPage.putExtra("PHOTO_ARRAY_LIST", photoArrayList);
//            context.startActivity(photoPage);
//            android.support.v7.app.ActionBar actionBar = context.getSupportActionBar();
//            actionBar.hide();

//            int itemNum = getIntent().getIntExtra("PHOTO_SCREEN_ITEM_NUM",0);
//            ArrayList<PhotoBean> photo = (ArrayList<PhotoBean>) getIntent().getSerializableExtra("PHOTO_ARRAY_LIST");

//            RecyclerView recyclerView = findViewById(R.id.recyclerPhoto);

//            new PhotoFunction(context, recyclerView, photoArrayList, (int) v.getTag());
                new PhotoDialog(context,R.style.PhotoDialog,photoArrayList,(int) v.getTag()).show();
        }
    };
}
