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
import com.example.shop.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;

public class BuyedActivity extends AppCompatActivity {
    private ImageView mIvBack;
    private TextView mTvBuyed;
    private RecyclerView recyclerView;
    private List<GoodsBean> goodsBeanList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyed);
        initGoods();
        mTvBuyed =findViewById(R.id.tv_buyed);
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