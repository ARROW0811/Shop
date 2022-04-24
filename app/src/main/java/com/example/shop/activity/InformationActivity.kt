package com.example.shop.activity

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import com.example.shop.R
import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.example.shop.MyApplication
import com.example.shop.entity.User
import com.example.shop.util.LoginStateUtil

class InformationActivity : AppCompatActivity(), View.OnClickListener {
    private var mDlgUserName: AlertDialog? = null
    lateinit var mTvUserName: TextView
    lateinit var mIvUserName: ImageView
    private var mDlgSchool: AlertDialog? = null
    private var mTvSchool: TextView? = null
    lateinit var mIvSchool: ImageView
    private var mDlgStudentNumber: AlertDialog? = null
    private var mTvStudentNumber: TextView? = null
    lateinit var mIvStudentNumber: ImageView
    private var mDlgPhoneNumber: AlertDialog? = null
    private var mTvPhoneNumber: TextView? = null
    lateinit var mIvPhoneNumber: ImageView
    private var mDlgPassword: AlertDialog? = null
    private var mTvPassword: TextView? = null
    lateinit var mIvPassword: ImageView
    lateinit var mIvBack: ImageView
    lateinit var user:User
    var handler:Handler =object:Handler(Looper.myLooper()!!){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg?.what){
                0->{
                    user=msg.obj as User
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        Thread{
            var user=MyApplication.instance.userDao
                    .getUser(LoginStateUtil.getInstance(this).localPhoneNumberOrNull)
            var message=Message.obtain()
            message.what=0
            message.obj=user
            handler.sendMessage(message)
        }
        mTvUserName = findViewById(R.id.tv_my_username)
        mTvUserName.text= user.name
        mIvUserName  = findViewById(R.id.iv_username)
        mTvSchool = findViewById(R.id.tv_my_school)
        mIvSchool = findViewById(R.id.iv_school)
        mTvStudentNumber = findViewById(R.id.tv_my_studentNumber)
        mIvStudentNumber = findViewById(R.id.iv_studentNumber)
        mTvPhoneNumber = findViewById(R.id.tv_my_phoneNumber)
        mIvPhoneNumber = findViewById(R.id.iv_phoneNumber)
        mTvPassword = findViewById(R.id.tv_my_password)
        mIvPassword = findViewById(R.id.iv_password)
        mIvBack = findViewById(R.id.iv_back)
        mIvUserName.setOnClickListener(this)
        mIvSchool.setOnClickListener(this)
        mIvStudentNumber.setOnClickListener(this)
        mIvPhoneNumber.setOnClickListener(this)
        mIvPassword.setOnClickListener(this)
        mIvBack.setOnClickListener(this)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(view: View) {
        when (view.id) {
            R.id.iv_username -> {
                val mDlgUserNameView = LayoutInflater.from(this@InformationActivity)
                        .inflate(R.layout.dialog_edit, null)
                val mEtName = mDlgUserNameView.findViewById<EditText>(R.id.et_name)
                val mBtOk = mDlgUserNameView.findViewById<Button>(R.id.bt_ok)
                val mBtCancel = mDlgUserNameView.findViewById<Button>(R.id.bt_cancel)
                mBtCancel.setOnClickListener { mDlgUserName!!.dismiss() }
                mBtOk.setOnClickListener {
                    mTvUserName!!.text = mEtName.text.toString()
                    mDlgUserName!!.dismiss()
                }
                mDlgUserName = AlertDialog.Builder(this@InformationActivity)
                        .setView(mDlgUserNameView)
                        .create()
                mDlgUserName!!.show()
            }
            R.id.iv_school -> {
                val mDlgSchoolView = LayoutInflater.from(this@InformationActivity)
                        .inflate(R.layout.dialog_edit, null)
                val mEtName_shchool = mDlgSchoolView.findViewById<EditText>(R.id.et_name)
                val mBtOk_shool = mDlgSchoolView.findViewById<Button>(R.id.bt_ok)
                val mBtCancel_school = mDlgSchoolView.findViewById<Button>(R.id.bt_cancel)
                mBtCancel_school.setOnClickListener { mDlgSchool!!.dismiss() }
                mBtOk_shool.setOnClickListener {
                    mTvSchool!!.text = mEtName_shchool.text.toString()
                    mDlgSchool!!.dismiss()
                }
                mDlgSchool = AlertDialog.Builder(this@InformationActivity)
                        .setView(mDlgSchoolView)
                        .create()
                mDlgSchool!!.show()
            }
            R.id.iv_studentNumber -> {
                val mDlgStudentNumberView = LayoutInflater.from(this@InformationActivity)
                        .inflate(R.layout.dialog_edit, null)
                val mEtName_studentName = mDlgStudentNumberView.findViewById<EditText>(R.id.et_name)
                val mBtOk_studentName = mDlgStudentNumberView.findViewById<Button>(R.id.bt_ok)
                val mBtCancel_studentName = mDlgStudentNumberView.findViewById<Button>(R.id.bt_cancel)
                mBtCancel_studentName.setOnClickListener { mDlgStudentNumber!!.dismiss() }
                mBtOk_studentName.setOnClickListener {
                    mTvStudentNumber!!.text = mEtName_studentName.text.toString()
                    mDlgStudentNumber!!.dismiss()
                }
                mDlgStudentNumber = AlertDialog.Builder(this@InformationActivity)
                        .setView(mDlgStudentNumberView)
                        .create()
                mDlgStudentNumber!!.show()
            }
            R.id.iv_phoneNumber -> {
                val mDlgPhoneNumberView = LayoutInflater.from(this@InformationActivity)
                        .inflate(R.layout.dialog_edit, null)
                val mEtName_phoneNumber = mDlgPhoneNumberView.findViewById<EditText>(R.id.et_name)
                val mBtOk_phoneNumber = mDlgPhoneNumberView.findViewById<Button>(R.id.bt_ok)
                val mBtCancel_phoneNumber = mDlgPhoneNumberView.findViewById<Button>(R.id.bt_cancel)
                mBtCancel_phoneNumber.setOnClickListener { mDlgPhoneNumber!!.dismiss() }
                mBtOk_phoneNumber.setOnClickListener {
                    mTvPhoneNumber!!.text = mEtName_phoneNumber.text.toString()
                    mDlgPhoneNumber!!.dismiss()
                }
                mDlgPhoneNumber = AlertDialog.Builder(this@InformationActivity)
                        .setView(mDlgPhoneNumberView)
                        .create()
                mDlgPhoneNumber!!.show()
            }
            R.id.iv_password -> {
                val mDlgPasswordView = LayoutInflater.from(this@InformationActivity)
                        .inflate(R.layout.dialog_edit, null)
                val mEtName_password = mDlgPasswordView.findViewById<EditText>(R.id.et_name)
                val mBtOk_password = mDlgPasswordView.findViewById<Button>(R.id.bt_ok)
                val mBtCancel_password = mDlgPasswordView.findViewById<Button>(R.id.bt_cancel)
                mBtCancel_password.setOnClickListener { mDlgPassword!!.dismiss() }
                mBtOk_password.setOnClickListener {
                    mTvPassword!!.text = mEtName_password.text.toString()
                    mDlgPassword!!.dismiss()
                }
                mDlgPassword = AlertDialog.Builder(this@InformationActivity)
                        .setView(mDlgPasswordView)
                        .create()
                mDlgPassword!!.show()
            }
            R.id.iv_back -> mIvBack!!.setOnClickListener { finish() }
        }
    }

    override fun finish() {
        super.finish()
    }
}