package com.mindiii.lasross.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.crashlytics.android.Crashlytics;
import com.mindiii.lasross.R;
import com.mindiii.lasross.helper.Constant;
import com.mindiii.lasross.helper.Util;
import com.mindiii.lasross.loginregistration.LoginActivity;
import com.mindiii.lasross.picker.ImagePicker;
import com.mindiii.lasross.sessionNew.Session;
import com.mindiii.lasross.sessionNew.UserInfo;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;

import io.fabric.sdk.android.Fabric;

public class EditProfileActivity extends AppCompatActivity {

    ImageView ivPassword, ivProfileImage;
    Button btnCrash;
    RelativeLayout rvImage;
    Uri uri;
    Bitmap imageBitmap;
    String strImage, mediaPath;
    EditText etName, etAddress;
    TextView tvUpdate;
    Session session;
    Pattern pattern;
    ProgressDialog progressDialog;
    String fullName = "";
    String mCurrentPhotoPath = "";
    private Bitmap profileImageBitmap;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_activty);

        Fabric.with(this, new Crashlytics());
        logUser();

        ivPassword = findViewById(R.id.ivPassword);
        ivProfileImage = findViewById(R.id.ivProfileImage);

        linearLayout = findViewById(R.id.linearLayout);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the input method manager
                InputMethodManager inputMethodManager = (InputMethodManager)
                        view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                // Hide the soft keyboard
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
            }
        });

        //pref = new SharedPref(this);
        session = new Session(this);

        fullName = session.getFirst_name() + " " + session.getLast_Name();

        progressDialog = new ProgressDialog(this);

        pattern = Pattern.compile("^[a-zA-Z ]+$");

        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        //etName.setText(pref.getFirst_name()+" "+pref.getLast_name());
        //etName.setText(pref.getDescription());
        etName.setText(session.getFirst_name() + " " + session.getLast_Name());
        etAddress.setText(session.getDescription());

        tvUpdate = findViewById(R.id.tvUpdate);

        rvImage = findViewById(R.id.rvImage);

        btnCrash = findViewById(R.id.btnCrash);
        btnCrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throw new RuntimeException("This is a crash");
            }
        });

        ivPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChangePasswordDialog();
            }
        });

        rvImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPermissionAndPicImage();
            }
        });

        if (!session.getProfile_image().isEmpty()) {
            Picasso.with(this)
                    .load(session.getProfile_image())
                    .resize(600, 600)
                    .into(ivProfileImage);
        }

        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String address = etAddress.getText().toString();

                try  {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {

                }

                if (isValidName(name) && isValidAddress(address)) {
                    validateData(name, address);
                } else if (!isValidName(name)) {
                    etName.setError("Invalid name");
                } else if (!isValidAddress(address)) {
                    etAddress.setError("Invalid pass");
                }
            }
        });
    }

    private void validateData(final String name, final String address) {

        progressDialog.show();
        progressDialog.setMessage("loading.....");

        AndroidNetworking.post("http://dev.lasross.com/api/service/update_profile")
                .addHeaders("authToken", session.getCookie())
                .addBodyParameter("display_name", name)
                .addBodyParameter("description", address)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("response is", response.toString());
                        try {
                            String status = response.getString("status");
                            if (status.equals("ok")) {
                                progressDialog.dismiss();

                                JSONObject object = response.getJSONObject("user");
                                UserInfo userInfo = new UserInfo();

                                userInfo.cookie = session.getCookie();
                                Log.v("userInfo", userInfo.cookie);
                                //userInfo.userId = object.getInt("id");
                                userInfo.userId = Integer.toString(object.getInt("id"));
                                userInfo.first_name = object.getString("firstname");
                                userInfo.last_name = object.getString("lastname");
                                userInfo.email = object.getString("email");
                                userInfo.profile_image = object.getString("avatar_url_full");
                                userInfo.description = object.getString("description");

                                session.createSession(userInfo);

                                Intent intent = new Intent(EditProfileActivity.this, UserProfileActivity26.class);
                                intent.putExtra("address", address);
                                startActivity(intent);

                                finish();
                                Toast.makeText(EditProfileActivity.this, "updated", Toast.LENGTH_LONG).show();
                            } else if (status.equals("error")) {
                                progressDialog.dismiss();
                                String error = response.getString("error");
                                Toast.makeText(EditProfileActivity.this, error, Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Util.parseError(EditProfileActivity.this,anError);
                        Toast.makeText(EditProfileActivity.this, anError.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private boolean isValidName(String lName) {
        return pattern.matcher(lName).matches();
    }

    private boolean isValidAddress(String address) {
        return address.length() > 1;
    }

    private void openChangePasswordDialog() {

        final Dialog dialog = new Dialog(EditProfileActivity.this);//,android.R.style.Theme_Dialog);
        dialog.setContentView(R.layout.reset_password_dialog_artboard_35);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final EditText etOldPass, etNewPass, etConfirmPass;
        TextView tvDone;
        ImageView ivClose;

        etOldPass = dialog.findViewById(R.id.etOldPass);
        etNewPass = dialog.findViewById(R.id.etNewPass);
        etConfirmPass = dialog.findViewById(R.id.etConfirmPass);

        tvDone = dialog.findViewById(R.id.tvDone);

        ivClose = dialog.findViewById(R.id.ivClose);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = etOldPass.getText().toString();
                String newPass = etNewPass.getText().toString();
                String confPass = etConfirmPass.getText().toString();

                if (isValidOldPass(oldPass) && isValidNewPass(newPass) && isValidConfPass(newPass, confPass)) {
                    validateResetPasswordData(oldPass, newPass, confPass);
                } else if (!isValidOldPass(oldPass)) {
                    etOldPass.setError("Enter valid password");
                } else if (!isValidNewPass(newPass)) {
                    etNewPass.setError("Enter valid password");
                } else if (!isValidConfPass(newPass, confPass)) {
                    etConfirmPass.setError("New and Confirm password should be same");
                }
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        lp.gravity = Gravity.TOP;
        lp.windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setAttributes(lp);

        dialog.setCanceledOnTouchOutside(false);

        dialog.show();
    }

    private boolean isValidOldPass(String oldPass) {
        return oldPass.length() > 1;
    }

    private boolean isValidNewPass(String newPass) {
        return newPass.length() > 1;
    }

    private boolean isValidConfPass(String newPass, String confPass) {
        return newPass.equals(confPass);
    }

    private void validateImageData() {
        File file = new File(strImage);

        AndroidNetworking.post("http://dev.lasross.com/api/service/update_user_avatar")
                .addHeaders("authToken", session.getCookie())
                .addBodyParameter("image", strImage)
                .setTag(this)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        /*String status = response.getString("status");
                        String user_id = response.getString("user_id");*/

                        String img = null;
                        try {
                            img = response.getString("avatar_url_full");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("Response for image is", String.valueOf(response));
                        session.setProfile_image(img);
                    }

                    @Override
                    public void onError(ANError error) {
                        Log.e("Response for image is", String.valueOf(error));
                    }
                });
    }

    private void validateResetPasswordData(String oldPass, String newPass, String confPass) {
        AndroidNetworking.post("http://dev.lasross.com/api/service/change_password")
                .addHeaders("authToken", session.getCookie())
                .addBodyParameter("old_password", oldPass)
                .addBodyParameter("new_password", newPass)
                .setTag(this)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        /*String status = response.getString("status");
                        String user_id = response.getString("user_id");*/
                        Log.e("The response is ", String.valueOf(response));
                        String status = null;
                        try {
                            status = response.getString("status");
                            if (status.equals("ok")) {
                                String message = response.getString("message");
                                Toast.makeText(EditProfileActivity.this, message, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(EditProfileActivity.this, LoginActivity.class));
                            } else if (status.equals("error")) {
                                Toast.makeText(EditProfileActivity.this, "wrong password", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(EditProfileActivity.this, "wrong password", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void openChooseImageDialog() {

        final Dialog dialog = new Dialog(EditProfileActivity.this);//,android.R.style.Theme_Dialog);
        dialog.setContentView(R.layout.picture_selection_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.setTitle("Add Photo!");

        TextView tvClose, tvCamera, tvGallery, tvCancel;

        tvClose = dialog.findViewById(R.id.tvClose);
        tvCamera = dialog.findViewById(R.id.tvCamera);
        tvGallery = dialog.findViewById(R.id.tvGallery);
        tvCancel = dialog.findViewById(R.id.tvCancel);

        if (tvClose.getVisibility() == View.VISIBLE) {
            dialog.setCancelable(false);
        }

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPermissionAndPicImage();
                dialog.dismiss();
            }
        });

        tvGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPermissionAndPicImage();
                dialog.dismiss();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == 234) {
//Bitmap bitmap = ImagePicker.getImageFromResult(this, requestCode, resultCode, data);
                Uri imageUri = ImagePicker.getImageURIFromResult(this, requestCode, resultCode, data);
                Bitmap bitmap = ImagePicker.getImageFromResult(this, requestCode, resultCode, data);
                if (imageUri != null) {

                    CropImage.activity(imageUri)
                            .setAspectRatio(1, 1)
                            .setFixAspectRatio(true)
                            .start(this);

                    Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 500, 500, false);
                    strImage = ConvertBitmapToString(resizedBitmap);

                    ivProfileImage.setImageBitmap(bitmap);

                    validateImageData();


                } else {
                    //showToast(getString(R.string.msg_some_thing_went_wrong));
                }

            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                try {
                    if (result != null)
                        profileImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), result.getUri());
                    assert result != null;
                    uri = result.getUri();

                    if (profileImageBitmap != null) {
                        ivProfileImage.setImageBitmap(profileImageBitmap);

                        Bitmap resizedBitmap = Bitmap.createScaledBitmap(profileImageBitmap, 500, 500, false);
                        strImage = ConvertBitmapToString(resizedBitmap);

                        validateImageData();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                if (data != null) {

                    final String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    File file = new File(String.valueOf(filePathColumn));
                    uri = data.getData();


                    Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursor.getString(columnIndex);
                    Picasso.with(EditProfileActivity.this)
                            .load(uri)
                            .resize(600, 600)
                            .into(ivProfileImage);
                }
                *//*Bundle extras = data.getExtras();
                imageBitmap = (Bitmap) extras.get("data");*//*
                try {
                    imageBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                    Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, 500, 500, false);
                    strImage = ConvertBitmapToString(resizedBitmap);

                    validateImageData();

                    CropImage.activity(uri).
                            setCropShape(CropImageView.CropShape.RECTANGLE).
                            setAspectRatio(4, 4).start(this);

                    //Log.e("ye hai image ",strImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {

                if (data != null) {
                    uri = data.getData();
                    Picasso.with(EditProfileActivity.this)
                            .load(uri)
                            .resize(600, 600)
                            .into(ivProfileImage);

                    try {
                        imageBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, 500, 500, false);
                        strImage = ConvertBitmapToString(resizedBitmap);

                        validateImageData();
                        //Log.e("ye hai image ",strImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                   *//* try {
                        imageBitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
                        //ivImage.setImageBitmap(imageBitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*//*

                    CropImage.activity(uri).setCropShape(CropImageView.CropShape.RECTANGLE).setAspectRatio(4, 4).start(this);
                }
            }
        }
    }*/





    private File createImageFile() throws IOException {
// Create an image file name
        @SuppressLint("SimpleDateFormat")
        String timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

// Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    Bitmap ShrinkBitmap(String file, int width, int height) {

        BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
        bmpFactoryOptions.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(file, bmpFactoryOptions);

        int heightRatio = (int) Math.ceil(bmpFactoryOptions.outHeight / (float) height);
        int widthRatio = (int) Math.ceil(bmpFactoryOptions.outWidth / (float) width);

        if (heightRatio > 1 || widthRatio > 1) {
            if (heightRatio > widthRatio) {
                bmpFactoryOptions.inSampleSize = heightRatio;
            } else {
                bmpFactoryOptions.inSampleSize = widthRatio;
            }

            bmpFactoryOptions.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(file, bmpFactoryOptions);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
            byte[] imageInByte = stream.toByteArray();
            long lengthbmp = imageInByte.length / 1024;

            Log.e("the size is  ", String.valueOf(lengthbmp));


        }
        return bitmap;
    }

    public static String ConvertBitmapToString(Bitmap bitmap) {
        String encodedImage = "";

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void logUser() {
        Crashlytics.setUserIdentifier("12345");
        Crashlytics.setUserEmail("user@fabric.io");
        Crashlytics.setUserName("Test User");
    }

    public void getPermissionAndPicImage() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        Constant.MY_PERMISSIONS_REQUEST_CEMERA_OR_GALLERY);
            } else {
                ImagePicker.pickImage(this);
            }
        } else {
            ImagePicker.pickImage(this);
        }
    }

}
