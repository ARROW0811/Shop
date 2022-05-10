package com.example.shop.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.shop.MyApplication
import com.example.shop.R
import com.example.shop.util.RxClickUtil
import com.example.shop.util.T
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.random.Random

class FindPwdFragment : Fragment() {
    lateinit var mIvBack: ImageView
    lateinit var mBtGetCode: Button
    lateinit var mBtComfirm: Button
    lateinit var mEtPhoneNumber: EditText
    lateinit var mEtCode: EditText
    var tempCode: String? =null
    var password: String? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", "FindPwdFragment have created")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_find_pwd, container, false)
        mIvBack = view.findViewById(R.id.iv_back)
        mBtGetCode = view.findViewById(R.id.bt_getCode)
        mBtComfirm = view.findViewById(R.id.bt_confirm)
        mEtPhoneNumber = view.findViewById(R.id.et_phoneNumber)
        mEtCode = view.findViewById(R.id.et_code)
        RxClickUtil.clickEvent(mIvBack)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.d("TAG", "FindPwdFragment back")
                    Navigation.findNavController(mIvBack).navigate(R.id.action_findPwdFragment_to_loginFragment)
                }
        RxClickUtil.clickEvent(mBtGetCode)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    showNormalDialog()
                }
        RxClickUtil.clickEvent(mBtComfirm)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {

                    val phoneNumber = mEtPhoneNumber.text.toString()
                    val code = mEtCode.text.toString()
                    runBlocking {
                        password = MyApplication.instance.userDao.getPassword(phoneNumber)
                    }
                    if (TextUtils.equals(code,tempCode)) {
                        password?.let { showPasswordDialog(it) }
                    } else {
                        T.showShort(context, "验证码错误或手机号错误")
                    }


                }
        return view
    }

    private fun showNormalDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("获取验证码")
        val code = Random.nextInt(1000, 9999)//验证码
        tempCode = code.toString()
        builder.setMessage("验证码为：${code}")

        val dialogClickListener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    Log.e("TAG", "确认")
                }
                /*
                DialogInterface.BUTTON_NEGATIVE -> {
                    Log.e("TAG", "复制")
                }
                 */
            }
        }
        builder.setPositiveButton("确认", dialogClickListener)
        //builder.setNegativeButton("复制", dialogClickListener)
        builder.create().show();
    }

    private fun showPasswordDialog(password: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("找回密码")
        builder.setMessage("您的密码为：${password}")
        val dialogClickListener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    Log.e("TAG", "确认")
                }

            }
        }
        builder.setPositiveButton("确认", dialogClickListener)
        builder.create().show();
    }


}