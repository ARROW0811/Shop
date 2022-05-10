package com.example.shop.activity

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.room.Index
import com.chockqiu.view.PriceView
import com.example.shop.MyApplication
import com.example.shop.R
import com.example.shop.entity.Collect
import com.example.shop.entity.Goods
import com.example.shop.entity.Orders
import com.example.shop.util.L
import com.example.shop.util.LoginStateUtil
import com.example.shop.util.RxClickUtil
import com.example.shop.util.T
import kotlinx.coroutines.runBlocking
import org.w3c.dom.Text
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

class GoodsDetailActivity : AppCompatActivity() {
    lateinit var mIvBack: ImageView
    lateinit var mIvCollect: ImageView
    lateinit var mBtBuy: Button
    lateinit var mIvGoods:ImageView
    lateinit var mTvTitle:TextView
    lateinit var mTvPrice:TextView
    lateinit var mTvPrimePrice:TextView
    lateinit var mTvDescribe:TextView
    lateinit var mTvPhoneNumber:TextView
    lateinit var mTvDelivery:TextView
    lateinit var mTvLocation:TextView
    lateinit var goods:Goods
    var isCollect:Boolean=false
    var uid:Int=0
    val phoneNumber: String =LoginStateUtil.getInstance(this).localPhoneNumberOrNull

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)
        mIvBack = findViewById(R.id.iv_back)
        mIvCollect=findViewById(R.id.iv_collect)
        mBtBuy=findViewById(R.id.bt_buy)
        mIvGoods=findViewById(R.id.iv_goods)
        mTvTitle=findViewById(R.id.tv_goodsTitle)
        mTvPrice=findViewById(R.id.tv_goodsPrice)
        mTvPrimePrice=findViewById(R.id.tv_goodsPrimePrice)
        mTvDescribe=findViewById(R.id.tv_details_content)
        mTvPhoneNumber=findViewById(R.id.tv_seller_phone)
        mTvDelivery=findViewById(R.id.tv_delivery_content)
        mTvLocation=findViewById(R.id.tv_location_content)

        val gid=intent.getIntExtra("gid",1)
        runBlocking {
            goods=MyApplication.instance.goodsDao.getGoods(gid)
            uid= MyApplication.instance.userDao.getUserId(phoneNumber)!!
        }
        mIvGoods.setImageURI(Uri.parse(goods.image))
        mTvTitle.text=goods.title
        mTvPrice.text=goods.price
        mTvPrimePrice.text=goods.primePrice
        mTvDescribe.text=goods.describe
        mTvPhoneNumber.text=goods.phoneNumber
        mTvDelivery.text=goods.deliver
        mTvLocation.text=goods.location
        mIvBack.setOnClickListener(View.OnClickListener { finish() })
        var id: Int?
        runBlocking {
            id=MyApplication.instance.collectDao.getId(phoneNumber,gid) 
        }

        if(id!=null){
            mIvCollect?.setImageResource(R.drawable.like_pressed)
        }else{
            mIvCollect?.setImageResource(R.drawable.like_normal)
        }
        RxClickUtil.clickEvent(mIvCollect)
                .throttleFirst(5,TimeUnit.MILLISECONDS)
                .subscribe {
                    changeCollectState(goods)
                }

        RxClickUtil.clickEvent(mBtBuy)
                .throttleFirst(500,TimeUnit.MILLISECONDS)
                .subscribe {
                    if(goods.state==1) {
                        val current = LocalDateTime.now()
                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                        val time = current.format(formatter)
                        val orders: Orders
                        runBlocking {
                            val bid = MyApplication.instance.userDao.getUserId(phoneNumber)
                            MyApplication.instance.goodsDao.updateState(2, goods.gid)
                            orders = bid?.let { it1 -> Orders(goods.gid, it1, time) }!!
                            MyApplication.instance.ordersDao.addOrder(orders)
                        }
                        T.showShort(this, "购买成功")
                    }else{
                        T.showShort(this,"商品已经下架或已被购买")
                    }
                }
    }
    fun changeCollectState(goods: Goods){
        if(isCollect){
            mIvCollect?.setImageResource(R.drawable.like_normal)
            runBlocking {
                val collect: Collect =Collect(goods.gid,LoginStateUtil.getInstance(applicationContext).localPhoneNumberOrNull)
                MyApplication.instance.collectDao.deleteCollect(phoneNumber,goods.gid)
            }
            T.showShort(this,"取消收藏")
            isCollect=false
        }else{
            mIvCollect.setImageResource(R.drawable.like_pressed)
            runBlocking {
                val collect: Collect =Collect(goods.gid,phoneNumber)
                MyApplication.instance.collectDao.addCollect(collect)
            }
            T.showShort(this,"收藏成功")
            isCollect=true
        }
    }
}