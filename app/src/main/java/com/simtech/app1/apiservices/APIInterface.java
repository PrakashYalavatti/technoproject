package com.simtech.app1.apiservices;

import com.simtech.app1.apiservices.apirequestresponse.MainMenuResponse;
import com.simtech.app1.apiservices.apirequestresponse.UserLoginRequest;
import com.simtech.app1.apiservices.apirequestresponse.UserLoginResponse;
import com.simtech.app1.pojo.layout.LayoutDetailsPojo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    @POST("/api/login")
    Call<UserLoginResponse> createUser(@Body UserLoginRequest userLoginResponse);

    @GET("/api/main_menu")
    Call<MainMenuResponse> mainMenu(@Query("username") String username);

    @GET("/api/layout")
    Call<LayoutDetailsPojo> layoutDetails(@Query("username") String username, @Query("startdate") String startdate,
                                          @Query("locationname") String locationname, @Query("locationid") String locationid,
                                          @Query("trialtypename") String trialtypename, @Query("trialtypeid") String trialtypeid);
}
