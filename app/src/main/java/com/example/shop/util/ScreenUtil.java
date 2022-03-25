package com.example.shop.util;

import android.content.Context;

public class ScreenUtil {
    public  static int dp2pxInt(Context context,float dpValue){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }
    public static int px2dpInt(Context context,float pxValue){
        final  float scale=context.getResources().getDisplayMetrics().density;
        return (int) (pxValue/scale+0.5f);
    }
}
