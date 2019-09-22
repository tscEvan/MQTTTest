package com.example.mqtttest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences.Editor editor;
    private TextInputEditText ed_account;
    private TextInputEditText ed_passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPreferences = getSharedPreferences("Account", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        ed_account = findViewById(R.id.login_ed_account);
        ed_passwd = findViewById(R.id.login_ed_passwd);
    }

    public void login(View view) {
        String account = ed_account.getText().toString();
        String passwd = ed_passwd.getText().toString();
        if (!account.isEmpty() && !passwd.isEmpty()) {
            editor.putString("ACCOUNT_NAME", account);
            editor.commit();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            LoginActivity.this.finish();
        } else {
            new AlertDialog.Builder(this).setMessage("Please enter your account name and the password").setPositiveButton("OK", null).show();
        }
    }
}
