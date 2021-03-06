package com.hp.bookaholic.admin.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.github.florent37.shapeofview.shapes.ArcView;
import com.google.android.material.button.MaterialButton;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.bookaholic.EndUsers.Models.BookdetailsModel;
import com.hp.bookaholic.R;
import com.hp.bookaholic.Retro.Retro;
import com.hp.bookaholic.admin.modelsAdmin.LendBookModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LendRequestDetails extends Fragment {


    private ArcView rootlay;
    private ImageView bookimg;
    private TextView bookname;
    private TextView author;
    private TextView lendDays;
    private TextView extralendDays;
    private TextView rent;
    private TextView name;
    private TextView email;
    private TextView phone;
    private TextView address;
    private TextView postaladd;
    private TextView accNo;
    private TextView ifsc;
    private TextView branch;
    private MaterialButton lend;
    private MaterialButton reject;
    BookdetailsModel bookdetailsModel;
    AppPreferences appPreferences;

    public LendRequestDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_lend_request_details, container, false);
        initView(root);
         appPreferences = AppPreferences.getInstance(getContext(), getResources().getString(R.string.app_name));
        Log.e("BOOK ID", appPreferences.getData("book_id"));
        new Retro().getApi().BOOKDETAILS_MODEL_CALL(appPreferences.getData("book_id")).enqueue(new Callback<BookdetailsModel>() {
            @Override
            public void onResponse(Call<BookdetailsModel> call, Response<BookdetailsModel> response) {
                bookdetailsModel = response.body();

                appPreferences.saveData("lenduser_id",bookdetailsModel.getBook_Details().get(0).getUser_id());
                if(bookdetailsModel != null) {
                    if (bookdetailsModel.getStatus().equalsIgnoreCase("success")) {
                        Glide.with(getContext()).load(bookdetailsModel.getBook_Details().get(0).getPhoto()).into(bookimg);
                        bookname.setText(bookdetailsModel.getBook_Details().get(0).getBook_name());
                        author.setText("Author : " + bookdetailsModel.getBook_Details().get(0).getAuthor());
                        lendDays.setText("Lend Price : " + bookdetailsModel.getBook_Details().get(0).getLend_days());
                        extralendDays.setText("Extra Price/Day : " + bookdetailsModel.getBook_Details().get(0).getExtra_days());
                        postaladd.setText("Address : " + bookdetailsModel.getBook_Details().get(0).getPostal_address());
                        accNo.setText("Account No : " + bookdetailsModel.getBook_Details().get(0).getAccount_no());
                        ifsc.setText("IFSC : " + bookdetailsModel.getBook_Details().get(0).getIfsc_code());
                        branch.setText("Branch : " + bookdetailsModel.getBook_Details().get(0).getBranch());
                        rent.setText("Original Price : " + bookdetailsModel.getBook_Details().get(0).getPrice() + " Rs");

                        name.setText("Name : "+appPreferences.getData("user_name"));
                        email.setText("Email : "+appPreferences.getData("user_email"));
                        phone.setText("Phone : "+appPreferences.getData("user_phone"));
                        address.setText("Address : "+appPreferences.getData("user_address"));




                    } else {
                        Toast.makeText(getContext(), "Book not found", Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<BookdetailsModel> call, Throwable t) {
                Toast.makeText(getContext(), "API FAILURE "+t, Toast.LENGTH_SHORT).show();

            }
        });

        lend.setOnClickListener(v -> {

            new Retro().getApi().lendThisBookCall(appPreferences.getData("book_id"),
                    appPreferences.getData("lenduser_id")
                    ).enqueue(new Callback<LendBookModel>() {
                @Override
                public void onResponse(Call<LendBookModel> call, Response<LendBookModel> response) {
                    LendBookModel lendBookModel=response.body();
                    if(lendBookModel.getStatus().equalsIgnoreCase("Updated  Successfully")){
                        Toast.makeText(getContext(), " Request passed to courier", Toast.LENGTH_SHORT).show();
                         Navigation.findNavController(v).navigate(R.id.action_lendRequestDetails_to_lendRequestFrag);

                    }else
                    {
                        Toast.makeText(getContext(), "under processing", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LendBookModel> call, Throwable t) {
                    Toast.makeText(getContext(), "LendBookModel api fail  "+t, Toast.LENGTH_SHORT).show();
                }
            });



        });
        reject.setOnClickListener(v -> {
            Toast.makeText(getContext(), " Request rejected", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(v).navigate(R.id.action_lendRequestDetails_to_lendRequestFrag);

        });


        return root;

    }

    private void initView(View root) {
        rootlay = root.findViewById(R.id.rootlay);
        bookimg = root.findViewById(R.id.bookimg);
        bookname = root.findViewById(R.id.bookname);
        author = root.findViewById(R.id.author);
        lendDays = root.findViewById(R.id.lendDays);
        extralendDays = root.findViewById(R.id.extralendDays);
        rent = root.findViewById(R.id.rent);
        name = root.findViewById(R.id.name);
        email = root.findViewById(R.id.email);
        phone = root.findViewById(R.id.phone);
        address = root.findViewById(R.id.address);
        postaladd = root.findViewById(R.id.postaladd);
        accNo = root.findViewById(R.id.accNo);
        ifsc = root.findViewById(R.id.ifsc);
        branch = root.findViewById(R.id.branch);
        lend = root.findViewById(R.id.lend);
        reject = root.findViewById(R.id.reject);
    }
}
