package com.example.shop.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.shop.R
import com.example.shop.util.RxClickUtil
import java.util.concurrent.TimeUnit

class RegisterFragment : Fragment() {

    lateinit var iv_back:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", "RegisterFragment have created")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val  view=inflater.inflate(R.layout.fragment_register, container, false)

        iv_back=view.findViewById(R.id.iv_back)
        RxClickUtil.clickEvent(iv_back)
                .throttleFirst(500,TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.d("TAG", "Register back")
                   Navigation.findNavController(iv_back).navigate(R.id.action_registerFragment_to_loginFragment)
                }
        return view
    }
}