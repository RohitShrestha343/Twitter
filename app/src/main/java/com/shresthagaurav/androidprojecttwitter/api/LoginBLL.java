package com.shresthagaurav.androidprojecttwitter.api;


import com.shresthagaurav.androidprojecttwitter.model.SignUpResponse;
import com.shresthagaurav.androidprojecttwitter.url.URL;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {

    boolean isSuccess = false;

    public boolean checkUser(String username, String password) {

        ApiClass usersAPI = new ApiClass();
               // Url.getInstance().create(UsersAPI.class);
        Call<SignUpResponse> usersCall = usersAPI.calls().checkUser(username, password);

        try {
            Response<SignUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {

                URL.token += loginResponse.body().getToken();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
