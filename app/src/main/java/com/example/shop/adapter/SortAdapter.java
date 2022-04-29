package com.example.shop.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;
import com.example.shop.activity.GoodsDetailActivity;
import com.example.shop.activity.SortDetailActivity;
import com.example.shop.entity.Goods;
import com.example.shop.entity.Sort;

import java.util.List;

public class SortAdapter extends RecyclerView.Adapter<SortAdapter.ViewHolder> {
    private List<Sort> mSortList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View mSortView;
        ImageView mIvSort;
        TextView mTvSortName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mSortView=itemView;
            mIvSort =(ImageView) itemView.findViewById(R.id.iv_sort);
            mTvSortName =(TextView) itemView.findViewById(R.id.tv_sortName);
        }
    }
    public SortAdapter(List<Sort> sortList){
        mSortList=sortList;
    }
    @NonNull
    @Override
    public SortAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sort_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SortAdapter.ViewHolder holder, int position) {
        Sort sort =mSortList.get(position);
        holder.mIvSort.setImageResource(sort.getImageId());
        holder.mTvSortName.setText(sort.getName());
        holder.mSortView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Sort sort=mSortList.get(position);
                Intent intent=new Intent();
                intent.putExtra("sortName",sort.getName());
                intent.setClass(view.getContext(), SortDetailActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSortList.size();
    }
}
