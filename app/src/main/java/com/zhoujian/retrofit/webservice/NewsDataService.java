package com.zhoujian.retrofit.webservice;

import com.zhoujian.retrofit.bean.NewsData;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by zhoujian on 2016/12/28.
 */



public interface NewsDataService
{
    @POST("news/list")
    Call<NewsData> getNewsData(@Query("key") String key);
}
