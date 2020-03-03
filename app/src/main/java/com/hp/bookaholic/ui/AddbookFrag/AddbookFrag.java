package com.hp.bookaholic.ui.AddbookFrag;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hp.bookaholic.BuildConfig;
import com.hp.bookaholic.Models.AddBookModel;
import com.hp.bookaholic.R;
import com.hp.bookaholic.Retro.Retro;
import com.hp.bookaholic.Utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class AddbookFrag extends Fragment {


    private TextInputEditText bookname;
    private TextInputEditText author;
    private TextInputEditText lendDays;
    private TextInputEditText extraDays;
    private TextInputEditText address;
    private TextInputEditText accNO;
    private TextInputEditText ifsc;
    private TextInputLayout outlinedTextField;
    private TextInputEditText branch;
    private Button addpic;
    private Button requestbook;
    File filedp;
    private File imgFile;
    private boolean isPhototaken;

    private String pictureFilePath;
    private MultipartBody.Part filePart;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.addbookfrag, container, false);

        initView(root);
        View rootView = root.findViewById(android.R.id.content);

        //conecting all TextInputEditText as list
        final List<TextInputEditText> textInputEditTexts = Utils.findViewsWithType(
                root, TextInputEditText.class);

        addpic.setOnClickListener(v -> {
          selectImage();
        });



        requestbook.setOnClickListener((View v) -> {
            Log.e("Data","CLICKED");

            boolean noErrors = true;
            for (TextInputEditText textInputEditText : textInputEditTexts) {
                //get strings from each edittext
                String editTextString = textInputEditText.getText().toString();
                Log.e("Data",editTextString);
                if (editTextString.isEmpty()) {
                    textInputEditText.setError(("please fill this field"));
                    noErrors = false;
                } else {
                    textInputEditText.setError(null);
                }
            }
            if(noErrors  && isPhototaken){//no null fields and pic chosen

                Log.e("INSIDE", String.valueOf(noErrors)+isPhototaken);
                RequestBody booknameRB = RequestBody.create(MediaType.parse("text/plain"), bookname.getText().toString());
                RequestBody authorRB = RequestBody.create(MediaType.parse("text/plain"), author.getText().toString());
                RequestBody lenddaysRB = RequestBody.create(MediaType.parse("text/plain"), lendDays.getText().toString());
                RequestBody extraRB = RequestBody.create(MediaType.parse("text/plain"), extraDays.getText().toString());
                RequestBody postaladdRB = RequestBody.create(MediaType.parse("text/plain"), address.getText().toString());
                RequestBody accnoRB = RequestBody.create(MediaType.parse("text/plain"), accNO.getText().toString());
                RequestBody ifscRB = RequestBody.create(MediaType.parse("text/plain"), ifsc.getText().toString());
                RequestBody branchRB = RequestBody.create(MediaType.parse("text/plain"), branch.getText().toString());
                RequestBody uidRB = RequestBody.create(MediaType.parse("text/plain"),"1");

                try {
                    filePart = MultipartBody.Part.createFormData("avatar", imgFile.getName(), RequestBody.create(MediaType.parse("image/*"), imgFile));

                }catch (Exception e)
                {
                    Toast.makeText(getContext(), "No image  file ", Toast.LENGTH_SHORT).show();
                }

                new Retro().getApi().ADD_BOOK_MODEL_CALL(booknameRB,
                        authorRB,uidRB,lenddaysRB,extraRB,postaladdRB,accnoRB,ifscRB,branchRB,filePart).enqueue(new Callback<AddBookModel>() {
                    @Override
                    public void onResponse(Call<AddBookModel> call, Response<AddBookModel> response) {
                        AddBookModel addBookModel = response.body();
                        if(addBookModel.getStatus().equalsIgnoreCase("success")){
                            Snackbar.make(requestbook,"Book requested for approval", BaseTransientBottomBar.LENGTH_LONG).show();
                        }
                        else {
                            Snackbar.make(requestbook,""+addBookModel.getMessage(), BaseTransientBottomBar.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<AddBookModel> call, Throwable t) {
                        Toast.makeText(getContext(), "API FAIL"+t, Toast.LENGTH_SHORT).show();
                    }
                });





            }else
            {
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }

        });


        return root;
    }

    private void initView(View root) {
        bookname = root.findViewById(R.id.bookname);
        author = root.findViewById(R.id.author);
        lendDays = root.findViewById(R.id.lendDays);
        extraDays = root.findViewById(R.id.extraDays);
        address = root.findViewById(R.id.address);
        accNO = root.findViewById(R.id.accNO);
        ifsc = root.findViewById(R.id.ifsc);
        outlinedTextField = root.findViewById(R.id.outlinedTextField);
        branch = root.findViewById(R.id.branch);
        addpic = root.findViewById(R.id.addpic);
        requestbook = root.findViewById(R.id.requestbook);
    }
    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    takePicture();
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                //File for upload
                imgFile = new File(pictureFilePath);
                if (imgFile.exists()) {
                    isPhototaken = true;
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(),
                            bitmapOptions);

                    Toast.makeText(getContext(), "Photo Chosed for upload", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "imgFile not exist", Toast.LENGTH_SHORT).show();

                }
            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getActivity().getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.e("path************", picturePath+"");
            }
        }
    }
    private void takePicture() {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, true);
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            //startActivityForResult(cameraIntent, REQUEST_PICTURE_CAPTURE);

            File pictureFile = null;
            try {
                pictureFile = getPictureFile();
            } catch (Exception ex) {
                Toast.makeText(getContext(),
                        "Photo file can't be created, please try again",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            if (pictureFile != null) {
                Uri photoURI = FileProvider.getUriForFile(Objects.requireNonNull(getActivity()),
                        BuildConfig.APPLICATION_ID + ".provider", pictureFile);


                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(cameraIntent, 1);
            }
        }
    }
    private File getPictureFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String pictureFile = "Bookaholic" + timeStamp;
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(pictureFile, ".jpg", storageDir);
        pictureFilePath = image.getAbsolutePath();
        return image;
    }


}
