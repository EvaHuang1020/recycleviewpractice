package com.example.recycleviewpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class LoginActivity extends AppCompatActivity {
    public EditText username;
    public EditText password;
    public Button login;
    public OkHttpClient client;
    public ExecutorService service;
    GlobalVariable gv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        gv = (GlobalVariable) getApplicationContext();
        username = (EditText) findViewById(R.id.userId);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        client = new OkHttpClient();
        service = Executors.newSingleThreadExecutor();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.submit(new Runnable() {
                    @Override

                    public void run() {

                        Request request = new Request.Builder().url("http://140.133.78.44:63914/Login/Login?id=" + username.getText().toString().trim() + "&password=" + password.getText().toString().trim() + "&phoneid=none").build();
                        try {
                            final Response response = client.newCall(request).execute();
                            final String resStr = response.body().string();
                            JSONObject jsonObject = new JSONObject(resStr);
                            String access = jsonObject.getString("Access").trim();

                            switch (access) {

                                case ("登入成功"): {
                                    String username = jsonObject.getString("UserName").trim();
                                    String token = jsonObject.getString("Token").trim();
                                    Log.e("名稱", username);
                                    Log.e("Token", token);

                                    gv.setUsername(username);
                                    gv.setToken(token);
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    break;
                                }
                                case ("帳號或密碼錯誤"): {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(), "帳號或密碼錯誤", Toast.LENGTH_SHORT).show();
                                        }
                                    });


                                    break;
                                }

                            }


                        } catch (JSONException e) {
                            Log.e("json錯誤", e.getMessage());
                        } catch (IOException e) {
                            Log.e("錯誤", e.getMessage());
                        }

                    }

                });

            }
        });
    }
}