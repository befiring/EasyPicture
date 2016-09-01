package com.befiring.easypicture.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.befiring.easypicture.R;
import com.befiring.easypicture.adapter.JokeViewPagerAdapter;
import com.befiring.easypicture.bean.JokeResponse.Data;
import com.befiring.easypicture.bean.JokeResponse.JokeResponse;
import com.befiring.easypicture.network.Network;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/31.
 */
public class SecondFragment extends BaseFragment{

    @Bind(R.id.get)Button getBtn;
    @Bind(R.id.text)TextView titleText;
    @Bind(R.id.joke_viewpager)ViewPager mViewPager;
    public static SecondFragment instance;
    private List<TextView> textViews=new ArrayList<>();
    private JokeViewPagerAdapter mAdapter;

    Observer<JokeResponse> observer=new Observer<JokeResponse>() {
        @Override
        public void onCompleted() {
            int a=0;
        }

        @Override
        public void onError(Throwable e) {
            int b=0;
        }

        @Override
        public void onNext(JokeResponse response) {
            List<Data> datas=response.getResult().getData();
            for(int i=0;i<datas.size();i++){
                TextView textView=new TextView(getActivity());
                textView.setText(datas.get(i).getContent());
                textView.setTextSize(18);
                textViews.add(textView);
            }
            mAdapter=new JokeViewPagerAdapter(textViews);
            mViewPager.setAdapter(mAdapter);
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_second,container,false);
        ButterKnife.bind(this,view);

        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unsubscribe();
                subscription= Network.getApiService("http://japi.juhe.cn/joke/")
                        .getJokeData("8e10be3f8234d89f061d18ba5150a7d1","asc",1,10,"1418745237")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer);
            }
        });
        return view;
    }

    public static SecondFragment getInstance() {
        if(instance==null){
            instance=new SecondFragment();
        }
        return instance;
    }
}
