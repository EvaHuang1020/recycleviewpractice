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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

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
    public OkHttpClient client ;
    public ExecutorService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycleview = findViewById(R.id.mReycleview);
        fakebmi = new ArrayList<>();
        bmibig50 = new ArrayList<>();

        client = new OkHttpClient();
        service = Executors.newSingleThreadExecutor();
        myAdapter = new MyAdapter(fakebmi);

        mRecycleview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mRecycleview.setAdapter(myAdapter);
        GlobalVariable gv = (GlobalVariable) getApplicationContext();
        Toast.makeText(getApplicationContext(),"Username:"+gv.getUsername()+"Token"+gv.getToken(),Toast.LENGTH_SHORT).show();



        service.submit(new Runnable() {
            @Override
            public void run() {

                Request request = new Request.Builder().url("http://140.133.78.44:63914/Selectitembyplace/Selectitembyplace?item_place=265-Y9&Token="+gv.getToken()).build();
                try {
                    final Response response = client.newCall(request).execute();
                    final String resStr = response.body().string();
                    JSONObject jsonObject = new JSONObject(resStr);
                        String username = jsonObject.getString("UserName");
                        String token =  jsonObject.getString("Token");
                  Log.e("名稱",username);
                  Log.e("Token",token);




                } catch (JSONException e) {
                    Log.e("json錯誤",e.getMessage());
                } catch (IOException e) {
                Log.e("錯誤",e.getMessage());
                }
            }
        });





    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<String> mbmi;

        public MyAdapter(List<String> bmi) {
            mbmi = bmi;
        }


        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bmicardview, parent, false);
            ViewHolder vh = new ViewHolder(v);

//            vh.checkBox.setChecked(false);
//            vh.checkBox.setClickable(false);
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = vh.getAdapterPosition();

                    bmibig50.set(position, !bmibig50.get(position));
                    vh.checkBox.setChecked(bmibig50.get(position));


                }
            });
            return vh;
        }


        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            holder.text_item_id.setText(mbmi.get(position));


//            holder.item_id.setText(item_id);
//            holder.item_name.setText(nameSet.get(item_id));
//            holder.checkBox.setVisibility(View.GONE);


        }


        @Override
        public int getItemCount() {
            return mbmi.size();
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