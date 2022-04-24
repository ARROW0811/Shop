package com.example.shop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shop.R
import android.content.Intent
import android.net.Uri
import com.example.shop.activity.PublishedActivity
import com.example.shop.activity.SoldActivity
import com.example.shop.activity.BuyedActivity
import com.example.shop.activity.CollectedActivity
import com.example.shop.activity.InformationActivity
import com.example.shop.util.L
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.example.shop.minePack.presenter.MinePresenter
import com.example.shop.util.BlurTransformation
import com.example.shop.util.T

class MineFragment : Fragment() {
    lateinit var circleImageHead: ImageView
    lateinit var mIvIcon: ImageView
    lateinit var mIvPublished: ImageView
    lateinit var mTvPublished: TextView
    lateinit var mIvSold: ImageView
    lateinit var mTvSold: TextView
    lateinit var mIvBuyed: ImageView
    lateinit var mTvBuyed: TextView
    lateinit var mIvCollection: ImageView
    lateinit var mTvCollection: TextView
    lateinit var mIvInformation: ImageView
    lateinit var mTvInformation: TextView
    lateinit var mBtnQuit: Button
    private var popupWindow: PopupWindow?=null
    lateinit var imageUri: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mine, container, false)
        mIvIcon = view.findViewById(R.id.iv_icon_bg)
        mIvPublished = view.findViewById(R.id.iv_published)
        mTvPublished = view.findViewById(R.id.tv_published)
        mIvSold = view.findViewById(R.id.iv_sold)
        mTvSold = view.findViewById(R.id.tv_sold)
        mIvBuyed = view.findViewById(R.id.iv_buyed)
        mTvBuyed = view.findViewById(R.id.tv_buyed)
        mIvCollection = view.findViewById(R.id.iv_collection)
        mTvCollection = view.findViewById(R.id.tv_collection)
        mIvInformation = view.findViewById(R.id.iv_information)
        mTvInformation = view.findViewById(R.id.tv_information)
        mBtnQuit = view.findViewById(R.id.bt_quit)
        mIvPublished.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, PublishedActivity::class.java)
            startActivity(intent)
        })
        mTvPublished.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, PublishedActivity::class.java)
            startActivity(intent)
        })
        mIvSold.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, SoldActivity::class.java)
            startActivity(intent)
        })
        mTvSold.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, SoldActivity::class.java)
            startActivity(intent)
        })
        mIvBuyed.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, BuyedActivity::class.java)
            startActivity(intent)
        })
        mTvBuyed.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, BuyedActivity::class.java)
            startActivity(intent)
        })
        mIvCollection.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, CollectedActivity::class.java)
            startActivity(intent)
        })
        mTvCollection.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, CollectedActivity::class.java)
            startActivity(intent)
        })
        mIvInformation.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, InformationActivity::class.java)
            startActivity(intent)
        })
        mTvInformation.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, InformationActivity::class.java)
            startActivity(intent)
        })
        mBtnQuit.setOnClickListener(View.OnClickListener {
            requireActivity().finish()
            L.d("退出登录")
            T.showShort(context, "退出登录")
        })
        mIvIcon.setOnClickListener(View.OnClickListener { show_popup_windows() })
        Glide.with(this).load(R.drawable.icon)
                .apply(RequestOptions.bitmapTransform(BlurTransformation(requireContext(), 75, 5)))
                .into(mIvIcon)
        //参数用来设置模糊程度
        return view
    }

    //展示修改头像的选择框，并设置选择框的监听器
    private fun show_popup_windows() {
        val layout_photo_selected = layoutInflater.inflate(R.layout.photo_select, null) as RelativeLayout
        if (popupWindow == null) {
            popupWindow=PopupWindow()
            popupWindow = PopupWindow(layout_photo_selected, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true)
        }
        //设置动画
        popupWindow!!.animationStyle = R.style.popwindow_anim
        //显示popupwindows
        popupWindow!!.showAtLocation(layout_photo_selected, Gravity.CENTER, 0, 0)
        //设置监听器
        val mTvTakePhoto = layout_photo_selected.findViewById<View>(R.id.tv_take_photo) as TextView
        val mTvFromAlbums = layout_photo_selected.findViewById<View>(R.id.tv_from_albums) as TextView
        val mLlCancel = layout_photo_selected.findViewById<View>(R.id.ll_cancel) as LinearLayout
        //拍照按钮监听
        mTvTakePhoto.setOnClickListener {
            if (popupWindow != null && popupWindow!!.isShowing) {
                popupWindow!!.dismiss()
            }
        }
        //相册按钮监听
        mTvFromAlbums.setOnClickListener { //去除选择框
            MinePresenter(this).requestPermissionAndTryOpen();
            popupWindow!!.dismiss()
        }
        //取消按钮监听
        mLlCancel.setOnClickListener {
            if (popupWindow != null && popupWindow!!.isShowing) {
                popupWindow!!.dismiss()
            }
        }
    }

    companion object {
        const val TAKE_PHOTO = 1
        const val FROM_ALBUMS = 2
    }
}