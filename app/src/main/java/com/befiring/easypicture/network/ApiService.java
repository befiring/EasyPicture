package com.befiring.easypicture.network;

import com.befiring.easypicture.bean.Image;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/8/31.
 */
public interface ApiService {
    @GET("search")
    Observable<List<Image>> search(@Query("q") String query);
}
