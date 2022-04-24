package com.example.shop.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shop.R;
import com.example.shop.adapter.SortAdapter;
import com.example.shop.entity.Goods;
import com.example.shop.entity.Sort;

import java.util.ArrayList;
import java.util.List;


public class SortFragment extends Fragment {

    private List<Sort> sortList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sort, container, false);
        initGoodsBean();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rl_goods_free);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        SortAdapter adapter = new SortAdapter(sortList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void initGoodsBean() {
        Sort sort1 = new Sort("校园代步", R.drawable.sort_bike);
        sortList.add(sort1);
        Sort sort2 = new Sort("手机", R.drawable.sort_phone);
        sortList.add(sort2);
        Sort sort3 = new Sort("电脑", R.drawable.sort_computer);
        sortList.add(sort3);
        Sort sort4 = new Sort("电器", R.drawable.sort_electric);
        sortList.add(sort4);
        Sort sort5 = new Sort("运动健身", R.drawable.sort_sport);
        sortList.add(sort5);
        Sort sort6 = new Sort("图书教材", R.drawable.sort_book);
        sortList.add(sort6);
        Sort sort7 = new Sort("租赁", R.drawable.sort_rent);
        sortList.add(sort7);
        Sort sort8 = new Sort("免费赠送", R.drawable.sort_present);
        sortList.add(sort8);
        Sort sort9 = new Sort("其他", R.drawable.sort_other);
        sortList.add(sort9);

    }
}