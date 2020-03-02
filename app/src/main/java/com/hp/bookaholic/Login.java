package com.hp.bookaholic;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

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
