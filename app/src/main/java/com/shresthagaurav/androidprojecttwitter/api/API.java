package com.shresthagaurav.androidprojecttwitter.api;


import com.shresthagaurav.androidprojecttwitter.model.Check;
import com.shresthagaurav.androidprojecttwitter.model.ImageModel;
import com.shresthagaurav.androidprojecttwitter.model.SignUpResponse;
import com.shresthagaurav.androidprojecttwitter.model.TweetM;
import com.shresthagaurav.androidprojecttwitter.model.User;
import com.shresthagaurav.androidprojecttwitter.model.UserInfo;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface API {
    @FormUrlEncoded
    @POST("users/login")
    Call<SignUpResponse> checkUser(@Field("email") String username, @Field("password") String password);


    @POST("users/signup")
    Call<SignUpResponse> register(@Body User cud);

    @Multipart
    @POST("upload")
    Call<ImageModel> uploadImage(@Part MultipartBody.Part imageFile);

    @POST("users/check")
    Call<Check> check(@Body User email);

    @POST("users/showalltweet")
    Call<List<TweetM>> GetTweet(@Header("Authorization") String token);

    @POST("users/me")
    Call<UserInfo> getUser(@Header("Authorization") String token);
}
