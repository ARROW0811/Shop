package com.example.shop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;
import com.example.shop.entity.Goods;

import java.util.List;

public class GoodsAdapter2 extends RecyclerView.Adapter<GoodsAdapter2.ViewHolder>{
    private List<Goods> mGoodsList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mIvGoods;
        TextView mTvGoodsName;
        TextView mTvGoodsPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvGoods=(ImageView) itemView.findViewById(R.id.iv_goods);
            mTvGoodsName=(TextView) itemView.findViewById(R.id.tv_goodsName);
            mTvGoodsPrice=(TextView) itemView.findViewById(R.id.tv_goodsPrice);
        }
    }
    public GoodsAdapter2(List<Goods> goodsList){
        mGoodsList=goodsList;
    }
    @NonNull
    @Override
    public GoodsAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods2_item,parent,false);
        ViewHolder holder=new GoodsAdapter2.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsAdapter2.ViewHolder holder, int position) {
        Goods goods =mGoodsList.get(position);
        holder.mIvGoods.setImageResource(goods.getImageId());
        holder.mTvGoodsName.setText(goods.getName());
        holder.mTvGoodsPrice.setText(goods.getPrice());
    }

    @Override
    public int getItemCount() {
        return mGoodsList.size();
    }
}