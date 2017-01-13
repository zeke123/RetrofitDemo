package com.zhoujian.retrofit.webservice;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by zhoujian on 2017/1/13.
 */

public interface UploadFileService
{
    @Multipart
    @POST("uploaad/file")
    Call<ResponseBody>  uploadFile(@Part MultipartBody.Part photo, @Part("description") RequestBody description);


    @Multipart
    @POST("uploaad/files")
    Call<ResponseBody> uploadfiles(@PartMap Map<String, RequestBody> images, @Part("description") RequestBody description);
}
