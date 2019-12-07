package com.example.quanlythuvien;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.quanlythuvien.Cong_Nghe_Thong_Tin.CNTTFragMent;
import com.example.quanlythuvien.Giao_Duc.GDFragMent;
import com.example.quanlythuvien.Giai_Tri.GiaiTriFragment;
import com.example.quanlythuvien.Khoa_Hoc_Kinh_Te.KHKTFragMent;

public class ThuVienFragMent extends AppCompatActivity{
//    private TextView btn_cntt, btn_gdxh, btn_khkt;
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
                case 2:
                    return new GDFragMent();
                default:
                    return new GiaiTriFragment();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

            @Nullable
            @Override
            public CharSequence getPageTitle(int i){
                switch (i) {
                    case 0:
                        return "Công Nghệ Thông Tin";
                    case 1:
                        return "Khoa Học Kỹ Thuật";
                    case 2:
                        return "Giáo Dục";
                    default:
                        return "Giải Trí";

            }
        }
    }

}
