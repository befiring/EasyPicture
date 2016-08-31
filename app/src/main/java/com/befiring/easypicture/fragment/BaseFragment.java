package com.befiring.easypicture.fragment;

import android.support.v4.app.Fragment;

import rx.Subscription;

/**
 * Created by Administrator on 2016/8/31.
 */
public abstract class BaseFragment extends Fragment {
    protected Subscription subscription;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

}
