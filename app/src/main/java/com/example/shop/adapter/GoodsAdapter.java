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
import com.example.shop.util.T;

import java.util.List;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder>{
    private List<Goods> mGoodsList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View mGoodsView;
        ImageView mIvGoods;
        TextView mTvGoodsName;
        TextView mTvGoodsPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mGoodsView=itemView;
            mIvGoods=(ImageView) itemView.findViewById(R.id.iv_goods);
            mTvGoodsName=(TextView) itemView.findViewById(R.id.tv_goodsName);
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
        holder.mIvGoods.setImageResource(goods.getImageId());
        holder.mTvGoodsName.setText(goods.getName());
        holder.mTvGoodsPrice.setText(goods.getPrice());
        holder.mGoodsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Goods goods = mGoodsList.get(position);
                T.showShort(view.getContext(), "进入详情");
                Intent intent=new Intent();
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
