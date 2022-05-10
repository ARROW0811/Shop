package com.example.shop.activity

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import com.example.shop.R
import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import com.example.shop.MyApplication
import com.example.shop.entity.Goods
import com.example.shop.util.RxClickUtil
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

class GoodsDetailActivity2 : AppCompatActivity() {
    lateinit var mIvBack: ImageView
    lateinit var mTvTitle: TextView
    lateinit var mTvPrice: TextView
    lateinit var mTvPrimePrice: TextView
    lateinit var mTvDescribe: TextView
    lateinit var mTvPhoneNumber: TextView
    lateinit var mTvDelivery: TextView
    lateinit var mTvLocation: TextView
    lateinit var mIvGoods:ImageView
    lateinit var goods:Goods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail2)
        mIvBack = findViewById(R.id.iv_back)
        mTvTitle = findViewById(R.id.tv_goodsTitle)
        mTvPrice = findViewById(R.id.tv_goodsPrice)
        mTvPrimePrice = findViewById(R.id.tv_goodsPrimePrice)
        mTvDescribe = findViewById(R.id.tv_details_content)
        mTvPhoneNumber = findViewById(R.id.tv_seller_phone)
        mTvDelivery = findViewById(R.id.tv_delivery_content)
        mTvLocation = findViewById(R.id.tv_location_content)
        mIvGoods=findViewById(R.id.iv_goods)
        runBlocking {
            val gid=intent.getIntExtra("gid",1)
            goods = MyApplication.instance.goodsDao.getGoods(gid)
        }
        mIvGoods.setImageURI(Uri.parse(goods.image))
        mTvTitle.setText(goods.title)
        mTvPrice.setText(goods.price)
        mTvPrimePrice.setText(goods.primePrice)
        mTvDescribe.setText(goods.describe)
        mTvPhoneNumber.setText(goods.phoneNumber)
        mTvDelivery.setText(goods.deliver)
        mTvLocation.setText(goods.location)
        RxClickUtil.clickEvent(mIvBack)
                .throttleFirst(500,TimeUnit.MILLISECONDS)
                .subscribe {
                    finish()
                }


    }
}