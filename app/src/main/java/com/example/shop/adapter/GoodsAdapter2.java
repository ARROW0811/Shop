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
import com.example.shop.util.T;

import java.util.List;

public class GoodsAdapter2 extends RecyclerView.Adapter<GoodsAdapter2.ViewHolder>{
    private List<Goods> mGoodsList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View mGoodsView;
        ImageView mIvGoods;
        ImageView mIvDelete;
        TextView mTvGoodsName;
        TextView mTvGoodsPrice;
        int gid;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mGoodsView=itemView;
            mIvDelete=itemView.findViewById(R.id.iv_delete);
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Goods goods =mGoodsList.get(position);
        holder.mIvGoods.setImageResource(goods.getImage());
        holder.mTvGoodsName.setText(goods.getTitle());
        holder.mTvGoodsPrice.setText(goods.getPrice());
        holder.gid=goods.getGid();
        holder.mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                T.showShort(view.getContext(), "删除成功");
            }
        });
        holder.mGoodsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                T.showShort(view.getContext(), "查看详情");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGoodsList.size();
    }
}