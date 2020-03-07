package com.hp.bookaholic.EndUsers.tabfrags.mybooks;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hp.bookaholic.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyBooksFragment extends Fragment {


    public MyBooksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_my_books, container, false);


        return root;

    }

}
