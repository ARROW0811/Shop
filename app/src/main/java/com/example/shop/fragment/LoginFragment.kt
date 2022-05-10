package com.example.shop.fragment

import android.content.Intent
import android.widget.TextView
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.shop.R
import androidx.navigation.Navigation
import com.example.shop.MyApplication
import com.example.shop.activity.HomeActivity
import com.example.shop.entity.User
import com.example.shop.util.L
import com.example.shop.util.L.d
import com.example.shop.util.LoginStateUtil
import com.example.shop.util.RxClickUtil
import com.example.shop.util.T
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class LoginFragment : Fragment() {
    lateinit var mTxtFindpsw:TextView
    lateinit var mTxtRegister: TextView
    lateinit var mBtnLogin: Button
    lateinit var mEtPhoneNumber:EditText
    lateinit var mEtPassWord:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        mTxtFindpsw = view.findViewById(R.id.tv_findpsw)
        mTxtRegister = view.findViewById(R.id.tv_register)
        mBtnLogin = view.findViewById(R.id.bt_login)
        mEtPhoneNumber=view.findViewById(R.id.et_phoneNumber)
        mEtPassWord=view.findViewById(R.id.et_password)
        RxClickUtil.clickEvent(mTxtFindpsw)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe { it->
                    Navigation.findNavController(mTxtFindpsw).navigate(R.id.action_loginFragment_to_findPwdFragment)
                }

        RxClickUtil.clickEvent(mTxtRegister)
                .throttleFirst(500,TimeUnit.MILLISECONDS)
                .subscribe{
                    Navigation.findNavController(mTxtRegister).navigate(R.id.action_loginFragment_to_registerFragment)
                }
        RxClickUtil.clickEvent(mBtnLogin)
                .throttleFirst(500,TimeUnit.MILLISECONDS)
                .subscribe{
                    var password=mEtPassWord.text.toString()
                    var phoneNumber=mEtPhoneNumber.text.toString()
                    var temp=false
                    var userName:String?
                    runBlocking {
                        temp= (password.equals(MyApplication.instance.userDao.getPassword(phoneNumber)))
                        userName=MyApplication.instance.userDao.getName(phoneNumber)
                    }
                        if(temp){
                            var intent=Intent(activity,HomeActivity::class.java)
                            startActivity(intent)
                            LoginStateUtil.getInstance(context).savePhoneNumberToLocal(phoneNumber)
                            LoginStateUtil.getInstance(context).saveUserNameToLocal(userName)
                            T.showShort(context,"登陆成功")
                            L.d("电话号码是：${LoginStateUtil.getInstance(context).localPhoneNumberOrNull}")
                        }else{
                            T.showShort(context,"密码错误")
                        }

                }

        return view
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAg", "resume")
    }
}