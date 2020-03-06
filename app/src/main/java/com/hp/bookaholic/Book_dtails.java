package com.hp.bookaholic;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.bookaholic.Models.BookdetailsModel;
import com.hp.bookaholic.Retro.Retro;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Book_dtails extends Fragment {
    BookdetailsModel bookdetailsModel;
    private AppPreferences appPreferences;
    TextView name;
    TextView author;
    TextView lenddays;
    TextView extradays;
    ImageView image;


    public Book_dtails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_book_dtails, container, false);
        appPreferences = AppPreferences.getInstance(getContext(), getResources().getString(R.string.app_name));

        Log.e("BOOK ID",appPreferences.getData("book_id"));

        initView(root);
        Retro retro=new Retro();
        retro.getApi().BOOKDETAILS_MODEL_CALL(appPreferences.getData("book_id")).enqueue(new Callback<BookdetailsModel>() {
            @Override
            public void onResponse(Call<BookdetailsModel> call, Response<BookdetailsModel> response) {
                bookdetailsModel=response.body();
                Log.e("RESPONSE", String.valueOf(bookdetailsModel));

//                name.setText(bookdetailsModel.getBook_Details().get(0).getBook_name());
//                author.setText(bookdetailsModel.getBook_Details().get(0).getAuthor());
//
//                Glide.with(getContext())
//                        .load(bookdetailsModel.getBook_Details().get(0).getPhoto())
//                        .placeholder(R.drawable.bookround)
//                        .into(image);
//                lenddays.setText(bookdetailsModel.getBook_Details().get(0).getLend_days());
//                extradays.setText(bookdetailsModel.getBook_Details().get(0).getExtra_days());
//
           }

            @Override
            public void onFailure(Call<BookdetailsModel> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }
    private void initView(View root) {
        image=root.findViewById(R.id.bookdetails_img);
        name=root.findViewById(R.id.bookdetails_name);
        author=root.findViewById(R.id.bookdetails_author);
        lenddays=root.findViewById(R.id.bookdetails_lenddays);
        extradays=root.findViewById(R.id.bookdetails_extradays);


    }


}


