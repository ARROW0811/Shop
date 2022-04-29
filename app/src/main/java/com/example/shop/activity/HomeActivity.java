package com.example.shop.activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.shop.R;
import com.example.shop.adapter.MainFragmentPagerAdapter;
import com.example.shop.fragment.MainFragment;
import com.example.shop.fragment.PublishFragment;
import com.example.shop.fragment.MineFragment;
import com.example.shop.fragment.SortFragment;
import com.example.shop.util.WindowBarStatusUtil;

public class HomeActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private ViewPager2 viewPager2;
    private RadioGroup radioGroup;
    private MainFragmentPagerAdapter mainFragmentPagerAdapter;
    private RadioButton mRbMain,mRbPresent,mRbMessage,mRbMine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        WindowBarStatusUtil.setBarStatus(this, View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        mainFragmentPagerAdapter = new MainFragmentPagerAdapter(this);
        radioGroup = findViewById(R.id.rg_tab_bar);
        viewPager2 = findViewById(R.id.vpager);
        radioGroup.setOnCheckedChangeListener(this::onCheckedChanged);

        viewPager2.setAdapter(mainFragmentPagerAdapter);
        mainFragmentPagerAdapter.addFragment(new MainFragment());
        mainFragmentPagerAdapter.addFragment(new SortFragment());
        mainFragmentPagerAdapter.addFragment(new PublishFragment());
        mainFragmentPagerAdapter.addFragment(new MineFragment());

        mRbMain=(RadioButton) findViewById(R.id.rb_main);
        mRbPresent=(RadioButton) findViewById(R.id.rb_present);
        mRbMessage=(RadioButton) findViewById(R.id.rb_message);
        mRbMine=(RadioButton) findViewById(R.id.rb_mine);

        Drawable drawable1=getResources().getDrawable(R.drawable.tab_menu_main);
        drawable1.setBounds(0,0,69,69);
        mRbMain.setCompoundDrawables(null,drawable1,null,null);

        Drawable drawable2=getResources().getDrawable(R.drawable.tab_menu_sort);
        drawable2.setBounds(0,0,69,69);
        mRbPresent.setCompoundDrawables(null,drawable2,null,null);

        Drawable drawable3=getResources().getDrawable(R.drawable.tab_menu_message);
        drawable3.setBounds(0,0,69,69);
        mRbMessage.setCompoundDrawables(null,drawable3,null,null);

        Drawable drawable4=getResources().getDrawable(R.drawable.tab_menu_mine);
        drawable4.setBounds(0,0,69,69);
        mRbMine.setCompoundDrawables(null,drawable4,null,null);

        viewPager2.setCurrentItem(0);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        mRbMain.setChecked(true);
                        Log.d(TAG, "main被选中了");
                        break;
                    case 1:
                        mRbPresent.setChecked(true);
                        Log.d(TAG, "present被选中了");
                        break;
                    case 2:
                        mRbMessage.setChecked(true);
                        Log.d(TAG, "message被选中了");
                        break;
                    case 3:
                        mRbMine.setChecked(true);
                        Log.d(TAG, "mine被选中了");
                        break;
                }
            }
        });
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_main:
                viewPager2.setCurrentItem(0);
                break;
            case R.id.rb_present:
                viewPager2.setCurrentItem(1);
                break;
            case R.id.rb_message:
                viewPager2.setCurrentItem(2);
                break;
            case R.id.rb_mine:
                viewPager2.setCurrentItem(3);
        }
    }


}
