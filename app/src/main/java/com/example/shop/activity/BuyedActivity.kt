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
import com.example.shop.adapter.BuyerAdapter
import com.example.shop.adapter.GoodsAdapter2
import com.example.shop.entity.Orders
import com.example.shop.util.LoginStateUtil
import com.example.shop.util.T
import kotlinx.coroutines.runBlocking
import java.util.ArrayList

class BuyedActivity : AppCompatActivity() {
    lateinit var mIvBack: ImageView
    private var mTvBuyed: TextView? = null
    private var recyclerView: RecyclerView? = null
    private val mOrdersList: MutableList<Orders> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyed)
        initGoods()
        mTvBuyed = findViewById(R.id.tv_buyed)
        recyclerView = findViewById<View>(R.id.rl_goods2) as RecyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        val adapter = BuyerAdapter(mOrdersList)
        recyclerView!!.adapter = adapter
        mIvBack = findViewById(R.id.iv_back)
        mIvBack.setOnClickListener(View.OnClickListener { finish() })
    }

    private fun initGoods() {
        var mOrdersList2: List<Orders>? =null
        runBlocking {
            val bid=MyApplication.instance.userDao.getUserId(LoginStateUtil.getInstance(applicationContext).localPhoneNumberOrNull)
            mOrdersList2= bid?.let { MyApplication.instance.ordersDao.getOrdersFromBid(it) }
        }
        if (mOrdersList2==null){
            T.showShort(applicationContext,"你还没有购买过商品")
            return
        }
        for (i in mOrdersList2?.indices!!){
            val orders= mOrdersList2!!.get(i)
            mOrdersList.add(orders)
        }
    }

    override fun finish() {
        super.finish()
    }
}