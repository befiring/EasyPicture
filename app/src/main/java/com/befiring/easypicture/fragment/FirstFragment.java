package com.befiring.easypicture.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.befiring.easypicture.R;
import com.befiring.easypicture.adapter.FirstGridAdapter;
import com.befiring.easypicture.bean.PictureResponse.Image;
import com.befiring.easypicture.network.Network;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/31.
 */
public class FirstFragment extends BaseFragment {

    @Bind(R.id.first_refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.first_recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.search_et)
    EditText search_et;
    @Bind(R.id.search_btn)
    Button search_btn;
    @Bind(R.id.submit)
    Button submit_btn;

    private static FirstFragment instance;
    FirstGridAdapter adapter;

    Observer<List<Image>> observer = new Observer<List<Image>>() {
        @Override
        public void onCompleted() {
            int b=0;
        }

        @Override
        public void onError(Throwable e) {
               int a=0;
        }

        @Override
        public void onNext(List<Image> images) {
            refreshLayout.setRefreshing(false);
            adapter.setImages(images);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.bind(this, view);
        SMSSDK.registerEventHandler(eh); //注册短信回调

        adapter=new FirstGridAdapter();
        refreshLayout.setEnabled(false);
        refreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unsubscribe();
                refreshLayout.setRefreshing(true);
                subscription= Network.getApiService("http://zhuangbi.info/")
                        .searchPicture(search_et.getText().toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer);
                getSMS("86","13392533903");
            }
        });
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("wm","submit click");
                 SMSSDK.submitVerificationCode("86","13392533903",search_et.getText().toString());
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        SMSSDK.unregisterEventHandler(eh);
        super.onDestroyView();
    }

    public static FirstFragment getInstance() {
        if(instance==null){
            instance=new FirstFragment();
        }
        return instance;
    }

   public void getSMS(String country,String phone){
       SMSSDK.getSupportedCountries();
       SMSSDK.getVerificationCode(country, phone, new OnSendMessageHandler() {
           @Override
           public boolean onSendMessage(String s, String s1) {
               Log.d("wm",s+"---"+s1);
               return false;
           }
       });
   }

    EventHandler eh=new EventHandler(){

        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getActivity(),"校验成功",Toast.LENGTH_SHORT).show();
//                        }
//                    });
                    Log.d("wm","校验成功");
                    //提交验证码成功
                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    //获取验证码成功
                }else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                    //返回支持发送验证码的国家列表
                }

            }else{
                ((Throwable)data).printStackTrace();
            }
        }

    };


}
