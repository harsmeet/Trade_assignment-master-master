package com.event.trading_assignment.networking;


import com.event.trading_assignment.pojo.ResponseLoginDataSet;

import org.json.JSONArray;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @GET("category_list.php/?id_category=43")
//    Call<Example> getwikiData();


    //    App feedback

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("login")
    Call<ResponseLoginDataSet> postlogin(@Body Map<String, String> body);

//
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("checktwofa")
    Call<ResponseLoginDataSet> postchecking(@Body Map<String, String> body);


}
