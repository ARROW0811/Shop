package com.example.shop.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.shop.R
import androidx.fragment.app.Fragment
import com.example.shop.MyApplication
import com.example.shop.entity.Goods
import com.example.shop.minePack.presenter.PublishPresenter
import com.example.shop.util.L
import com.example.shop.util.LoginStateUtil
import com.example.shop.util.RxClickUtil
import com.example.shop.util.T
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

class PublishFragment : Fragment() {
    lateinit var mSpinner: Spinner
    lateinit var mEtTitle:EditText
    lateinit var mEtPrice:EditText
    lateinit var mEtPrimePrice:EditText
    lateinit var mIvGoodsImg:ImageView
    lateinit var mEtDescribe:EditText
    lateinit var radioGroup: RadioGroup
    lateinit var mRbOther:RadioButton
    lateinit var mRbMe:RadioButton
    lateinit var publishPresenter:PublishPresenter
    lateinit var delivery:String
    lateinit var mEtLocation:EditText
    lateinit var goodsSort:Array<String>
    lateinit var sort:String
    lateinit var mBtPublish:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_publish, container, false)
        mSpinner = view.findViewById<View>(R.id.sp_goodsSort) as Spinner
        mIvGoodsImg=view.findViewById(R.id.iv_goodsImg)
        publishPresenter= PublishPresenter(this)
        radioGroup=view.findViewById(R.id.rg_delivery)
        mRbOther=view.findViewById(R.id.rb_delivery_other)
        mRbMe=view.findViewById(R.id.rb_delivery_me)
        mEtTitle=view.findViewById(R.id.et_goodsTitle)
        mEtPrice=view.findViewById(R.id.et_goodsPrice)
        mEtPrimePrice=view.findViewById(R.id.et_goodsOriginalPrice)
        mEtDescribe=view.findViewById(R.id.et_goodsIntro)
        mEtLocation=view.findViewById(R.id.et_location)
        mBtPublish=view.findViewById(R.id.bt_publish)

        // ???????????????
        val mItems = resources.getStringArray(R.array.goodsSort)
        // ??????Adapter?????????????????????
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, mItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //?????? Adapter?????????
        mSpinner.adapter = adapter
        sort="??????"
        mSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                goodsSort = resources.getStringArray(R.array.goodsSort)
                sort=goodsSort[i]
                //Toast.makeText(getContext(), "???????????????:"+goodsSort[i], Toast.LENGTH_LONG).show();
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
        RxClickUtil.clickEvent(mIvGoodsImg)
                .throttleFirst(500,TimeUnit.MILLISECONDS)
                .subscribe {
                    publishPresenter.requestPermissionAndTryOpen()
                }
        delivery="????????????"
        //??????????????????
        radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener{radioGroup, i ->
            if(mRbOther.isChecked){
                delivery=mRbOther.text.toString()
                L.d("delivery:$delivery")
            }else{
                delivery=mRbMe.text.toString()
                L.d("delivery:$delivery")
            }
        })
        //????????????
        RxClickUtil.clickEvent(mBtPublish)
                .throttleFirst(500,TimeUnit.MILLISECONDS)
                .subscribe {
                    var title=mEtTitle.text.toString()
                    var price=mEtPrice.text.toString()
                    var primePrice=mEtPrimePrice.text.toString()
                    var image=publishPresenter.goodsUri.toString()
                    var describe=mEtDescribe.text.toString()
                    var location=mEtLocation.text.toString()
                    var phoneNumber=LoginStateUtil.getInstance(requireContext()).localPhoneNumberOrNull
                    var goods:Goods= Goods(title,image,price,describe,location,sort,primePrice,delivery,phoneNumber,1)
                    runBlocking {
                        MyApplication.instance.goodsDao.insertGoods(goods)
                    }
                    T.showShort(context,"????????????")
                }

        return view

    }
}