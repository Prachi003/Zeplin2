package com.mindiii.lasross.loginregistration;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.mindiii.lasross.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.mindiii.lasross.base.BaseActivity;
import com.mindiii.lasross.helper.Util;
import com.mindiii.lasross.home.HomeActivity;
import com.mindiii.lasross.sessionNew.Session;
import com.mindiii.lasross.sessionNew.UserInfo;

public class LoginActivity extends BaseActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {


    private static final String EMAIL = "email";
    TextView tvSignUp, tvForgotPass, tvSignIn;
    LinearLayout llFacebook, llGoogle, linearLayout;
    EditText etEmail, etPass;
    CallbackManager callbackManager;

    ProgressDialog progressDialog;

    Session session;

    LoginButton loginButton;

    private SignInButton googleButtonSignIn;
    private Button googleButtonSignOut;
    private GoogleApiClient googleApiClient;
    private GoogleSignInClient googleSignInClient;
    private static final int REQ_CODE_GOOGLE = 9001;


    GoogleSignInClient mGoogleSignInClient;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        Log.i("key has", "its working");

        llGoogle = findViewById(R.id.llGoogle);
        linearLayout = findViewById(R.id.linearLayout);


        session = new Session(this);
        googleButtonSignIn = findViewById(R.id.sign_in_button);

