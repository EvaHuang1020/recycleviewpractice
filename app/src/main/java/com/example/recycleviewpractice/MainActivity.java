package com.example.recycleviewpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView mRecycleview;
    public List<String> fakebmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycleview = findViewById(R.id.mReycleview);
        fakebmi = new ArrayList<>();




    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
//        private HashMap<String, Boolean> mid;


        public MyAdapter(HashMap<String, Boolean> id) {
//            mid = id;
        }


        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bmicardview, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }


        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {


//            holder.item_id.setText(item_id);
//
//            holder.item_name.setText(nameSet.get(item_id));
//
//            holder.checkBox.setVisibility(View.GONE);


        }


        @Override
        public int getItemCount() {
            return 0;
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
            public Button button;

            public ViewHolder(View v) {
                super(v);
//                item_id = v.findViewById(R.id.item_id);
//                item_name = v.findViewById(R.id.mission_name);
//                checkBox = v.findViewById(R.id.checkBox);
                showbmi = v.findViewById(R.id.bmitextview);
                button = v.findViewById(R.id.button);
            }
        }

    }
}