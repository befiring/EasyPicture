package com.befiring.easypicture.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TableLayout;

import com.befiring.easypicture.R;
import com.befiring.easypicture.fragment.BaseFragment;
import com.befiring.easypicture.fragment.FirstFragment;
import com.befiring.easypicture.fragment.SecondFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tablayout)TabLayout tableLayout;
    @Bind(R.id.viewpager) ViewPager viewPager;
    @Bind(R.id.toolbar)Toolbar toolbar;

    List<BaseFragment> fragments=new ArrayList<>();
    FirstFragment firstFragment=new FirstFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initData();

        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));
        tableLayout.setupWithViewPager(viewPager);

        getSupportFragmentManager().beginTransaction().add(R.id.content,firstFragment).show(firstFragment).commit();
    }

    private void initData(){
        fragments.add(FirstFragment.getInstance());
        fragments.add(SecondFragment.getInstance());
    }

    public class MainViewPagerAdapter extends FragmentPagerAdapter{

        public MainViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "搞笑图片";
                case 1:
                    return "笑话";
                default:
                    break;
            }
            return super.getPageTitle(position);
        }
    }
}
