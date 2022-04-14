package com.example.shop.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.shop.R
import com.example.shop.activity.MainActivity
import com.example.shop.util.RxClickUtil
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class FindPwdFragment : Fragment() {
    lateinit var mIvBack:ImageView
    lateinit var btn_getCode:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", "FindPwdFragment have created")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_find_pwd, container, false)
        mIvBack=view.findViewById(R.id.iv_back)
        btn_getCode=view.findViewById(R.id.bt_getCode)
        RxClickUtil.clickEvent(mIvBack)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.d("TAG", "FindPwdFragment back")
                   Navigation.findNavController(mIvBack).navigate(R.id.action_findPwdFragment_to_loginFragment)
                }
        RxClickUtil.clickEvent(btn_getCode)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    showNormalDialog()
                }
        return view
    }

    private fun showNormalDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("获取验证码")
        val code=Random.nextInt(1000,9999)//验证码
        builder.setMessage("验证码为：${code}")

        val dialogClickListener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    Log.e("TAG", "确认")
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                    Log.e("TAG", "复制")

                }
            }
        }
        builder.setPositiveButton("确认", dialogClickListener)
        builder.setNegativeButton("复制", dialogClickListener)
        builder.create().show();

    }


}