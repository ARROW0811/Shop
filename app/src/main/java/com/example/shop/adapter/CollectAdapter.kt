package com.example.shop.adapter


import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.example.shop.entity.Goods
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.example.shop.R
import android.view.ViewGroup
import android.view.LayoutInflater
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.example.shop.MyApplication
import com.example.shop.activity.GoodsDetailActivity
import com.example.shop.activity.GoodsDetailActivity2
import com.example.shop.util.LoginStateUtil
import com.example.shop.util.SetImageBitmapUtil
import com.example.shop.util.T
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

internal class CollectAdapter(private val mGoodsList: List<Goods>) : RecyclerView.Adapter<CollectAdapter.ViewHolder>() {
    private lateinit var mContext:Context
    internal class ViewHolder(var mGoodsView: View) : RecyclerView.ViewHolder(mGoodsView) {
        var mIvGoods: ImageView
        var mIvDelete: ImageView
        var mTvGoodsName: TextView
        var mTvGoodsPrice: TextView

        var gid = 0

        init {
            mIvDelete = itemView.findViewById(R.id.iv_delete)
            mIvGoods = itemView.findViewById<View>(R.id.iv_goods) as ImageView
            mTvGoodsName = itemView.findViewById<View>(R.id.tv_goodsName) as TextView
            mTvGoodsPrice = itemView.findViewById<View>(R.id.tv_goodsPrice) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext=parent.context
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.goods2_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val goods = mGoodsList[position]
        //holder.mIvGoods.setImageResource(goods.image)
        holder.mIvGoods.setImageURI(Uri.parse(goods.image))
        //SetImageBitmapUtil.setBitmap(goods.image,holder.mIvGoods,mContext)
        holder.mTvGoodsName.text = goods.title
        holder.mTvGoodsPrice.text = goods.price
        holder.gid = goods.gid
        holder.mIvDelete.setOnClickListener { view ->
            showDeleteDialog(mContext,goods)

        }
        holder.mGoodsView.setOnClickListener { view ->
            val position = holder.adapterPosition
            val goods = mGoodsList[position]
            val intent = Intent()
            intent.putExtra("gid",goods.gid)
            /*
            intent.putExtra("title",goods.title)
            intent.putExtra("image",goods.image)
            intent.putExtra("price",goods.price)
            intent.putExtra("primePrice",goods.primePrice)
            intent.putExtra("describe",goods.describe)
            intent.putExtra("phoneNumber",goods.phoneNumber)
            intent.putExtra("deliver",goods.deliver)
            intent.putExtra("location",goods.location)

             */
            intent.setClass(view.context, GoodsDetailActivity2::class.java)
            view.context.startActivity(intent)
            T.showShort(view.context, "查看详情")
        }
    }

    override fun getItemCount(): Int {
        return mGoodsList.size
    }
    private fun showDeleteDialog(context: Context,goods:Goods) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("消息提示")
        builder.setMessage("您确认要取消收藏吗？")

        val dialogClickListener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    val phoneNumber=LoginStateUtil.getInstance(context).localPhoneNumberOrNull
                    val gid=goods.gid
                    runBlocking {
                        MyApplication.instance.collectDao.deleteCollect(phoneNumber,gid)
                    }
                    T.showShort(context, "取消成功")
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                    Log.e("TAG", "取消")
                }
            }
        }
        builder.setPositiveButton("确认", dialogClickListener)
        builder.setNegativeButton("取消", dialogClickListener)
        builder.create().show();
    }
}