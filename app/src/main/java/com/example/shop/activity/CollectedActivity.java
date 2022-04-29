package com.example.shop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.adapter.GoodsAdapter2;
import com.example.shop.entity.Goods;

import java.util.ArrayList;
import java.util.List;

public class CollectedActivity extends AppCompatActivity {
    private ImageView mIvBack;
    private TextView mTvCollected;
    private RecyclerView recyclerView;
    private List<Goods> goodsList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collected);
        initGoods();
        mTvCollected =findViewById(R.id.tv_collection);
        recyclerView=(RecyclerView) findViewById(R.id.rl_goods2);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        GoodsAdapter2 adapter=new GoodsAdapter2(goodsList);
        recyclerView.setAdapter(adapter);
        mIvBack=findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initGoods() {
        for (int i=0;i<10;i++){
            Goods goods1=new Goods(1,"商品",R.drawable.icon,"100");
            goodsList.add(goods1);
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}