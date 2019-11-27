package com.example.quanlythuvien.Fragment;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quanlythuvien.R;

public class ThuVienFragMent extends AppCompatActivity{
    private TextView btn_cntt, btn_gdxh, btn_khkt;
    private ViewPager view_pager;
    private TabLayout tab_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thuvien_fragment);
        view_pager = findViewById(R.id.ViewPager);
        tab_layout = findViewById(R.id.tab_layout);

        view_pager.setAdapter(new PageAdapter());
        tab_layout.setupWithViewPager(view_pager);

        //

    }

    //
private class PageAdapter extends FragmentPagerAdapter {

        public PageAdapter() {
            super(getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int i) {
            switch (i){
                case 0:
                    return new CNTTFragMent();
                case 1:
                    return new KHKTFragMent();
                default:
                    return new GDFragMent();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

            @Nullable
            @Override
            public CharSequence getPageTitle(int i){
                switch (i) {
                    case 0:
                        return "CNTT";
                    case 1:
                        return "Khoa Học KT";
                    default:
                        return "Giáo Dục";

            }
        }
    }

}
