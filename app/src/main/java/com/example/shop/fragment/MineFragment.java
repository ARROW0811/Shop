package com.example.shop.fragment;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static android.provider.MediaStore.EXTRA_OUTPUT;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.shop.R;
import com.example.shop.activity.BuyedActivity;
import com.example.shop.activity.CollectedActivity;
import com.example.shop.activity.InformationActivity;
import com.example.shop.activity.PublishedActivity;
import com.example.shop.activity.SoldActivity;
import com.example.shop.util.BlurTransformation;
import com.example.shop.util.L;
import com.example.shop.util.T;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MineFragment extends Fragment {
    private ImageView mIvIcon;
    private ImageView mIvPublished;
    private TextView mTvPublished;
    private ImageView mIvSold;
    private TextView mTvSold;
    private ImageView mIvBuyed;
    private TextView mTvBuyed;
    private ImageView mIvCollection;
    private TextView mTvCollection;
    private ImageView mIvInformation;
    private TextView mTvInformation;
    private Button mBtnQuit;
    private PopupWindow popupWindow;
    private Uri imageUri;
    public static final int TAKE_PHOTO=1;
    public static final int FROM_ALBUMS=2;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        mIvIcon = view.findViewById(R.id.iv_icon_bg);
        mIvPublished=view.findViewById(R.id.iv_published);
        mTvPublished=view.findViewById(R.id.tv_published);
        mIvSold=view.findViewById(R.id.iv_sold);
        mTvSold=view.findViewById(R.id.tv_sold);
        mIvBuyed=view.findViewById(R.id.iv_buyed);
        mTvBuyed=view.findViewById(R.id.tv_buyed);
        mIvCollection=view.findViewById(R.id.iv_collection);
        mTvCollection=view.findViewById(R.id.tv_collection);
        mIvInformation=view.findViewById(R.id.iv_information);
        mTvInformation=view.findViewById(R.id.tv_information);
        mBtnQuit=view.findViewById(R.id.bt_quit);
        mIvPublished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), PublishedActivity.class);
                startActivity(intent);
            }
        });
        mTvPublished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),PublishedActivity.class);
                startActivity(intent);
            }
        });
        mIvSold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SoldActivity.class);
                startActivity(intent);
            }
        });
        mTvSold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SoldActivity.class);
                startActivity(intent);
            }
        });
        mIvBuyed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), BuyedActivity.class);
                startActivity(intent);
            }
        });
        mTvBuyed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), BuyedActivity.class);
                startActivity(intent);
            }
        });
        mIvCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), CollectedActivity.class);
                startActivity(intent);
            }
        });
        mTvCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), CollectedActivity.class);
                startActivity(intent);
            }
        });
        mIvInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), InformationActivity.class);
                startActivity(intent);
            }
        });
        mTvInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), InformationActivity.class);
                startActivity(intent);
            }
        });
        mBtnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                L.d("退出登录");
                T.showShort(getContext(),"退出登录");
            }
        });
        mIvIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_popup_windows();
            }
        });

        Glide.with(this).load(R.drawable.icon)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(requireContext(), 75, 5)))
                .into(mIvIcon);
        //参数用来设置模糊程度
        return view;
    }
    //展示修改头像的选择框，并设置选择框的监听器
    private void show_popup_windows(){
        RelativeLayout layout_photo_selected = (RelativeLayout) getLayoutInflater().inflate(R.layout.photo_select,null);
        if(popupWindow==null){
            popupWindow = new PopupWindow(layout_photo_selected, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        }
        //设置动画
        popupWindow.setAnimationStyle(R.style.popwindow_anim);
        //显示popupwindows
        popupWindow.showAtLocation(layout_photo_selected, Gravity.CENTER, 0, 0);
        //设置监听器
        TextView mTvTakePhoto =  (TextView) layout_photo_selected.findViewById(R.id.tv_take_photo);
        TextView mTvFromAlbums = (TextView)  layout_photo_selected.findViewById(R.id.tv_from_albums);
        LinearLayout mLlCancel = (LinearLayout) layout_photo_selected.findViewById(R.id.ll_cancel);
        //拍照按钮监听
        mTvTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
        //相册按钮监听
        mTvFromAlbums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //去除选择框
                popupWindow.dismiss();
            }
        });
        //取消按钮监听
        mLlCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });


    }



}