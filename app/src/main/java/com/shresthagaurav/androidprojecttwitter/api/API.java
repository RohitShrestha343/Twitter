package com.shresthagaurav.androidprojecttwitter.api;





import com.shresthagaurav.androidprojecttwitter.model.Check;
import com.shresthagaurav.androidprojecttwitter.model.User;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface API {
//    @POST("users/signup")
//    Call<UserToken> register(@Body User_model cud);

//    @Multipart
//    @POST("upload")
//    Call<ImageModel> uploadImage(@Part MultipartBody.Part imageFile);

    @POST("users/check")
    Call<Check> check(@Body User email);
}
