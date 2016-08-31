package com.befiring.easypicture.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.befiring.easypicture.R;
import com.befiring.easypicture.bean.Joke;
import com.befiring.easypicture.network.Network;

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
    @Bind(R.id.text)TextView textView;
    public static SecondFragment instance;

    Observer<List<Joke>> observer=new Observer<List<Joke>>() {
        @Override
        public void onCompleted() {
            int a=0;
        }

        @Override
        public void onError(Throwable e) {
            int b=0;
        }

        @Override
        public void onNext(List<Joke> jokes) {
             textView.setText(jokes.get(1).getReason());
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
                        .getJokeData("8e10be3f8234d89f061d18ba5150a7d1","asc",1,1,String.valueOf(System.currentTimeMillis()))
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
