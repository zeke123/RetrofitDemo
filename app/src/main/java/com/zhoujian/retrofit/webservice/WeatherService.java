package com.zhoujian.retrofit.webservice;
import com.zhoujian.retrofit.bean.WeatherData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zhoujian on 2016/12/28.
 */

public interface WeatherService
{
    @GET("weather/index")
    Call<WeatherData> getWeatherData(@Query("cityname") String cityname,@Query("key") String key);
}
