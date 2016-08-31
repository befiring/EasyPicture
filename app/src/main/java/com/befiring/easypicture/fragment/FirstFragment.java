package com.befiring.easypicture.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.befiring.easypicture.R;
import com.befiring.easypicture.adapter.FirstGridAdapter;
import com.befiring.easypicture.bean.Image;
import com.befiring.easypicture.network.Network;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Scheduler;
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

    private static FirstFragment instance;
    FirstGridAdapter adapter;

    Observer<List<Image>> observer = new Observer<List<Image>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

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
                subscription= Network.getApiService()
                        .search(search_et.getText().toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer);
            }
        });
        return view;
    }

    public static FirstFragment getInstance() {
        if(instance==null){
            instance=new FirstFragment();
        }
        return instance;
    }

//    private List<Image> getData() {
//        List<Image> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            Image image = new Image("安静", "http://zhuangbi.idagou.com/i/2016-06-25-7f28e26f4b0d6c071492d6d0793cfe0d.jpg?");
//            list.add(image);
//        }
//        return list;
//    }

}
