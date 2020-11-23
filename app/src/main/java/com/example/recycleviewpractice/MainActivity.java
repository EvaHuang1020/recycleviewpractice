package com.example.recycleviewpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public RecyclerView mRecycleview;
    public List<String> fakebmi;
    public List<Boolean> bmibig50;
    public MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycleview = findViewById(R.id.mReycleview);
        fakebmi = new ArrayList<>();
        bmibig50 = new ArrayList<>();

        for (int i = 0; i < 20; i++) {

            fakebmi.add(String.valueOf((Math.random() * 100)));
            bmibig50.add(false);
        }
        myAdapter = new MyAdapter(fakebmi);

        mRecycleview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mRecycleview.setAdapter(myAdapter);


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


            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = vh.getAdapterPosition();

                  bmibig50.set(position,!bmibig50.get(position));
                  vh.checkBox.setChecked(bmibig50.get(position));



                }
            });



            return vh;
        }


        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {



            holder.showbmi.setText(mbmi.get(position));

            if (Float.parseFloat(mbmi.get(position)) > 50) {

                holder.showbmi.setTextColor(Color.rgb(255, 0, 0));


            }
            else {
                holder.showbmi.setTextColor(Color.rgb(0, 0, 0));

            }


            holder.checkBox.setChecked(bmibig50.get(position));

//            holder.item_id.setText(item_id);
//
//            holder.item_name.setText(nameSet.get(item_id));
//
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

            public TextView showbmi;
            public CheckBox checkBox;

            public ViewHolder(View v) {
                super(v);
//                item_id = v.findViewById(R.id.item_id);
//                item_name = v.findViewById(R.id.mission_name);
//                checkBox = v.findViewById(R.id.checkBox);
                showbmi = v.findViewById(R.id.bmitextview);
                checkBox = v.findViewById(R.id.checkBox);

            }
        }

    }
}