        googleButtonSignIn.setOnClickListener(this);

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, REQ_CODE_GOOGLE);
            }
        });

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, signInOptions);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i("key has", hashKey);
                Log.e("MY KEY HASH:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        llGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = googleSignInClient.getSignInIntent();
                startActivityForResult(intent, REQ_CODE_GOOGLE);
            }
        });


        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.home_header_bg1));

        progressDialog = new ProgressDialog(this);

        loginButton = findViewById(R.id.login_button);

        loginButton.setReadPermissions(Arrays.asList(EMAIL));

        callbackManager = CallbackManager.Factory.create();

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                etEmail.setText("Login successfull" + loginResult.getAccessToken().getUserId());
            }

            @Override
            public void onCancel() {
                etEmail.setText("Login cancel");
            }

            @Override
            public void onError(FacebookException exception) {

            }
        });

        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }

        //pref = new SharedPref(this);

        tvSignUp = findViewById(R.id.tvSignUp);
        tvForgotPass = findViewById(R.id.tvForgotPass);
        tvSignIn = findViewById(R.id.tvSignIn);

        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);

        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openForgetPasswordDialog();
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                finish();
            }
        });

        printHashKey(this);

        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard();
                }
            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the input method manager
                InputMethodManager inputMethodManager = (InputMethodManager)
                        view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                // Hide the soft keyboard
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });


        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String pass = etPass.getText().toString().trim();

                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {

                }

                if (email.equals("")) {
                    etEmail.setError("Enter email");
                    return;
                } else if (pass.equals("")) {
                    etPass.setError("Enter password");
                    return;
                }


                if (isValidEmail(email) && isValidPass(pass)) {
                    validateDataForLoginApi(email, pass, v);
                } else if (!isValidEmail(email)) {
                    etEmail.setError("Invalid email");
                } else if (!isValidPass(pass)) {
                    etPass.setError("Invalid password");
                }
            }
        });

    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etEmail.getWindowToken(), 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    }

    public void fbLogin(View view) {

        //LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("user_photos", "email", "public_profile", "user_posts"));
        //LoginManager.getInstance().logInWithPublishPermissions(this, Arrays.asList("publish_actions"));
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList(EMAIL));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        //etEmail.setText("Login successfull" +loginResult.getAccessToken().getUserId());
                        etEmail.setText("Login successfull");
                        final GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {

                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        Log.e("Main", response.toString());
                                        validateDataFacebook(object);
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "name,email,id,picture");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        etEmail.setText("Login cancel");
                        //tvNameEmail.setText("Login cancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                    }
                });


    }

    private void validateDataFacebook(final JSONObject jsonObject) {
        try {
            String name = jsonObject.getString("name");
            String email = jsonObject.getString("email");
            JSONObject picture = jsonObject.getJSONObject("picture");
            JSONObject data = picture.getJSONObject("data");
            String url = data.getString("url");

            Log.e("URL of picture is ", url);
            Log.e("name of user is ", name);
            etEmail.setText(name);


            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void gmailLogin(View view) {
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent, REQ_CODE_GOOGLE);
    }

    public void printHashKey(Context pContext) {
        String TAG = "";
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.e(TAG, "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "printHashKey()", e);
        } catch (Exception e) {
            Log.e(TAG, "printHashKey()", e);
        }
    }

    private void openForgetPasswordDialog() {

        final Dialog dialog = new Dialog(LoginActivity.this);//,android.R.style.Theme_Dialog);
        dialog.setContentView(R.layout.forgot_password_dialog_artboard_2_1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final EditText etEmailID;
        TextView tvSend;
        ImageView ivClose;

        etEmailID = dialog.findViewById(R.id.etEmailID);

        tvSend = dialog.findViewById(R.id.tvSend);

        ivClose = dialog.findViewById(R.id.ivClose);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmailID.getText().toString();
                if (isValidEmail(email)) {
                    validPasswordDetail(email, dialog);
                } else if (!isValidEmail(email)) {
                    etEmailID.setError("Invalid email");
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

    private void validPasswordDetail(String email, final Dialog dialog) {

        progressDialog.show();
        progressDialog.setMessage("loading.....");

        AndroidNetworking.post("http://lasross.com/api/service/forgot_password")
                .addBodyParameter("email", email)
                .setTag(this)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("response is", response.toString());
                        try {
                            String status = response.getString("status");
                            if (status.equals("ok")) {
                                progressDialog.dismiss();
                                dialog.dismiss();
                                String msg = response.getString("msg");
                                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG).show();
                            } else if (status.equals("error")) {
                                progressDialog.dismiss();
                                String error = response.getString("error");
                                Toast.makeText(LoginActivity.this, error, Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("response is", anError.toString());
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Your email address not found!", Toast.LENGTH_LONG).show();
                    }
                });
    }

  /*  private void validateData(final String email, final String pass, final View v) {

        progressDialog.show();
        progressDialog.setMessage("loading.....");


        AndroidNetworking.post("http://dev.lasross.com/api/service/login")
                .addBodyParameter("email", email)
                .addBodyParameter("password", pass)
                .setTag(this)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String status = response.getString("status");
                            if (status.equals("ok")) {
                                String cookie = response.getString("cookie");
                                JSONObject object = response.getJSONObject("user");

                                String id = object.getString("id");
                                String firstname = object.getString("firstname");
                                String lastname = object.getString("lastname");
                                String email = object.getString("email");
                                String profile_image = object.getString("avatar_url_full");
                                String description = object.getString("description");

                                UserInfo userInfo = new UserInfo();
                                userInfo.cookie = cookie;
                                userInfo.first_name = firstname;
                                userInfo.email = email;
                                userInfo.last_name = lastname;
                                userInfo.profile_image = profile_image;
                                userInfo.description = description;

                                session.createSession(userInfo);

                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                finish();
                            } else if (status.equals("error")) {
                                progressDialog.dismiss();
                                String error = response.getString("error");
                                Toast.makeText(LoginActivity.this, error, Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Log.e("error", anError + "");
                        Util.parseError(getBaseContext(), anError);

                        if (anError.getErrorCode() == 0) {
                            Toast.makeText(LoginActivity.this, "Internate Connetion is slow", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(LoginActivity.this, "email does not exist", Toast.LENGTH_LONG).show();
                    }
                });
    }*/


    private void validateDataForLoginApi(final String email, final String pass, final View v) {

        progressDialog.show();
        progressDialog.setMessage("loading.....");

        HashMap<String, String> paramas = new HashMap();
        paramas.put("email", email);
        paramas.put("password", pass);

        getDataManager().doServerLogin(paramas).getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    if (status.equals("ok")) {
                        progressDialog.dismiss();

                        String cookie = jsonObject.getString("cookie");

                        JSONObject object = jsonObject.getJSONObject("user");

                        String id = object.getString("id");
                        String firstname = object.getString("firstname");
                        String lastname = object.getString("lastname");
                        String email = object.getString("email");
                        String profile_image = object.getString("avatar_url_full");
                        String description = object.getString("description");

                        UserInfo userInfo = new UserInfo();
                        userInfo.cookie = cookie;
                        userInfo.first_name = firstname;
                        userInfo.email = email;
                        userInfo.last_name = lastname;
                        userInfo.profile_image = profile_image;
                        userInfo.description = description;

                        session.createSession(userInfo);

                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    } else if (status.equals("error")) {
                        progressDialog.dismiss();
                        String error = jsonObject.getString("error");
                        Toast.makeText(LoginActivity.this, error, Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onError(ANError anError) {
                progressDialog.dismiss();
                Util.parseError(getBaseContext(), anError);

                if (anError.getErrorCode() == 0) {
                    Toast.makeText(LoginActivity.this, "Internate Connetion is slow", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(LoginActivity.this, "email does not exist", Toast.LENGTH_LONG).show();

            }
        });
    }

    private boolean isValidEmail(String email) {
        return email.matches(emailPattern);
    }

    private boolean isValidPass(String pass) {
        return pass.length() > 5;
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        else {
            callbackManager.onActivityResult(requestCode, resultCode, data);

        }
        super.onActivityResult(requestCode, resultCode, data);

    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("Request code ", "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE_GOOGLE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            handleresult(task);
        } else
            callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleresult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            String name = account.getDisplayName();
            String email = account.getEmail();
            String url = String.valueOf(account.getPhotoUrl());
            String displayName = account.getAccount().name;

            Log.e("Name is ", name);
            Log.e("email is ", email);
            Log.e("profile url  is ", url);
            //tvNameEmail.setText(name);
            etEmail.setText(email);
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        } catch (ApiException e) {

            Log.w("", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }

    private void signIn() {

        //Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent, REQ_CODE_GOOGLE);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
