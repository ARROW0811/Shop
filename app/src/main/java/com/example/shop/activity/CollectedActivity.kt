package com.example.shop.activity

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.entity.Goods
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.shop.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shop.MyApplication
import com.example.shop.adapter.CollectAdapter
import com.example.shop.adapter.GoodsAdapter2
import com.example.shop.util.L
import com.example.shop.util.LoginStateUtil
import com.example.shop.util.T
import kotlinx.coroutines.runBlocking
import java.util.ArrayList

class CollectedActivity : AppCompatActivity() {
    lateinit var mIvBack: ImageView
    private var mTvCollected: TextView? = null
    private var recyclerView: RecyclerView? = null
    private val mGoodsList: MutableList<Goods> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collected)
        initGoods()
        mTvCollected = findViewById(R.id.tv_collection)
        recyclerView = findViewById<View>(R.id.rl_goods2) as RecyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        val adapter = CollectAdapter(mGoodsList)
        recyclerView!!.adapter = adapter
        mIvBack = findViewById(R.id.iv_back)
        mIvBack.setOnClickListener(View.OnClickListener { finish() })
    }

    private fun initGoods() {
        var mGoodList: List<Goods>? =null
        var mGoodsIdList:List<Int>?=null
        runBlocking {
            mGoodsIdList= MyApplication.instance.collectDao
                    .getMyCollect(LoginStateUtil.getInstance(applicationContext).localPhoneNumberOrNull)
        }
        if (mGoodsIdList==null){
            T.showShort(this,"你还没有收藏商品")
            return
        }else{
            var size= mGoodsIdList!!.size
            T.showShort(this,"你收藏了${size}商品:")
            var goodsId=mGoodsIdList.toString()
            L.d("goodsId:${goodsId}")
        }

        for (i in mGoodsIdList?.indices!!){
            val goodsId= mGoodsIdList!![i]
            L.d("第${i}个goodsId:${goodsId}")
            var goods: Goods? =null
            runBlocking {
                goods=goods.let{MyApplication.instance.goodsDao.getGoods(goodsId)}
            }
            val goods1= goods?.let { Goods(it.gid, goods!!.title, goods!!.image, goods!!.price) }
            L.d("goods具体详情：${goods1.toString()}")
            if (goods1 != null) {
                mGoodsList.add(goods1)
            }
        }
    }

    override fun finish() {
        super.finish()
    }
}