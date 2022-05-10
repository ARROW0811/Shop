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
import com.example.shop.adapter.GoodsAdapter2
import com.example.shop.util.LoginStateUtil
import com.example.shop.util.T
import kotlinx.coroutines.runBlocking
import java.util.ArrayList

class SoldActivity : AppCompatActivity() {
    lateinit var mIvBack: ImageView
    private var mTvSold: TextView? = null
    private var recyclerView: RecyclerView? = null
    private val goodsList: MutableList<Goods> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sold)
        initGoods()
        mTvSold = findViewById(R.id.tv_sold)
        recyclerView = findViewById<View>(R.id.rl_goods2) as RecyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        val adapter = GoodsAdapter2(goodsList)
        recyclerView!!.adapter = adapter
        mIvBack = findViewById(R.id.iv_back)
        mIvBack.setOnClickListener(View.OnClickListener {
            finish()
            //Intent intent=new Intent(PublishedActivity.this,HomeActivity.class);
            //startActivity(intent);
        })
    }

    private fun initGoods() {
        var mGoodList: List<Goods>? =null
        runBlocking {
            mGoodList= MyApplication.instance.goodsDao
                    .getMySoldGoods(LoginStateUtil.getInstance(applicationContext).localPhoneNumberOrNull)
        }
        if (mGoodList==null){
            T.showShort(applicationContext,"你还没有卖出过商品")
            return
        }
        for (i in mGoodList?.indices!!){
            val goods= mGoodList!!.get(i)
            val goods1=Goods(goods.gid,goods.title,goods.image,goods.price)
            goodsList.add(goods1)
        }
    }

    override fun finish() {
        super.finish()
    }
}