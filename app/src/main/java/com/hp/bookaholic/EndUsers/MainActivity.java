package com.hp.bookaholic.EndUsers;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import com.hp.bookaholic.R;

public class MainActivity extends AppCompatActivity {

    private ImageView booklogo;
    private TextView splashtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //Fullscreen with status bar-Use this`
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

    }

    private void initView() {
        booklogo = (ImageView) findViewById(R.id.booklogo);
        splashtext = (TextView) findViewById(R.id.splashtext);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.scalezoom);
        booklogo.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                new Handler().postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        Intent i = new Intent(MainActivity.this, Login.class);
                        //finish();
                        ActivityOptionsCompat options = ActivityOptionsCompat.
                                makeSceneTransitionAnimation(MainActivity.this,
                                        booklogo,
                                        ViewCompat.getTransitionName(booklogo));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            startActivity(i, options.toBundle());
                        }else
                        {
                            startActivity(i);
                        }
                    }
                }, 1000);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
