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
import com.example.shop.entity.Goods;

import java.util.List;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder>{
    private List<Goods> mGoodsList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View mGoodsView;
        ImageView mIvGoods;
        TextView mTvGoodsTitle;
        TextView mTvGoodsPrice;
        int gid;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mGoodsView=itemView;
            mIvGoods=(ImageView) itemView.findViewById(R.id.iv_goods);
            mTvGoodsTitle =(TextView) itemView.findViewById(R.id.tv_goodsName);
            mTvGoodsPrice=(TextView) itemView.findViewById(R.id.tv_goodsPrice);
        }
    }
    public GoodsAdapter(List<Goods> goodsList){
        mGoodsList=goodsList;
    }
    @NonNull
    @Override
    public GoodsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsAdapter.ViewHolder holder, int position) {
        Goods goods =mGoodsList.get(position);
        holder.mIvGoods.setImageResource(goods.getImage());
        holder.mTvGoodsTitle.setText(goods.getTitle());
        holder.mTvGoodsPrice.setText(goods.getPrice());
        holder.gid=goods.getGid();
        holder.mGoodsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Goods goods = mGoodsList.get(position);
                Intent intent=new Intent();
                intent.putExtra("gid",goods.getGid());
                intent.setClass(view.getContext(), GoodsDetailActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGoodsList.size();
    }
}
