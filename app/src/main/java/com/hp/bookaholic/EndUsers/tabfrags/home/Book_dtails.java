package com.hp.bookaholic.EndUsers.tabfrags.home;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.github.florent37.shapeofview.shapes.ArcView;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.bookaholic.EndUsers.Models.Avail_Set_model;
import com.hp.bookaholic.EndUsers.Models.BookdetailsModel;
import com.hp.bookaholic.EndUsers.Models.BuyBook_Model;
import com.hp.bookaholic.R;
import com.hp.bookaholic.Retro.Retro;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    private TextView rent;
    private Button buy;


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

        buy.setOnClickListener(v -> {

            Date today = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            String dateToStr = format.format(today);
            System.out.println(dateToStr);
            Log.e("TODAY", dateToStr);
            Log.e("BOOK ID", bookdetailsModel.getBook_Details().get(0).getBook_id());
            Log.e("PRICE", bookdetailsModel.getBook_Details().get(0).getPrice());


            new Retro().getApi().BUY_BOOK_MODEL_CALL("1",
                    bookdetailsModel.getBook_Details().get(0).getBook_id(),
                    dateToStr,
                    bookdetailsModel.getBook_Details().get(0).getPrice()).enqueue(new Callback<BuyBook_Model>() {
                @Override
                public void onResponse(Call<BuyBook_Model> call, Response<BuyBook_Model> response) {

                    BuyBook_Model buyBook_model = response.body();
                    Toast.makeText(getContext(), buyBook_model.getStatus(), Toast.LENGTH_SHORT).show();
                    if (buyBook_model.getStatus().equalsIgnoreCase("Inserted successfuly")) {
                        Toast.makeText(getContext(), "Request forwarded to admin for further processing", Toast.LENGTH_SHORT).show();

                        new Retro().getApi().AVAIL_SET_MODEL_CALL(bookdetailsModel.getBook_Details().get(0).getBook_id()).enqueue(new Callback<Avail_Set_model>() {
                            @Override
                            public void onResponse(Call<Avail_Set_model> call, Response<Avail_Set_model> response) {
                                Avail_Set_model avail_set_model = response.body();
                                if (avail_set_model.getStatus().equalsIgnoreCase("Updated  Successfully")) {

                                    Navigation.findNavController(v).navigate(R.id.action_book_dtails_to_navigation_home);

                                    Toast.makeText(getContext(), "Request Will be processed within 2 days", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(getContext(), "" + avail_set_model.getStatus(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Avail_Set_model> call, Throwable t) {
                                Toast.makeText(getContext(), "API APRROVAL" + t, Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else {
                        Toast.makeText(getContext(), "Ooops! Something went Wrong", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<BuyBook_Model> call, Throwable t) {
                    Toast.makeText(getContext(), "API BUY BOOK" + t, Toast.LENGTH_SHORT).show();

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
        postaladd = root.findViewById(R.id.postaladd);
        accNo = root.findViewById(R.id.accNo);
        ifsc = root.findViewById(R.id.ifsc);
        branch = root.findViewById(R.id.branch);
        rent = root.findViewById(R.id.rent);
        buy = root.findViewById(R.id.buy);

    }
}


