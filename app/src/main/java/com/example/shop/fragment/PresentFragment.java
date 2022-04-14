package com.example.shop.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shop.R;
import com.example.shop.adapter.FreeGoodsAdapter;
import com.example.shop.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;


public class PresentFragment extends Fragment {

    private List<GoodsBean> goodsBeanList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_present, container, false);
        initGoodsBean();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rl_goods_free);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        FreeGoodsAdapter adapter = new FreeGoodsAdapter(goodsBeanList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void initGoodsBean() {
        for (int i = 0; i < 10; i++) {
            GoodsBean goods1 = new GoodsBean("免费商品", R.drawable.icon);
            goodsBeanList.add(goods1);
        }
    }
}