package com.example.shop.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.chockqiu.view.PriceView
import com.example.shop.MyApplication
import com.example.shop.R
import com.example.shop.entity.Goods
import com.example.shop.util.RxClickUtil
import com.example.shop.util.T
import kotlinx.coroutines.runBlocking
import org.w3c.dom.Text
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

class GoodsDetailActivity : AppCompatActivity() {
    lateinit var mIvBack: ImageView
    lateinit var mIvCollect: ImageView
    lateinit var mBtBuy: Button
    lateinit var mTvTitle:TextView
    lateinit var mTvPrice:TextView
    lateinit var mTvPrimePrice:TextView
    lateinit var mTvDescribe:TextView
    lateinit var mTvPhoneNumber:TextView
    lateinit var mTvDelivery:TextView
    lateinit var mTvLocation:TextView
    lateinit var goods:Goods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)
        mIvBack = findViewById(R.id.iv_back)
        mIvCollect=findViewById(R.id.iv_collect)
        mBtBuy=findViewById(R.id.bt_buy)
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
        }
        mTvTitle.text=goods.title
        mTvPrice.text=goods.price
        mTvPrimePrice.text=goods.primePrice
        mTvDescribe.text=goods.describe
        mTvPhoneNumber.text=goods.phoneNumber
        mTvDelivery.text=goods.deliver
        mTvLocation.text=goods.location
        mIvBack.setOnClickListener(View.OnClickListener { finish() })
        mIvCollect?.setTag(R.drawable.like_normal)

        RxClickUtil.clickEvent(mIvCollect)
                .throttleFirst(5,TimeUnit.MILLISECONDS)
                .subscribe {

                    if (mIvCollect.getTag()==R.drawable.like_normal){
                        mIvCollect.setImageResource(R.drawable.like_pressed)
                        mIvCollect.setTag(R.drawable.like_pressed)
                        T.showShort(this,"收藏成功")
                    }else{
                        mIvCollect?.setImageResource(R.drawable.like_normal)
                        mIvCollect?.setTag(R.drawable.like_normal)
                        T.showShort(this,"取消收藏")
                    }


                }


    }
}