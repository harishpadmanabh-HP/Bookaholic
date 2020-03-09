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
import com.hp.bookaholic.admin.adapters.ApprovalList_Adapter;
import com.hp.bookaholic.admin.modelsAdmin.Booklists_For_Approval_Model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Approve_books_frag extends Fragment {


    private RecyclerView approvalBooksRV;
     Booklists_For_Approval_Model booklists_for_approval_model;

    public Approve_books_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_approve_books_frag, container, false);
        initView(root);

        new Retro().getApi().BOOKLISTS_FOR_APPROVAL_MODEL_CALL().enqueue(new Callback<Booklists_For_Approval_Model>() {
            @Override
            public void onResponse(Call<Booklists_For_Approval_Model> call, Response<Booklists_For_Approval_Model> response) {
                booklists_for_approval_model=response.body();
                if(booklists_for_approval_model.getStatus().equalsIgnoreCase("success")){
                    approvalBooksRV.setLayoutManager(new GridLayoutManager(getContext(),2));
                    approvalBooksRV.setAdapter(new ApprovalList_Adapter(getContext(),booklists_for_approval_model));

                }else
                {
                    Toast.makeText(getContext(), "No books found !", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<Booklists_For_Approval_Model> call, Throwable t) {
                Toast.makeText(getContext(), "API FAILURE"+t, Toast.LENGTH_SHORT).show();

            }
        });

        return root;
    }

    private void initView(View root) {
        approvalBooksRV = root.findViewById(R.id.approvalBooksRV);
    }
}
