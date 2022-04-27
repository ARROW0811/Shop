package com.example.shop.fragment

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.room.Room
import com.example.shop.MyApplication
import com.example.shop.R
import com.example.shop.dao.UserDao
import com.example.shop.entity.User
import com.example.shop.util.RxClickUtil
import com.example.shop.util.T
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class RegisterFragment : Fragment() {
    lateinit var mIvBack: ImageView
    lateinit var mBtRegister: Button
    lateinit var mEtUsername: EditText
    lateinit var mEtSchool: EditText
    lateinit var mEtStudentNumber: EditText
    lateinit var mEtPhoneNumber: EditText
    lateinit var mEtPassWord: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", "RegisterFragment have created")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        mIvBack = view.findViewById(R.id.iv_back)
        mBtRegister = view.findViewById(R.id.bt_register)
        mEtUsername = view.findViewById(R.id.et_username)
        mEtSchool = view.findViewById(R.id.et_school)
        mEtStudentNumber = view.findViewById(R.id.et_studentNumber)
        mEtPhoneNumber = view.findViewById(R.id.et_phoneNumber)
        mEtPassWord = view.findViewById(R.id.et_password)
        RxClickUtil.clickEvent(mIvBack)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.d("TAG", "Register back")
                    Navigation.findNavController(mIvBack).navigate(R.id.action_registerFragment_to_loginFragment)
                }
        RxClickUtil.clickEvent(mBtRegister)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    var name = mEtUsername.text.toString()
                    var school = mEtSchool.text.toString()
                    var studentNumber = mEtStudentNumber.text.toString()
                    var phoneNumber = mEtPhoneNumber.text.toString()
                    var password = mEtPassWord.text.toString()
                    var user = User(name, school, studentNumber, phoneNumber, password)
                    var temp=null
                    runBlocking {
                        temp= MyApplication.instance.userDao.getName(phoneNumber) as Nothing?
                    }
                        if (( temp== null)) {
                            runBlocking {
                                MyApplication.instance.userDao.insertUser(user)
                            }
                            T.showShort(context, "注册成功")
                            Navigation.findNavController(mBtRegister).navigate(R.id.action_registerFragment_to_loginFragment)
                        } else {
                            T.showShort(context, "此手机号已被注册")
                        }
                }
        return view
    }

}
