package com.example.shop.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shop.R;
import com.example.shop.adapter.GoodsAdapter;
import com.example.shop.entity.Goods;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {
    private List<Goods> mGoodsList=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main, container, false);
        initGoodsBean();
        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.rl_goods);
        StaggeredGridLayoutManager layoutManager=new
                StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        GoodsAdapter goodsAdapter=new GoodsAdapter(mGoodsList);
        recyclerView.setAdapter(goodsAdapter);
        return view;
    }

    private void initGoodsBean() {
        for (int i=0;i<20;i++){
            Goods goods1=new Goods("商品asjdnaosihfaonvoaivnapvnpaisciasncoiasnc",R.drawable.icon,"10.0");
            mGoodsList.add(goods1);
        }
    }
}