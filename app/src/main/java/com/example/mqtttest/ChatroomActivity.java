package com.example.mqtttest;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.mqtttest.mqtt.MqttHelper;
import com.example.mqtttest.mqtt.MqttHelperFunction;
import com.example.mqtttest.recyclerMQTT.MQTTFunction;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import static com.example.mqtttest.MainActivity.INTENT_GET_IMAGE;
import static com.example.mqtttest.MainActivity.PHOTO;
import static com.example.mqtttest.MainActivity.TEXT;

public class ChatroomActivity extends AppCompatActivity {

    private static final String TAG = ChatroomActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private EditText editText;
    private SharedPreferences sharedPreferences;
    private String myClientId;
    private String topic;
    private MqttHelperFunction mqttHelperFunction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        recyclerView = findViewById(R.id.recyclerView);
        editText = findViewById(R.id.editText);

        sharedPreferences = getSharedPreferences("Account", MODE_PRIVATE);
//        myClientId = "Evan";
        myClientId = MainActivity.myClientId;
        if (myClientId.isEmpty()) {
            Intent loginPage = new Intent(this,LoginActivity.class);
            startActivity(loginPage);
            this.finish();
        } else {
            topic = "NCKU_TOPIC";
            mqttHelperFunction = new MqttHelperFunction(this,myClientId, topic,recyclerView);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mqttHelperFunction.startSubscribe();
    }

    public void buttonPublisher(View view) {
        mqttHelperFunction.buttonPublisher(editText.getText().toString());
        editText.setText("");
    }

    public void imgPublisher(View view) {
        Intent imgIntent = new Intent();
        imgIntent.setType("image/*").setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(imgIntent, INTENT_GET_IMAGE);
    }

    // activity for result

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case INTENT_GET_IMAGE:
                if (resultCode==RESULT_OK) {
                    if (data != null) {
                        Uri uri = data.getData();
                        ContentResolver cr = this.getContentResolver();
                        try {
                            Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                            byte[] imageByte = byteArrayOutputStream.toByteArray();
                            String imgEncode = Base64.encodeToString(imageByte,Base64.DEFAULT);
                            mqttHelperFunction.imgPublisher(imgEncode);
                            Log.d(TAG, "photo encode :"+ imgEncode);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
        }
    }

    // options Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_settings:
                mqttHelperFunction.startSubscribe();
                return true;
            case R.id.action_lab_ead:
                startActivity(new Intent(this,LabEatActivity.class));
                return true;
            case R.id.action_logout:
                boolean clear_account_name = sharedPreferences.edit().putString("ACCOUNT_NAME", "").commit();
                if (clear_account_name) {
                    myClientId="";
                    Intent loginPage = new Intent(this,LoginActivity.class);
                    startActivity(loginPage);
                    this.finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
