package com.hp.bookaholic.EndUsers.tabfrags.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.bookaholic.EndUsers.Adapter.BookList_Adapter;
import com.hp.bookaholic.EndUsers.Models.AvailableBookListModel;
import com.hp.bookaholic.EndUsers.Models.BooklistModel;
import com.hp.bookaholic.R;
import com.hp.bookaholic.Retro.Retro;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    private AppPreferences appPreferences;
    private AvailableBookListModel availableBookListModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.r_viewbooklist);
        appPreferences = AppPreferences.getInstance(getContext(), getResources().getString(R.string.app_name));
        new Retro().getApi().AVAILABLE_BOOK_LIST_MODEL_CALL().enqueue(new Callback<AvailableBookListModel>() {
            @Override
            public void onResponse(Call<AvailableBookListModel> call, Response<AvailableBookListModel> response) {
                availableBookListModel=response.body();
                if(availableBookListModel.getStatus().equalsIgnoreCase("success")){

                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                      recyclerView.setAdapter(new BookList_Adapter(availableBookListModel,getContext()));

                }else
                {
                    Toast.makeText(getContext(), "No Books Available. Come back later!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AvailableBookListModel> call, Throwable t) {
                Toast.makeText(getContext(), "API FAILURE :"+t, Toast.LENGTH_SHORT).show();

            }
        });

        return root;
    }
}