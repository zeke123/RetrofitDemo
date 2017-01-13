package com.zhoujian.retrofit.webservice;

import com.zhoujian.retrofit.bean.NewsData;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zhoujian on 2016/12/28.
 */



public interface NewsDataService
{
    @FormUrlEncoded
    @POST("news/list")
    Call<NewsData> getNewsData(@Field("key") String key);


}
