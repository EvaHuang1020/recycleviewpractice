package com.example.recycleviewpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public RecyclerView mRecycleview;
    public List<String> fakebmi;
    public List<Boolean> bmibig50;
    public MyAdapter myAdapter;
    public OkHttpClient client;
    public ExecutorService service;
    public ArrayList idlist;
    public List<Fireitem> FireItemList= new ArrayList<>();
    GlobalVariable gv;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gson = new Gson();

        mRecycleview = findViewById(R.id.mReycleview);
        idlist = new ArrayList<>();
        bmibig50 = new ArrayList<>();

        client = new OkHttpClient();
        service = Executors.newSingleThreadExecutor();

        mRecycleview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        gv = (GlobalVariable) getApplicationContext();
        Toast.makeText(getApplicationContext(), "Username:" + gv.getUsername() + "Token" + gv.getToken(), Toast.LENGTH_SHORT).show();


        service.submit(new Runnable() {
            @Override

            public void run() {

                Request request = new Request.Builder().url("http://140.133.78.44:63914/Selectitembyplace/Selectitembyplace?item_place=265-Y9&Token=" + gv.getToken()).build();
                try {
                    final Response response = client.newCall(request).execute();
                    final String resStr = response.body().string();
                    JSONObject jsonObject = new JSONObject(resStr);
                    String newToken = jsonObject.getString("NewToken");
                    gv.setToken(newToken);

                    FireItemList = gson.fromJson(jsonObject.getString("JsonResult"), new TypeToken<List<Fireitem>>() {
                    }.getType());//將json字串直接轉換為FireitemList
                    Log.e("長度", FireItemList.size() + "");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            myAdapter = new MyAdapter(FireItemList);
                            mRecycleview.setAdapter(myAdapter);
                        }
                    });


//                  Log.e("名稱",username);
//                  Log.e("Token",token);


                } catch (JSONException e) {
                    Log.e("json錯誤", e.getMessage());
                } catch (IOException e) {
                    Log.e("錯誤", e.getMessage());
                }
            }
        });


    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<Fireitem> mfireitemList;

        public MyAdapter(List<Fireitem> fireitemList) {
           this. mfireitemList = fireitemList;
        }


        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bmicardview, parent, false);
            ViewHolder vh = new ViewHolder(v);


            return vh;
        }


        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            Fireitem fireitem = mfireitemList.get(position);
            holder.text_item_id.setText(fireitem.item_id);

            holder.text_item_name.setText(fireitem.item_name);
            holder.text_item_status.setText(fireitem.item_status);
            holder.text_item_area.setText(fireitem.item_area);
            holder.checkBox.setChecked(fireitem.ischeck);
//            holder.checkBox.setVisibility(View.GONE);


        }


        @Override
        public int getItemCount() {
            return mfireitemList.size();
        }

//        @Override
//        public int getItemViewType(int position) {
//            return position;
//        }

        public class ViewHolder extends RecyclerView.ViewHolder {
//            public TextView item_id;
//            public TextView item_name;
//            public CheckBox checkBox;

            public TextView text_item_name, text_item_id, text_item_status, text_item_area;
            public CheckBox checkBox;

            public ViewHolder(View v) {
                super(v);
//                item_id = v.findViewById(R.id.item_id);
//                item_name = v.findViewById(R.id.mission_name);
//                checkBox = v.findViewById(R.id.checkBox);
//                showbmi = v.findViewById(R.id.bmitextview);
                text_item_name = v.findViewById(R.id.text_item_name);
                text_item_id = v.findViewById(R.id.text_item_id);
                text_item_status = v.findViewById(R.id.text_item_status);
                text_item_area = v.findViewById(R.id.text_item_area);
                checkBox = v.findViewById(R.id.checkBox);

            }
        }

    }
}