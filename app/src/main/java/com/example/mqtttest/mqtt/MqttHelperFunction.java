package com.example.mqtttest.mqtt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.mqtttest.recyclerMQTT.MQTTFunction;

import static com.example.mqtttest.MainActivity.PHOTO;
import static com.example.mqtttest.MainActivity.TEXT;

public class MqttHelperFunction extends MqttHelper{
    Context context;
    String myClientId;
    String topic;
    RecyclerView recyclerView;
//    MQTTFunction recyclerFunction;

    public MqttHelperFunction(Context context, String myClientId, String topic, RecyclerView recyclerView) {
        super(new MQTTFunction(context,recyclerView,myClientId),topic,myClientId,recyclerView);
        this.context = context;
        this.myClientId = myClientId;
        this.topic = topic;
        this.recyclerView = recyclerView;
    }

    public void buttonPublisher(String msg) {
        startPublisher(msg, TEXT);
    }

    public void buttonPublisher(EditText view) {
        startPublisher(view.getText().toString(), TEXT);
        view.setText("");
    }

    public void imgPublisher(String imgEncode) {
        startPublisher(imgEncode, PHOTO);
        //TODO Please add the following annotation program
        //please add this in your activity

//        public void imgPublisher(View view) {
//            Intent imgIntent = new Intent();
//            imgIntent.setType("image/*").setAction(Intent.ACTION_GET_CONTENT);
//            startActivityForResult(imgIntent, INTENT_GET_IMAGE);
//        }
//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//            switch (requestCode) {
//                case INTENT_GET_IMAGE:
//                    if (resultCode==RESULT_OK) {
//                        if (data != null) {
//                            Uri uri = data.getData();
//                            ContentResolver cr = this.getContentResolver();
//                            try {
//                                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
//                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                                bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
//                                byte[] imageByte = byteArrayOutputStream.toByteArray();
//                                String imgEncode = Base64.encodeToString(imageByte,Base64.DEFAULT);
//                    ---->>      mqtt.startPublisher(imgEncode);
//                                Log.d(TAG, "photo encode :"+ imgEncode);
//                            } catch (FileNotFoundException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                    break;
//            }
//        }
    }
}
