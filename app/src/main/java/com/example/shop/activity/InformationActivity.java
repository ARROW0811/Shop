package com.example.shop.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.R;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener{
    private AlertDialog mDlgUserName;
    private TextView mTvUserName;
    private ImageView mIvUserName;

    private AlertDialog mDlgSchool;
    private TextView mTvSchool;
    private ImageView mIvSchool;

    private AlertDialog mDlgStudentNumber;
    private TextView mTvStudentNumber;
    private ImageView mIvStudentNumber;

    private AlertDialog mDlgPhoneNumber;
    private TextView mTvPhoneNumber;
    private ImageView mIvPhoneNumber;

    private AlertDialog mDlgPassword;
    private TextView mTvPassword;
    private ImageView mIvPassword;

    private ImageView mIvBack;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        mTvUserName=findViewById(R.id.tv_my_username);
        mIvUserName=findViewById(R.id.iv_username);
        mTvSchool=findViewById(R.id.tv_my_school);
        mIvSchool=findViewById(R.id.iv_school);
        mTvStudentNumber=findViewById(R.id.tv_my_studentNumber);
        mIvStudentNumber=findViewById(R.id.iv_studentNumber);
        mTvPhoneNumber=findViewById(R.id.tv_my_phoneNumber);
        mIvPhoneNumber=findViewById(R.id.iv_phoneNumber);
        mTvPassword=findViewById(R.id.tv_my_password);
        mIvPassword=findViewById(R.id.iv_password);
        mIvBack=findViewById(R.id.iv_back);

        mIvUserName.setOnClickListener(this);
        mIvSchool.setOnClickListener(this);
        mIvStudentNumber.setOnClickListener(this);
        mIvPhoneNumber.setOnClickListener(this);
        mIvPassword.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_username:
                View mDlgUserNameView = LayoutInflater.from(InformationActivity.this)
                        .inflate(R.layout.dialog_edit,null);
                EditText mEtName=mDlgUserNameView.findViewById(R.id.et_name);
                Button mBtOk=mDlgUserNameView.findViewById(R.id.bt_ok);
                Button mBtCancel=mDlgUserNameView.findViewById(R.id.bt_cancel);
                mBtCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDlgUserName.dismiss();
                    }
                });
                mBtOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mTvUserName.setText(mEtName.getText().toString());
                        mDlgUserName.dismiss();
                    }
                });
                mDlgUserName= new AlertDialog.Builder(InformationActivity.this)
                        .setView(mDlgUserNameView)
                        .create();
                mDlgUserName.show();
                break;
            case R.id.iv_school:
                View mDlgSchoolView = LayoutInflater.from(InformationActivity.this)
                        .inflate(R.layout.dialog_edit,null);
                EditText mEtName_shchool=mDlgSchoolView.findViewById(R.id.et_name);
                Button mBtOk_shool=mDlgSchoolView.findViewById(R.id.bt_ok);
                Button mBtCancel_school=mDlgSchoolView.findViewById(R.id.bt_cancel);
                mBtCancel_school.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDlgSchool.dismiss();
                    }
                });
                mBtOk_shool.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mTvSchool.setText(mEtName_shchool.getText().toString());
                        mDlgSchool.dismiss();
                    }
                });
                mDlgSchool= new AlertDialog.Builder(InformationActivity.this)
                        .setView(mDlgSchoolView)
                        .create();
                mDlgSchool.show();
                break;
            case R.id.iv_studentNumber:
                View mDlgStudentNumberView = LayoutInflater.from(InformationActivity.this)
                        .inflate(R.layout.dialog_edit,null);
                EditText mEtName_studentName=mDlgStudentNumberView.findViewById(R.id.et_name);
                Button mBtOk_studentName=mDlgStudentNumberView.findViewById(R.id.bt_ok);
                Button mBtCancel_studentName=mDlgStudentNumberView.findViewById(R.id.bt_cancel);
                mBtCancel_studentName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDlgStudentNumber.dismiss();
                    }
                });
                mBtOk_studentName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mTvStudentNumber.setText(mEtName_studentName.getText().toString());
                        mDlgStudentNumber.dismiss();
                    }
                });
                mDlgStudentNumber= new AlertDialog.Builder(InformationActivity.this)
                        .setView(mDlgStudentNumberView)
                        .create();
                mDlgStudentNumber.show();
                break;
            case R.id.iv_phoneNumber:
                View mDlgPhoneNumberView = LayoutInflater.from(InformationActivity.this)
                        .inflate(R.layout.dialog_edit,null);
                EditText mEtName_phoneNumber=mDlgPhoneNumberView.findViewById(R.id.et_name);
                Button mBtOk_phoneNumber=mDlgPhoneNumberView.findViewById(R.id.bt_ok);
                Button mBtCancel_phoneNumber=mDlgPhoneNumberView.findViewById(R.id.bt_cancel);
                mBtCancel_phoneNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDlgPhoneNumber.dismiss();
                    }
                });
                mBtOk_phoneNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mTvPhoneNumber.setText(mEtName_phoneNumber.getText().toString());
                        mDlgPhoneNumber.dismiss();
                    }
                });
                mDlgPhoneNumber= new AlertDialog.Builder(InformationActivity.this)
                        .setView(mDlgPhoneNumberView)
                        .create();
                mDlgPhoneNumber.show();
                break;
            case R.id.iv_password:
                View mDlgPasswordView = LayoutInflater.from(InformationActivity.this)
                        .inflate(R.layout.dialog_edit,null);
                EditText mEtName_password=mDlgPasswordView.findViewById(R.id.et_name);
                Button mBtOk_password=mDlgPasswordView.findViewById(R.id.bt_ok);
                Button mBtCancel_password=mDlgPasswordView.findViewById(R.id.bt_cancel);
                mBtCancel_password.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDlgPassword.dismiss();
                    }
                });
                mBtOk_password.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mTvPassword.setText(mEtName_password.getText().toString());
                        mDlgPassword.dismiss();
                    }
                });
                mDlgPassword= new AlertDialog.Builder(InformationActivity.this)
                        .setView(mDlgPasswordView)
                        .create();
                mDlgPassword.show();
                break;
            case R.id.iv_back:
                mIvBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}