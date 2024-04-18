package com.suixingpay.toolsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suixingpay.toolsapp.webview.WebViewRootActivity;

import java.util.ArrayList;
import java.util.List;

class ListItem {
    public String title; // 标题
    public String content; //内容
}
public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    List<ListItem> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        //添加Android自带的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        setDataSource();
        itemAdapter = new ItemAdapter();
        recyclerView.setAdapter(itemAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
    }

    protected  void setDataSource() {
        ListItem item = new ListItem();
        item.title = "WebView";
        item.content = "用于测试H5在Android App内运行状况";
        itemList.add(item);
    }
    class ItemAdapter extends RecyclerView.Adapter<MainViewHoder> {

        @NonNull
        @Override
        public MainViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(MainActivity.this, R.layout.activity_main_list_item, null);
            MainViewHoder mainViewHoder = new MainViewHoder(view);
            return mainViewHoder;
        }

        @Override
        public void onBindViewHolder(@NonNull MainViewHoder holder, int position) {
            ListItem news = itemList.get(position);
            holder.titleText.setText(news.title);
            holder.contentText.setText(news.content);
            holder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("MainActivity", "onClick position:" + position);
                    // 点击了webview
                    if(position == 0 ) {
                        Intent intent = new Intent(MainActivity.this, WebViewRootActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }
    }

    class MainViewHoder extends RecyclerView.ViewHolder {
        TextView titleText;
        TextView contentText;
        ConstraintLayout rootView;
        public MainViewHoder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.title);
            contentText = itemView.findViewById(R.id.content);
            rootView = itemView.findViewById(R.id.rootview);
        }
    }
}