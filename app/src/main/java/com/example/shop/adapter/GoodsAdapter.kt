package com.example.shop.adapter

import android.content.Context
import com.example.shop.entity.Goods
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.example.shop.R
import android.view.ViewGroup
import android.view.LayoutInflater
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.example.shop.activity.GoodsDetailActivity
import com.example.shop.util.SetImageBitmapUtil
import kotlinx.coroutines.NonDisposableHandle.parent

internal class GoodsAdapter(private val mGoodsList: List<Goods>) : RecyclerView.Adapter<GoodsAdapter.ViewHolder>() {
    private lateinit var mContext: Context
    internal class ViewHolder(var mGoodsView: View) : RecyclerView.ViewHolder(mGoodsView) {
        var mIvGoods: ImageView
        var mTvGoodsTitle: TextView
        var mTvGoodsPrice: TextView
        var gid = 0

        init {
            mIvGoods = itemView.findViewById<View>(R.id.iv_goods) as ImageView
            mTvGoodsTitle = itemView.findViewById<View>(R.id.tv_goodsName) as TextView
            mTvGoodsPrice = itemView.findViewById<View>(R.id.tv_goodsPrice) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.goods_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val goods = mGoodsList[position]
        //holder.mIvGoods.setImageResource(R.drawable.icon)
        holder.mIvGoods.setImageURI(Uri.parse(goods.image))
        //SetImageBitmapUtil.setBitmap("content://media/external_primary/images/media/9321",holder.mIvGoods,mContext)
        holder.mTvGoodsTitle.text = goods.title
        holder.mTvGoodsPrice.text = goods.price
        holder.gid = goods.gid
        holder.mGoodsView.setOnClickListener { view ->
            val position = holder.adapterPosition
            val goods = mGoodsList[position]
            val intent = Intent()
            intent.putExtra("gid", goods.gid)
            intent.setClass(view.context, GoodsDetailActivity::class.java)
            view.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mGoodsList.size
    }
}