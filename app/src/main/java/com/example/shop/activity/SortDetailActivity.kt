package com.example.shop.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.shop.MyApplication
import com.example.shop.R
import com.example.shop.adapter.GoodsAdapter
import com.example.shop.entity.Goods
import com.example.shop.util.T
import kotlinx.coroutines.runBlocking

class SortDetailActivity : AppCompatActivity() {
    var mGoodsList=mutableListOf<Goods>()
    lateinit var mTvSortName:TextView
    lateinit var sortName:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sort_detail)
        sortName= intent.getStringExtra("sortName").toString()
        mTvSortName=findViewById(R.id.tv_sortName)
        mTvSortName.text=sortName
        initGoodsBean()
        val recyclerView = findViewById<RecyclerView>(R.id.rl_goods)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        val goodsAdapter = GoodsAdapter(mGoodsList)
        recyclerView.adapter = goodsAdapter
    }
    private fun initGoodsBean() {
        var mGoodList: List<Goods>? =null
        runBlocking {
            mGoodList= MyApplication.instance.goodsDao.getGoodsFromSort(sortName) as List<Goods>
        }
        if (mGoodList==null){
            T.showShort(this,"当前没有商品")
            return
        }
        for (i in mGoodList?.indices!!){
            val goods= mGoodList!!.get(i)
            val goods1=Goods(goods.gid,goods.title,goods.image,goods.price)
            mGoodsList.add(goods1)
        }

        //val goods1 = Goods("商品asjdnaosihfaonvoaivnapvnpaisciasncoiasnc", R.drawable.icon, "10.0")

    }
}