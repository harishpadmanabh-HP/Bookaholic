package com.hp.bookaholic.admin.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hp.bookaholic.R;
import com.hp.bookaholic.Retro.Retro;
import com.hp.bookaholic.admin.adapters.LendReqAdapter;
import com.hp.bookaholic.admin.modelsAdmin.Lend_Requests_Model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LendRequestFrag extends Fragment {

    private RecyclerView lenreqRV;

    public LendRequestFrag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_lend_request, container, false);
        initView(root);
        new Retro().getApi().LEND_REQUESTS_MODEL_CALL().enqueue(new Callback<Lend_Requests_Model>() {
            @Override
            public void onResponse(Call<Lend_Requests_Model> call, Response<Lend_Requests_Model> response) {
              Lend_Requests_Model lend_requests_model=response.body();
              if(lend_requests_model.getStatus().equalsIgnoreCase("success")) {

                  lenreqRV.setLayoutManager(new GridLayoutManager(getContext(), 2));
                  lenreqRV.setAdapter(new LendReqAdapter(lend_requests_model, getContext()));

              }else
              {
                  Toast.makeText(getContext(), "No books found", Toast.LENGTH_SHORT).show();
              }


            }

            @Override
            public void onFailure(Call<Lend_Requests_Model> call, Throwable t) {

                Toast.makeText(getContext(), "API "+t, Toast.LENGTH_SHORT).show();

            }
        });


        return root;
    }


    private void initView(View root) {
        lenreqRV = root.findViewById(R.id.lenreqRV);
    }
}
