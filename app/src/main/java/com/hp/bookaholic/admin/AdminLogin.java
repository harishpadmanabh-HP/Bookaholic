package com.hp.bookaholic.admin;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hp.bookaholic.R;
import com.hp.bookaholic.Retro.Retro;
import com.hp.bookaholic.Utils.Utils;
import com.hp.bookaholic.admin.modelsAdmin.AdminLoginModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminLogin extends AppCompatActivity {

    private ImageView bookicon;
    private CardView cardView;
    private TextInputLayout outlinedTextField;
    private TextInputEditText phone;
    private TextInputEditText pass;
    private MaterialButton login;
    private MaterialButton signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        initView();
        View rootView = findViewById(android.R.id.content);

        //conecting all TextInputEditText as list
        final List<TextInputEditText> textInputEditTexts = Utils.findViewsWithType(
                rootView, TextInputEditText.class);


        login.setOnClickListener(v -> {
            //checking null values for each edittesxt
            View view=v;
            boolean noErrors = true;
            for (TextInputEditText textInputEditText : textInputEditTexts) {
                //get strings from each edittext
                String editTextString = textInputEditText.getText().toString();
                if (editTextString.isEmpty()) {
                    textInputEditText.setError(("please fill this field"));
                    noErrors = false;
                } else {
                    textInputEditText.setError(null);
                }
            }
            if(noErrors){

                new Retro().getApi().ADMIN_LOGIN_MODEL_CALL(phone.getText().toString(),
                        pass.getText().toString()).enqueue(new Callback<AdminLoginModel>() {
                    @Override
                    public void onResponse(Call<AdminLoginModel> call, Response<AdminLoginModel> response) {
                        AdminLoginModel adminLoginModel=response.body();
                        if(adminLoginModel.getStatus().equalsIgnoreCase("success")){
                            Toast.makeText(AdminLogin.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(AdminLogin.this,adminLoginModel.getStatus() , Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AdminLoginModel> call, Throwable t) {

                    }
                });





            }else
            {
                Toast.makeText(this, "Fill All fields", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void initView() {
        bookicon = findViewById(R.id.bookicon);
        cardView = findViewById(R.id.cardView);
        outlinedTextField = findViewById(R.id.outlinedTextField);
        phone = findViewById(R.id.phone);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        signup.setVisibility(View.INVISIBLE);
        signup.setEnabled(false);
    }
}
