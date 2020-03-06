package com.hp.bookaholic.EndUsers.ui.home;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.github.florent37.shapeofview.shapes.ArcView;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.bookaholic.EndUsers.Models.BookdetailsModel;
import com.hp.bookaholic.R;
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
    private ArcView rootlay;
    private ImageView bookimg;
    private TextView bookname;
    private TextView author;
    private TextView lendDays;
    private TextView extralendDays;
    private TextView postaladd;
    private TextView accNo;
    private TextView ifsc;
    private TextView branch;


    public Book_dtails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_book_dtails, container, false);
        initView(root);
        appPreferences = AppPreferences.getInstance(getContext(), getResources().getString(R.string.app_name));

        Log.e("BOOK ID", appPreferences.getData("book_id"));
        new Retro().getApi().BOOKDETAILS_MODEL_CALL(appPreferences.getData("book_id")).enqueue(new Callback<BookdetailsModel>() {
            @Override
            public void onResponse(Call<BookdetailsModel> call, Response<BookdetailsModel> response) {
                bookdetailsModel = response.body();

                appPreferences.saveData("user_id",bookdetailsModel.getBook_Details().get(0).getUser_id());

                Glide.with(getContext()).load(bookdetailsModel.getBook_Details().get(0).getPhoto()).into(bookimg);
                bookname.setText(bookdetailsModel.getBook_Details().get(0).getBook_name());
                author.setText("Author : " + bookdetailsModel.getBook_Details().get(0).getAuthor());
                lendDays.setText("Lend Days : " + bookdetailsModel.getBook_Details().get(0).getLend_days());
                extralendDays.setText("Extra Days : " + bookdetailsModel.getBook_Details().get(0).getExtra_days());
                postaladd.setText("Address : " + bookdetailsModel.getBook_Details().get(0).getPostal_address());
                accNo.setText("Account No : " + bookdetailsModel.getBook_Details().get(0).getAccount_no());
                ifsc.setText("IFSC : " + bookdetailsModel.getBook_Details().get(0).getIfsc_code());
                branch.setText("Branch : " + bookdetailsModel.getBook_Details().get(0).getBranch());

            }

            @Override
            public void onFailure(Call<BookdetailsModel> call, Throwable t) {
                Toast.makeText(getContext(), "API FAILURE "+t, Toast.LENGTH_SHORT).show();

            }
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
        postaladd = root.findViewById(R.id.postaladd);
        accNo = root.findViewById(R.id.accNo);
        ifsc = root.findViewById(R.id.ifsc);
        branch = root.findViewById(R.id.branch);
    }
}


