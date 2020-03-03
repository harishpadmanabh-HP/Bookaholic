package com.hp.bookaholic.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.hp.bookaholic.Adapter.Adapter;
import com.hp.bookaholic.Models.BooklistModel;
import com.hp.bookaholic.R;
import com.hp.bookaholic.Retro.Retro;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    BooklistModel booklistModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.r_viewbooklist);
        Retro retro = new Retro();
        retro.getApi().BOOKLIST_MODEL_CALL().enqueue(new Callback<BooklistModel>() {
            @Override
            public void onResponse(Call<BooklistModel> call, Response<BooklistModel> response) {
                booklistModel = response.body();
                if(booklistModel.getStatus().equalsIgnoreCase("success")) {
                    Log.e("RESPONSE", booklistModel.getStatus());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);

                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    recyclerView.setAdapter(new Adapter(getContext(), booklistModel));
                }else
                {
                    Toast.makeText(getContext(), " Sorry ! No Books Found ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<BooklistModel> call, Throwable t) {
                Toast.makeText(getContext(), "API FAIL "+t, Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }
}