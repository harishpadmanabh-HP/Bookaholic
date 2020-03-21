package com.hp.bookaholic.EndUsers.tabfrags.mybooks;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.bookaholic.EndUsers.Adapter.MyBookAdapter;
import com.hp.bookaholic.EndUsers.Models.MyBooksModel;
import com.hp.bookaholic.R;
import com.hp.bookaholic.Retro.Retro;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyBooksFragment extends Fragment {


    private TextView textHome;
    private RecyclerView rViewbooklist;
    private AppPreferences appPreferences;

    public MyBooksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_books, container, false);
        initView(root);
        appPreferences = AppPreferences.getInstance(getContext(), getResources().getString(R.string.app_name));

        new Retro().getApi().myBooksCall(appPreferences.getData("id")).enqueue(new Callback<MyBooksModel>() {
            @Override
            public void onResponse(Call<MyBooksModel> call, Response<MyBooksModel> response) {

                MyBooksModel myBooksModel=response.body();
                if(myBooksModel.getStatus().equalsIgnoreCase("success"))
                {

                    rViewbooklist.setLayoutManager(new GridLayoutManager(getContext(),2));
                    rViewbooklist.setAdapter(new MyBookAdapter(myBooksModel,getActivity()));

                }else
                {
                    Toast.makeText(getContext(), "You have not got any books yet !!", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<MyBooksModel> call, Throwable t) {
                Toast.makeText(getContext(), "MyBooksModel api fail "+t, Toast.LENGTH_SHORT).show();
            }
        });


        return root;

    }

    private void initView(View root) {
        textHome = root.findViewById(R.id.text_home);
        rViewbooklist = root.findViewById(R.id.r_viewbooklist);
    }
}
