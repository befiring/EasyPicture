package com.befiring.easypicture.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.befiring.easypicture.bean.JokeResponse.Data;

import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
public class JokeViewPagerAdapter extends PagerAdapter {

    private List<TextView> textViews;

    public JokeViewPagerAdapter(List<TextView> jokes) {
        this.textViews = jokes;
    }

    @Override
    public int getCount() {
        return textViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(textViews.get(position % textViews.size()), 0);
        return textViews.get(position % textViews.size());
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager)container).removeView(textViews.get(position % textViews.size()));
    }
}
