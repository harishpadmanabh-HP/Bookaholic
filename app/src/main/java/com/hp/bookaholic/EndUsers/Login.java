package com.hp.bookaholic.EndUsers;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.bookaholic.EndUsers.Models.LoginModel;
import com.hp.bookaholic.R;
import com.hp.bookaholic.Retro.Retro;
import com.hp.bookaholic.Utils.Utils;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;
import java.util.function.Function;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private CardView cardView;
    private TextInputEditText phone;
    private TextInputEditText pass;
    private MaterialButton login;
    private MaterialButton signup;
    private ImageView bookicon;
    String input_phone;
    String input_password;
    String check;
    LoginModel  loginModel;

    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        View rootView = findViewById(android.R.id.content);
        appPreferences = AppPreferences.getInstance(getApplicationContext(), getResources().getString(R.string.app_name));
        requestStoragePermission();
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
               input_phone=phone.getText().toString();
               input_password=pass.getText().toString();
                Retro retro=new Retro();
                retro.getApi().LOGIN_MODEL_CALL(phone.getText().toString(),pass.getText().toString()).enqueue(new Callback<LoginModel>() {
                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    loginModel=response.body();
                     if(loginModel.getStatus().equalsIgnoreCase("Success")){
                         appPreferences.saveData("id",loginModel.getUser_data().getUser_id());
                         appPreferences.saveData("in_name",loginModel.getUser_data().getName());
                         appPreferences.saveData("in_email",loginModel.getUser_data().getEmail());
                         appPreferences.saveData("in_phone",loginModel.getUser_data().getPhone());
                         appPreferences.saveData("in_pass",loginModel.getUser_data().getPassword());
                         appPreferences.saveData("in_location",loginModel.getUser_data().getPostal_address());


                         Utils.logDetails("id",loginModel.getUser_data().getUser_id());

                         Toast.makeText(Login.this, "Success", Toast.LENGTH_SHORT).show();

                         startActivity(new Intent(Login.this,HomePage.class));}
                     else {
                         Toast.makeText(Login.this,loginModel.getStatus(), Toast.LENGTH_SHORT).show();
                     }
                    }

                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {

                    }
                });

            }
        });

    }
    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }
    private void requestStoragePermission() {
        Dexter.withActivity(Login.this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE

                       )
                .withListener(new MultiplePermissionsListener() {

                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

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
