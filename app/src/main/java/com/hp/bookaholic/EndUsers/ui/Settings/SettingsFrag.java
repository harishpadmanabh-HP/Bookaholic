package com.hp.bookaholic.EndUsers.ui.Settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.bookaholic.R;

public class SettingsFrag extends Fragment {


    private TextView logout;
    private TextView admin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.settingsfrag, container, false);
        initView(root);
        logout.setOnClickListener(v -> {
            AppPreferences appPreferences = AppPreferences.getInstance(getContext(), getResources().getString(R.string.app_name));
            appPreferences.clearData();
        });
        admin.setOnClickListener(v -> {

        });
        return root;
    }

    private void initView(View root) {
        logout = root.findViewById(R.id.logout);
        admin = root.findViewById(R.id.admin);
    }
}