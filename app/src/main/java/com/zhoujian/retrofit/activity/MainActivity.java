package com.zhoujian.retrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.zhoujian.retrofit.R;
import com.zhoujian.retrofit.bean.NewsData;
import com.zhoujian.retrofit.bean.WeatherData;
import com.zhoujian.retrofit.webservice.NewsDataService;
import com.zhoujian.retrofit.webservice.WeatherService;
import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.bt_get)
    Button mBtGet;
    @InjectView(R.id.resault)
    TextView mResault;
    @InjectView(R.id.bt_news)
    Button mBtNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        clickEvent();

    }

    private void clickEvent() {

        mBtNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {



                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://op.juhe.cn/yi18/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                NewsDataService service = retrofit.create(NewsDataService.class);
                Call<NewsData> call = service.getNewsData("4abbdf579ea79bef50a5af968a7e458a");
                call.enqueue(new Callback<NewsData>() {
                    @Override
                    public void onResponse(Call<NewsData> call, Response<NewsData> response) {
                        mResault.setText(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<NewsData> call, Throwable t)
                    {
                        Toast.makeText(MainActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });




        mBtGet.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {




                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://v.juhe.cn/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                WeatherService service = retrofit.create(WeatherService.class);
                Call<WeatherData> call = service.getWeatherData("北京", "5c2dd6dd912ba8336889b0325689f809");
                call.enqueue(new Callback<WeatherData>() {
                    @Override
                    public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {

                        mResault.setText(response.body().toString());
                    }
                    @Override
                    public void onFailure(Call<WeatherData> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });
    }
}


