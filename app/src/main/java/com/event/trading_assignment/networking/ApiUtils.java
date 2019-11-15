package com.event.trading_assignment.networking;

public class ApiUtils {


    private static final String BASE_URL = "https://ant.aliceblueonline.com/api/v2/";  //Todo


    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);


    }
}