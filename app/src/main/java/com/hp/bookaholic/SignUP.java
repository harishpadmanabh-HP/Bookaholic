package com.hp.bookaholic;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hp.bookaholic.Utils.Utils;

import java.util.List;

public class SignUP extends AppCompatActivity {

    private ImageView bookicon;
    private TextInputEditText name;
    private TextInputEditText email;
    private TextInputLayout outlinedTextField;
    private TextInputEditText phone;
    private TextInputEditText location;
    private TextInputEditText pass;
    private MaterialButton signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
        View rootView = findViewById(android.R.id.content);

        //conecting all TextInputEditText as list
        final List<TextInputEditText> textInputEditTexts = Utils.findViewsWithType(
                rootView, TextInputEditText.class);

        signup.setOnClickListener(view -> {
            //checking null values for each edittesxt
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
            if(noErrors)
            {
                //fields are non null
                gotoLogin();


            }


        } );

    }

    private void gotoLogin() {
        Intent i = new Intent(SignUP.this, Login.class);
        //finish();
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(SignUP.this,
                        bookicon,
                        ViewCompat.getTransitionName(bookicon));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(i, options.toBundle());
        } else {
            startActivity(i);
        }
    }

    private void initView() {
        bookicon = (ImageView) findViewById(R.id.bookicon);
        name = (TextInputEditText) findViewById(R.id.name);
        email = (TextInputEditText) findViewById(R.id.email);
        outlinedTextField = (TextInputLayout) findViewById(R.id.outlinedTextField);
        phone = (TextInputEditText) findViewById(R.id.phone);
        location = (TextInputEditText) findViewById(R.id.location);
        pass = (TextInputEditText) findViewById(R.id.pass);
        signup = (MaterialButton) findViewById(R.id.signup);
    }
}
