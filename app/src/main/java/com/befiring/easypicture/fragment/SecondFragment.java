package com.befiring.easypicture.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.befiring.easypicture.R;

/**
 * Created by Administrator on 2016/8/31.
 */
public class SecondFragment extends BaseFragment{

    public static SecondFragment instance;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_second,container,false);
        return view;
    }

    public static SecondFragment getInstance() {
        if(instance==null){
            instance=new SecondFragment();
        }
        return instance;
    }
}
