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

class PublishedActivity : AppCompatActivity() {
    lateinit var mIvBack: ImageView
    private var mTvPublished: TextView? = null
    private var recyclerView: RecyclerView? = null
    private val mGoodsList: MutableList<Goods> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_published)
        initGoods()
        mTvPublished = findViewById(R.id.tv_published)
        recyclerView = findViewById<View>(R.id.rl_goods2) as RecyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        val adapter = GoodsAdapter2(mGoodsList)
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
                    .getGoodsFromPhone(LoginStateUtil.getInstance(applicationContext).localPhoneNumberOrNull) as List<Goods>
        }
        if (mGoodList==null){
            T.showShort(applicationContext,"你还没有发布商品")
            return
        }
        for (i in mGoodList?.indices!!){
            val goods= mGoodList!!.get(i)
            val goods1=Goods(goods.gid,goods.title,R.drawable.icon,goods.price)
            mGoodsList.add(goods1)
        }
    }

    override fun finish() {
        super.finish()
    }
}