package com.hp.bookaholic.Retro;

import android.util.Log;

import com.hp.bookaholic.Models.AddBookModel;
import com.hp.bookaholic.Models.BookdetailsModel;
import com.hp.bookaholic.Models.BooklistModel;
import com.hp.bookaholic.Models.LoginModel;
import com.hp.bookaholic.Models.SignupModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Apis {


    @GET("user_login.php?")
    Call<LoginModel> LOGIN_MODEL_CALL(@Query("phone") String phone,
                                      @Query("password") String password);

    @GET("user_registration.php?")
    Call<SignupModel> SIGNUP_MODEL_CALL(@Query("name") String name,
                                        @Query("email") String email,
                                        @Query("phone") String phone,
                                        @Query("password") String password,
                                        @Query("postal_address") String address);

    @GET("view_approved_book.php?status=1")
    Call<BooklistModel> BOOKLIST_MODEL_CALL();

    @Multipart
    @POST("addbook.php")
    Call<AddBookModel> ADD_BOOK_MODEL_CALL(@Part("book_name") RequestBody book_name,
                                           @Part("author") RequestBody author,
                                           @Part("user_id") RequestBody user_id,
                                           @Part("lend_days") RequestBody lend_days,
                                           @Part("extra_days") RequestBody extra_days,
                                           @Part("postal_address") RequestBody postal_address,
                                           @Part("account_no") RequestBody account_no,
                                           @Part("ifsc_code") RequestBody ifsc_code,
                                           @Part("branch") RequestBody branch,
                                           @Part MultipartBody.Part file);


    @GET("view_particular_book.php?")
    Call<BookdetailsModel>BOOKDETAILS_MODEL_CALL(@Query("book_id")String id);



}
