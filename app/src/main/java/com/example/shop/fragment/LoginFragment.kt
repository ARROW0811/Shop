package com.example.shop.fragment

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

class LoginFragment : Fragment() {
    private var txt_findpsw: TextView? = null
    private var txt_register: TextView? = null
    private var btn_login: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        txt_findpsw = view.findViewById(R.id.tv_findpsw)
        txt_register = view.findViewById(R.id.tv_register)
        btn_login = view.findViewById(R.id.bt_login)
        txt_findpsw.setOnClickListener(View.OnClickListener { view -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_findPwdFragment) })
        txt_register.setOnClickListener(View.OnClickListener { view -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment) })
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