package com.hp.bookaholic;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.hp.bookaholic.Utils.Utils;

import java.util.List;

public class Login extends AppCompatActivity {

    private CardView cardView;
    private TextInputEditText phone;
    private TextInputEditText pass;
    private MaterialButton login;
    private MaterialButton signup;
    private ImageView bookicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        View rootView = findViewById(android.R.id.content);

        //conecting all TextInputEditText as list
        final List<TextInputEditText> textInputEditTexts = Utils.findViewsWithType(
                rootView, TextInputEditText.class);
        signup.setOnClickListener(view -> {
            Intent i = new Intent(Login.this, SignUP.class);
            //finish();
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(Login.this,
                            bookicon,
                            ViewCompat.getTransitionName(bookicon));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                startActivity(i, options.toBundle());
            } else {
                startActivity(i);
            }
        });

        login.setOnClickListener(v -> {
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
            if(noErrors){
                startActivity(new Intent(Login.this,HomePage.class));
            }
        });

    }

    private void initView() {
        cardView = (CardView) findViewById(R.id.cardView);
        phone = (TextInputEditText) findViewById(R.id.phone);
        pass = (TextInputEditText) findViewById(R.id.pass);
        login = (MaterialButton) findViewById(R.id.login);
        signup = (MaterialButton) findViewById(R.id.signup);
        bookicon = (ImageView) findViewById(R.id.bookicon);
    }
}
