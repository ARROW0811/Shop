package com.example.shop.fragment

import android.content.Intent
import android.widget.TextView
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.shop.R
import androidx.navigation.Navigation
import com.example.shop.activity.HomeActivity
import com.example.shop.util.RxClickUtil
import java.util.concurrent.TimeUnit

class LoginFragment : Fragment() {
    lateinit var mTxtFindpsw:TextView
    lateinit var mTxtRegister: TextView
    lateinit var mBtnLogin: Button
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
                    var intent=Intent(activity,HomeActivity::class.java)
                            startActivity(intent)
                }

        /*
        txt_register.setOnClickListener((View.OnClickListener) this);
        txt_findpsw.setOnClickListener((View.OnClickListener) this);
        switch (view.getId()){
            case R.id.tv_findpsw:
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_findPwdFragment);
            case R.id.tv_register:
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment);
        }
         */return view
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAg", "resume")
    }
}