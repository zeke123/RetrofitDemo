package com.zhoujian.retrofit.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zhoujian.retrofit.R;
import com.zhoujian.retrofit.bean.NewsData;
import com.zhoujian.retrofit.bean.WeatherData;
import com.zhoujian.retrofit.webservice.NewsDataService;
import com.zhoujian.retrofit.webservice.UploadFileService;
import com.zhoujian.retrofit.webservice.WeatherService;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bt_get)
    Button mBtGet;
    @BindView(R.id.resault)
    TextView mResault;
    @BindView(R.id.bt_news)
    Button mBtNews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        clickEvent();

       // uploadPicture();

       // uploadPictures();

    }

    private void uploadPictures()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.68:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UploadFileService service = retrofit.create(UploadFileService.class);
        //sd卡下imgs下的两张图片（拍照或者从相册中选择都可以获取到图片文件）
        File file1= new File(Environment.getExternalStorageDirectory()+"/imgs", "demo1.png");
        File file2 = new File(Environment.getExternalStorageDirectory()+"/imgs", "demo2.png");

        RequestBody photoRequestBody1 = RequestBody.create(MediaType.parse("application/octet-stream"), file1);
        RequestBody photoRequestBody2 = RequestBody.create(MediaType.parse("application/octet-stream"), file2);
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "图片的描述");

        Map<String,RequestBody> images = new HashMap<String,RequestBody>();
        //添加file1
        images.put("images\"; filename=\""+file1.getName(), photoRequestBody1);
        //添加file2
        images.put("images\"; filename=\""+file2.getName(), photoRequestBody2);
        //添加图片描述
        images.put("description",  description);

        Call<ResponseBody> call = service.uploadfiles(images, description);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                Toast.makeText(MainActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "上传失败", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void uploadPicture()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.68:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UploadFileService service = retrofit.create(UploadFileService.class);

        //sd卡下imgs下的一张图片（拍照或者从相册中选择都可以获取到图片文件）
        File file = new File(Environment.getExternalStorageDirectory()+"/imgs", "demo.png");

        //设置Content-Type:application/octet-stream
        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);

        //设置Content-Disposition:form-data; name="photo"; filename="demo.png"
        MultipartBody.Part photo = MultipartBody.Part.createFormData("photo", file.getName(), photoRequestBody);

        //添加参数description，并且是文本类型
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "图片的描述");

        Call<ResponseBody> mCall = service.uploadFile(photo, description);

        mCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Toast.makeText(MainActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {
                Toast.makeText(MainActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
            }
        });



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


                    private int mCode;

                    @Override
                    public void onResponse(Call<NewsData> call, Response<NewsData> response) {


                        mCode = response.body().getError_code();


                        mResault.setText( response.body().toString());
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


                        String sting =  response.body().toString();
                        mResault.setText(sting);
                        Toast.makeText(MainActivity.this, "请求数据成功", Toast.LENGTH_SHORT).show();
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


