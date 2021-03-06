package com.example.shop.fragment

import com.example.shop.entity.Goods
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.shop.R
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.shop.MyApplication
import com.example.shop.adapter.GoodsAdapter
import com.example.shop.util.T
import kotlinx.coroutines.runBlocking
import java.util.ArrayList

class MainFragment : Fragment() {
    var mGoodsList=mutableListOf<Goods>()
    lateinit var mSearchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        initGoodsBean()
        val recyclerView = view.findViewById<View>(R.id.rl_goods) as RecyclerView
        mSearchView=view.findViewById(R.id.sv_search)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        var goodsAdapter = GoodsAdapter(mGoodsList)
        recyclerView.adapter = goodsAdapter
        mSearchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                var mGoodList: List<Goods>? =null
                runBlocking {
                    mGoodList= newText?.let { MyApplication.instance.goodsDao.getGoodsFromText(it) } as List<Goods>
                }
                if (mGoodList==null){
                    T.showShort(context,"没有这样的商品")
                    return true
                }
                mGoodsList.clear()
                for (i in mGoodList?.indices!!){
                    val goods= mGoodList!!.get(i)
                    val goods1=Goods(goods.gid,goods.title,goods.image,goods.price)
                    mGoodsList.add(goods1)
                }
                val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                recyclerView.layoutManager = layoutManager
                goodsAdapter = GoodsAdapter(mGoodsList)
                recyclerView.adapter = goodsAdapter
                return true
            }

        })


        return view
    }

    private fun initGoodsBean() {
        var mGoodList: List<Goods>? =null
            runBlocking {
                mGoodList= MyApplication.instance.goodsDao.getAllUpGoods() as List<Goods>
            }
        if (mGoodList==null){
            T.showShort(context,"当前没有商品")
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