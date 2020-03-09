package com.hp.bookaholic.Retro;

import com.hp.bookaholic.EndUsers.Models.AddBookModel;
import com.hp.bookaholic.EndUsers.Models.Avail_Set_model;
import com.hp.bookaholic.EndUsers.Models.AvailableBookListModel;
import com.hp.bookaholic.EndUsers.Models.BookdetailsModel;
import com.hp.bookaholic.EndUsers.Models.BooklistModel;
import com.hp.bookaholic.EndUsers.Models.BuyBook_Model;
import com.hp.bookaholic.EndUsers.Models.LoginModel;
import com.hp.bookaholic.EndUsers.Models.SignupModel;
import com.hp.bookaholic.admin.modelsAdmin.AdminLoginModel;
import com.hp.bookaholic.admin.modelsAdmin.ApproveBook_Model;
import com.hp.bookaholic.admin.modelsAdmin.Booklists_For_Approval_Model;
import com.hp.bookaholic.admin.modelsAdmin.DeleteBookModel;
import com.hp.bookaholic.admin.modelsAdmin.Lend_Requests_Model;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
                                           @Part("price") RequestBody price,
                                           @Part MultipartBody.Part file);


    @GET("view_particular_book.php?")
    Call<BookdetailsModel> BOOKDETAILS_MODEL_CALL(@Query("book_id") String id);

    @GET("view_available_book.php")
    Call<AvailableBookListModel> AVAILABLE_BOOK_LIST_MODEL_CALL();


    @FormUrlEncoded
    @POST("admin_login.php")
    Call<AdminLoginModel> ADMIN_LOGIN_MODEL_CALL(@Field("username") String username,
                                                 @Field("password") String password);


    @GET("booksForApproval.php")
    Call<Booklists_For_Approval_Model> BOOKLISTS_FOR_APPROVAL_MODEL_CALL();

    @GET("approve_book_by_admin.php?")
    Call<ApproveBook_Model> APPROVE_BOOK_MODEL_CALL(@Query("book_id") String book_id,
                                                    @Query("user_id") String user_id);

    @GET("delete_book_request.php?")
    Call<DeleteBookModel> DELETE_BOOK_MODEL_CALL(@Query("book_id") String book_id,
                                                 @Query("user_id") String user_id);

    @GET("available_status_flag_view.php")
    Call<Lend_Requests_Model> LEND_REQUESTS_MODEL_CALL();


    @FormUrlEncoded
    @POST("buy_book.php?")
    Call<BuyBook_Model> BUY_BOOK_MODEL_CALL(@Field("user_id") String user_id,
                                            @Field("book_id") String book_id,
                                            @Field("buy_date") String buy_date,
                                            @Field("lend_price") String lend_price
    );


    @GET("book_available_set.php?")
    Call<Avail_Set_model>AVAIL_SET_MODEL_CALL(@Query("book_id") String book_id);
}
