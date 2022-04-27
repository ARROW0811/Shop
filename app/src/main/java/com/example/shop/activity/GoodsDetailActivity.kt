package com.example.shop.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.shop.R
import com.example.shop.util.RxClickUtil
import com.example.shop.util.T
import java.util.concurrent.TimeUnit

class GoodsDetailActivity : AppCompatActivity() {
    lateinit var mIvBack: ImageView
    lateinit var mIvCollect: ImageView
    lateinit var mBtBuy: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)
        mIvBack = findViewById(R.id.iv_back)
        mIvCollect=findViewById(R.id.iv_collect)
        mBtBuy=findViewById(R.id.bt_buy)
        mIvBack.setOnClickListener(View.OnClickListener { finish() })
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