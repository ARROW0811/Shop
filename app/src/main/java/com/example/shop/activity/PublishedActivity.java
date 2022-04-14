package com.example.shop.activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.adapter.GoodsAdapter2;
import com.example.shop.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;

public class PublishedActivity extends AppCompatActivity {
    private ImageView mIvBack;
    private TextView mTvPublished;
    private RecyclerView recyclerView;
    private List<GoodsBean> goodsBeanList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_published);
        initGoods();
        mTvPublished=findViewById(R.id.tv_published);
        recyclerView=(RecyclerView) findViewById(R.id.rl_goods2);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        GoodsAdapter2 adapter=new GoodsAdapter2(goodsBeanList);
        recyclerView.setAdapter(adapter);

        mIvBack=findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //Intent intent=new Intent(PublishedActivity.this,HomeActivity.class);
                //startActivity(intent);
            }
        });
    }

    private void initGoods() {
        for (int i=0;i<10;i++){
            GoodsBean goods1=new GoodsBean("商品",R.drawable.icon,"100");
            goodsBeanList.add(goods1);
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}