package com.hp.bookaholic.Retro;

import android.util.Log;

import com.hp.bookaholic.Models.LoginModel;
import com.hp.bookaholic.Models.SignupModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apis {

    @GET("user_login.php?")
    Call<LoginModel> LOGIN_MODEL_CALL(@Query("phone") String phone,
                                      @Query("password") String password);

}
