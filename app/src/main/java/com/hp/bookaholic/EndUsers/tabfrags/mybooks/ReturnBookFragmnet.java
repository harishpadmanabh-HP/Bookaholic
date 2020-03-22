package com.hp.bookaholic.EndUsers.tabfrags.mybooks;


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
import com.google.android.material.button.MaterialButton;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.bookaholic.EndUsers.Models.BookdetailsModel;
import com.hp.bookaholic.EndUsers.Models.ReturnBookModel;
import com.hp.bookaholic.EndUsers.Models.ReturnDetailsModel;
import com.hp.bookaholic.R;
import com.hp.bookaholic.Retro.Retro;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReturnBookFragmnet extends Fragment {


    private ArcView rootlay;
    private ImageView bookimg;
    private TextView bookname;
    private TextView author;
    private TextView lendDays;
    private TextView extralendDays;
    private TextView rent;
    private TextView postaladd;
    private TextView accNo;
    private TextView ifsc;
    private TextView branch;
    private TextView total;

    private MaterialButton returnbtn;
    private AppPreferences appPreferences;
    BookdetailsModel bookdetailsModel;

    public ReturnBookFragmnet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_return_book_fragmnet, container, false);

        initView(root);

        appPreferences = AppPreferences.getInstance(getContext(), getResources().getString(R.string.app_name));
        Log.e("BOOK ID", appPreferences.getData("returnbook_id"));
        new Retro().getApi().BOOKDETAILS_MODEL_CALL(appPreferences.getData("returnbook_id")).enqueue(new Callback<BookdetailsModel>() {
            @Override
            public void onResponse(Call<BookdetailsModel> call, Response<BookdetailsModel> response) {
                bookdetailsModel = response.body();

                appPreferences.saveData("owner_id", bookdetailsModel.getBook_Details().get(0).getUser_id());
                if (bookdetailsModel != null) {
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
                    } else {
                        Toast.makeText(getContext(), "Book not found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<BookdetailsModel> call, Throwable t) {
                Toast.makeText(getContext(), "API FAILURE " + t, Toast.LENGTH_SHORT).show();

            }
        });

        returnbtn.setOnClickListener(v -> {
            new Retro().getApi().returnBookCall(appPreferences.getData("returnbook_id")).enqueue(new Callback<ReturnBookModel>() {
                @Override
                public void onResponse(Call<ReturnBookModel> call, Response<ReturnBookModel> response) {
                    ReturnBookModel returnBookModel=response.body();
                    if(returnBookModel.getStatus().equalsIgnoreCase("Updated  Successfully"))
                    {
                        Toast.makeText(getContext(), "Book set for return.Pass the book to admin after payment.", Toast.LENGTH_LONG).show();

                         total.setText("Payable amount :  "+returnBookModel.getPay_amount()+" RS");
                         total.setVisibility(View.VISIBLE);
                         returnbtn.setVisibility(View.GONE);


                    }else {
                        Toast.makeText(getContext(), "OOPS !, "+returnBookModel.getStatus(), Toast.LENGTH_SHORT).show();
                    }



                }

                @Override
                public void onFailure(Call<ReturnBookModel> call, Throwable t) {
                    Toast.makeText(getContext(), "ReturnBookModel api fail"+t, Toast.LENGTH_SHORT).show();
                }
            });
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
        postaladd = root.findViewById(R.id.postaladd);
        accNo = root.findViewById(R.id.accNo);
        ifsc = root.findViewById(R.id.ifsc);
        branch = root.findViewById(R.id.branch);
        returnbtn = root.findViewById(R.id.returnbtn);
        total=root.findViewById(R.id.total);
    }
}
