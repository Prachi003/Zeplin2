package com.mindiii.lasross.loginregistration;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.mindiii.lasross.R;
import com.mindiii.lasross.home.HomeActivity;
import com.mindiii.lasross.sessionNew.Session;
import com.mindiii.lasross.sessionNew.UserInfo;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    TextView  tvSignUp,tvForgotPass,tvSignIn;
    EditText etFullName,etEmail,etPass;
    LinearLayout llImage;

    Pattern pattern;
    ProgressDialog progressDialog;

    //SharedPref pref;
    LinearLayout linearLayout;

    Session session;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_form);


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

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.home_header_bg1));

        progressDialog = new ProgressDialog(this);
        llImage = findViewById(R.id.llImage);

        //pref = new SharedPref(this);

        session = new Session(this);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        final String formattedDate = df.format(c.getTime());
        Log.e("date is ", formattedDate);

        pattern = Pattern.compile("^[a-zA-Z ]+$");

        tvSignIn = findViewById(R.id.tvSignIn);
        tvSignUp = findViewById(R.id.tvSignUp);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                finish();
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etFullName.getText().toString();
                String email = etEmail.getText().toString();
                String pass = etPass.getText().toString();

                try  {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {

                }

                if(name.equals("")) {
                    etFullName.setError("Enter name");
                    return;
                }
                else if(email.equals("")){
                    etEmail.setError("Enter email");
                    return;
                }else if(pass.equals("")){
                    etPass.setError("Enter password");
                    return;
                }

                if(isValidEmail(email) && isValidPass(pass)&& isValidName(name)){
                    validateData(name, email, pass,formattedDate,v);
                }
                else if(!isValidName(name))
                {
                    etFullName.setError("Invalid name");
                }
                else if(!isValidEmail(email))
                {
                    etEmail.setError("Invalid email");
                }
                else if(!isValidPass(pass))
                {
                    etPass.setError("Invalid pass");
                }
            }
        });
    }

    private  void validateData(final String fullName, final String email, final String pass, final String formattedDate, final View v) {

        progressDialog.show();
        progressDialog.setMessage("loading.....");

        AndroidNetworking.post("http://dev.lasross.com/api/service/regisration")
                .addBodyParameter("email",email)
                .addBodyParameter("password",pass)
                .addBodyParameter("username",email)
                .addBodyParameter("display_name",fullName)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String status = response.getString("status");
                            Log.e("response", String.valueOf(response));

                            if(status.equals("ok")) {
                                progressDialog.dismiss();
                                UserInfo userInfo = new UserInfo();

                                JSONObject object = response.getJSONObject("user");
                                userInfo.cookie = response.getString("cookie");

                                userInfo.userId = object.getString("id");
                                userInfo.first_name = object.getString("firstname");
                                userInfo.last_name = object.getString("lastname");
                                userInfo.email = object.getString("email");
                                userInfo.profile_image = object.getString("avatar_url_full");
                                userInfo.description = object.getString("description");

                                session.createSession(userInfo);

                                Log.e("first name is ", session.getFirst_name());
                                Log.e("cookie is ", session.getCookie());
                                Log.e("email is ", session.getEmail());
                                Log.e("profile url  is ", session.getProfile_image());
                                Snackbar snackbar = Snackbar
                                        .make(v, "Successfully registered", Snackbar.LENGTH_INDEFINITE)
                                        .setAction("Done", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                startActivity(new Intent(RegistrationActivity.this, HomeActivity.class));
                                                finish();
                                            }
                                        });
                                snackbar.show();
                            }
                            else {
                                progressDialog.dismiss();
                                String error = response.getString("error");
                                Toast.makeText(RegistrationActivity.this, error, Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(RegistrationActivity.this, "User already exists",Toast.LENGTH_LONG).show();
                    }
                });
    }

    private boolean isValidEmail(String email) {
        return email.matches(emailPattern) ;
    }

    private boolean isValidPass(String pass) {
        return pass.length()>5;
    }

    private boolean isValidName(String lName) {
        return pattern.matcher(lName).matches();
    }
}
