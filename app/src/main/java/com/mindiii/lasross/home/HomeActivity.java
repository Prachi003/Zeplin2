package com.mindiii.lasross.home;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mindiii.lasross.R;
import com.mindiii.lasross.addtocart.AddToCartLongActivity17;
import com.mindiii.lasross.activity.CoatsActivity;
import com.mindiii.lasross.addtocart.model.DescriptionModel;
import com.mindiii.lasross.home.interfc.HeaderInterface;
import com.mindiii.lasross.loginregistration.LoginActivity;
import com.mindiii.lasross.activity.UserProfileActivity26;
import com.mindiii.lasross.adapter.ExpandableListAdapter;
import com.mindiii.lasross.adapter.ExpandableListStaticAdapter;
import com.mindiii.lasross.adapter.FooterListAdapter;
import com.mindiii.lasross.adapter.HeaderListAdapter;
import com.mindiii.lasross.base.BaseActivity;
import com.mindiii.lasross.fragment.HomeScreenFragment;
import com.mindiii.lasross.helper.RoundedTransformation;
import com.mindiii.lasross.model.FooterListModel;
import com.mindiii.lasross.model.HeaderListModel1;
import com.mindiii.lasross.sessionNew.Session;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    /*/home/mindiii/.git*/

    private ExpandableListView listView1,listView2;//,listView3;
    private ExpandableListAdapter listAdapter1;
    private ExpandableListStaticAdapter listAdapter2;//,listAdapter3;
    private List<String> listDataHeader1,listDataHeader2;//,listDataHeader3;
    private HashMap<String, List<String>> listHash1,listHash2;//,listHash3;
    private DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    RelativeLayout rvUserProfile;

    List<String> itemList, childList, subMenuItemList;
    String[] itemArray;

    RecyclerView rvListUp, rvListDown;
    List<DescriptionModel.HeaderListModel> headerLists;
    List<HeaderListModel1> headerListModel1s;
    List<FooterListModel> footerLists;
    HeaderListAdapter headerListAdapter;
    FooterListAdapter footerListAdapter;
    private GoogleApiClient googleApiClient;
    ProgressDialog progressDialog;
    Session session;
    ImageView ivMenu;
    android.support.v7.app.ActionBarDrawerToggle actionBarDrawerToggle;


    TextView tvUserName;

    ImageView ivUserPic, ivSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_layout);

        initView();

        replaceFragment(new HomeScreenFragment(), R.id.fragment);
    }

    private void initView() {
        listDataHeader1 = new ArrayList<>();
        listHash1 = new HashMap<>();

        listDataHeader2 = new ArrayList<>();
        listHash2 = new HashMap<>();

        session = new Session(this);
        itemList = new ArrayList<>();
        subMenuItemList = new ArrayList<>();

        headerLists = new ArrayList<>();
        footerLists = new ArrayList<>();
        headerListModel1s = new ArrayList<>();

        rvListUp = findViewById(R.id.rvListUp);
        rvListDown = findViewById(R.id.rvListDown);
        rvListUp.setHasFixedSize(true);
        rvListDown.setHasFixedSize(true);


        Log.v("session", "" + session.getProfile_image());
        Log.v("session1", "" + session.getLast_Name());
        Log.v("session2", "" + session.getFirst_name());

        progressDialog = new ProgressDialog(this);


        ///////////////////HEADER ADAPTER/////////////////////////
        headerAdapter();
        /////////////////////////////////////////////////////////


        ///////////////////FOOTER ADAPTER/////////////////////////
        footerAdapter();
        //////////////////////////////////////////////////////////


        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .build();

        listView1 = findViewById(R.id.elvExpList1);
        listView2 = findViewById(R.id.elvExpList2);

        tvUserName = findViewById(R.id.tvUserName);

        ivUserPic = findViewById(R.id.ivUserPic);
        ivSort = findViewById(R.id.ivSort);

        rvUserProfile = findViewById(R.id.rvUserProfile);

        if (!session.getProfile_image().isEmpty()) {
            Picasso.with(this)
                    .load(session.getProfile_image())
                    .resize(600, 600)
                    .transform(new RoundedTransformation(50, 0))
                    .into(ivUserPic);
        }

        tvUserName.setText(session.getFirst_name() + " " + session.getLast_Name());

        rvUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, UserProfileActivity26.class));
                //finish();
            }
        });

        ivMenu = findViewById(R.id.ivMenu);


        ///////////////////////menuExpandableApi()///////////////////////////////////
        menuExpandableApi();
        ////////////////////////////////////////////////////////////////////////////


        ///////////////////////menuExpandableApi()///////////////////////////////////
        menuExpandableStatic();
        ////////////////////////////////////////////////////////////////////////////


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        ivSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });


        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });



        listView1.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {

                if (groupPosition == 0) {

                    String name = "";
                    if (childPosition == 0) {
                        Intent intent = new Intent(HomeActivity.this, CoatsActivity.class);
                        name = "Coats and Jackets";
                        intent.putExtra("name", name);
                        startActivity(intent);
                    }

                    if (childPosition == 1) {
                        Intent intent = new Intent(HomeActivity.this, CoatsActivity.class);
                        name = "Hoodies & Sweatshirts";
                        intent.putExtra("name", name);
                        startActivity(intent);
                    }

                    if (childPosition == 2) {
                        Intent intent = new Intent(HomeActivity.this, CoatsActivity.class);
                        name = "Shirt";
                        intent.putExtra("name", name);
                        startActivity(intent);
                    }

                    if (childPosition == 3) {
                        Intent intent = new Intent(HomeActivity.this, CoatsActivity.class);
                        name = "T-Shirt";
                        intent.putExtra("name", name);
                        startActivity(intent);
                    }
                }
                return false;
            }
        });

    }

    private void logoutApi(){
        progressDialog.show();
        progressDialog.setMessage("loading.....");

        AndroidNetworking.get("http://dev.lasross.com/api/service/logout")
                .addHeaders("authToken", session.getCookie())
                .setTag(this)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("response is", response.toString());
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        finish();
                        try {
                            String status = response.getString("status");
                            if (status.equals("ok")) {
                                progressDialog.dismiss();
                                String message = response.getString("message");
                                session.logout();
                                Toast.makeText(HomeActivity.this, message, Toast.LENGTH_LONG).show();
                            } else if (status.equals("error")) {
                                progressDialog.dismiss();
                                String error = response.getString("error");
                                Toast.makeText(HomeActivity.this, error, Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("response is", anError.toString());
                        progressDialog.dismiss();
                        Toast.makeText(HomeActivity.this, "Invalid valid authentication.", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void setHeaderAdapterData() {

        headerListAdapter = new HeaderListAdapter(headerListModel1s, this, new HeaderInterface() {
            @Override
            public void onClickListener() {
                startActivity(new Intent(HomeActivity.this, AddToCartLongActivity17.class));
            }
        });

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (headerListAdapter.getItemViewType(position)) {
                    case HeaderListAdapter.TYPE_HEAD:
                        return 2;

                    case HeaderListAdapter.TYPE_LIST:
                        return 1;

                    default:
                        return 1;
                }
            }
        });
        rvListUp.setLayoutManager(mLayoutManager);
        rvListUp.setAdapter(headerListAdapter);
    }

    private void headerAdapter() {
        progressDialog.show();
        progressDialog.setMessage("loading.....");

        HashMap<String, String> paramas = new HashMap();
        paramas.put("authToken", session.getCookie());

        getDataManager().getHomeProduct(paramas).getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    if (status.equals("ok")) {
                        progressDialog.dismiss();

                        JSONArray jsonArray = jsonObject.getJSONArray("products");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String post_title = jsonObject1.getString("post_title");
                            String featuredImage = jsonObject1.getString("featuredImage");
                            String price = jsonObject1.getString("price");
                            String category = jsonObject1.getString("category");
                            HeaderListModel1 itemList = new HeaderListModel1(
                                    category,
                                    post_title,
                                    price,
                                    featuredImage);
                            headerListModel1s.add(itemList);
                            //Log.e("image is ",featuredImage);
                        }

                        setHeaderAdapterData();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }

    private void setFooterAdapterData() {
        footerListAdapter = new FooterListAdapter(footerLists, this);
        rvListDown.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvListDown.setAdapter(footerListAdapter);
    }

    private void footerAdapter()    {
        progressDialog.show();
        progressDialog.setMessage("loading.....");

        HashMap<String, String> paramas = new HashMap();
        paramas.put("authToken", session.getCookie());

        getDataManager().getHomeProductCategory(paramas).getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;

                //Log.e("response is ",response);
                try {
                    jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    if (status.equals("ok")) {
                        progressDialog.dismiss();

                        JSONArray jsonArray = jsonObject.getJSONArray("category");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String name = jsonObject1.getString("name");
                            String categoryImage = jsonObject1.getString    ("categoryImage");
                            FooterListModel itemList = new FooterListModel(
                                    name,
                                    "from $50",
                                    categoryImage);
                            footerLists.add(itemList);
                            //Log.e("image is ",categoryImage);
                        }

                        setFooterAdapterData();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(ANError anError) {

            }
        });


    }

    private void openDialog() {

        final Dialog dialog = new Dialog(HomeActivity.this);//,android.R.style.Theme_Dialog);
        dialog.setContentView(R.layout.open_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final RadioGroup radioGroup;
        final RadioButton rbDefault, rbPoplarity, rbRating, rbLatest, rbLowHigh, rbHighLow;

        radioGroup = dialog.findViewById(R.id.radioGroup);
        rbDefault = dialog.findViewById(R.id.rbDefault);
        rbPoplarity = dialog.findViewById(R.id.rbPoplarity);
        rbRating = dialog.findViewById(R.id.rbRating);
        rbLatest = dialog.findViewById(R.id.rbLatest);
        rbLowHigh = dialog.findViewById(R.id.rbLowHigh);
        rbHighLow = dialog.findViewById(R.id.rbHighLow);

        final Typeface semi_bold = ResourcesCompat.getFont(HomeActivity.this, R.font.ibmplexsans_semibold);
        final Typeface regular = ResourcesCompat.getFont(HomeActivity.this, R.font.ibmplexsans_regular);

        rbDefault.setChecked(true);
        rbDefault.setTypeface(semi_bold);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.rbDefault) {
                    rbDefault.setTypeface(semi_bold);
                } else {
                    rbDefault.setTypeface(regular);
                }

                if (checkedId == R.id.rbPoplarity) {
                    rbPoplarity.setTypeface(semi_bold);
                } else {
                    rbPoplarity.setTypeface(regular);
                }

                if (checkedId == R.id.rbRating) {
                    rbRating.setTypeface(semi_bold);
                } else {
                    rbRating.setTypeface(regular);
                }

                if (checkedId == R.id.rbLatest) {
                    rbLatest.setTypeface(semi_bold);
                } else {
                    rbLatest.setTypeface(regular);
                }

                if (checkedId == R.id.rbLowHigh) {
                    rbLowHigh.setTypeface(semi_bold);
                } else {
                    rbLowHigh.setTypeface(regular);
                }

                if (checkedId == R.id.rbHighLow) {
                    rbHighLow.setTypeface(semi_bold);
                } else {
                    rbHighLow.setTypeface(regular);
                }
            }
        });

        dialog.setTitle("Add Photo!");
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        lp.windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setAttributes(lp);

        dialog.show();

    }

    private void setExpandableListViewHeight(ExpandableListView listView, int group) {

        ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);

            for (int i = 0; i < listAdapter.getGroupCount(); i++) {
                View groupItem = listAdapter.getGroupView(i, false, null, listView);
                groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                totalHeight += groupItem.getMeasuredHeight();

                if (((listView.isGroupExpanded(i)) && (i != group))
                        || ((!listView.isGroupExpanded(i)) && (i == group))) {
                    for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                        View listItem = listAdapter.getChildView(i, j, false, null,
                                listView);
                        listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                        totalHeight += listItem.getMeasuredHeight();
                    }
                }
            }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 100;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    private void setExpandableListStaticViewHeight(ExpandableListView listView, int group) {

        ExpandableListStaticAdapter listAdapter = (ExpandableListStaticAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);

        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();
                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 100;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    private void menuExpandableApi() {

        String subMenu = "";

        progressDialog.show();
        progressDialog.setMessage("loading.....");

        HashMap<String, String> paramas = new HashMap();
        paramas.put("authToken", session.getCookie());

        getDataManager().getMenuList(paramas).getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("ok")) {
                        progressDialog.dismiss();
                        itemList.clear();

                        JSONArray jsonArray = jsonObject.getJSONArray("menu");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            childList = new ArrayList<>();
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String title = jsonObject1.getString("title");
                            String subMenuTitle = "";
                            listDataHeader1.add(title);

                            JSONArray subMenu = jsonObject1.getJSONArray("subMenu");
                            if (subMenu.length()>0)
                            {
                                for(int j = 0; j < subMenu.length(); j++) {
                                    JSONObject object = subMenu.getJSONObject(j);
                                    subMenuTitle = object.getString("title");
                                    childList.add(subMenuTitle);
                                    /*final String finalSubMenuTitle = subMenuTitle;
                                    final int finalI = i;
                                    final int finalJ = j;*/
                                    showData(i,j,subMenuTitle);
                                }
                            }
                            String tempVal = listDataHeader1.get(i);
                            listHash1.put(tempVal, childList);
                        }

                        listAdapter1 = new ExpandableListAdapter(HomeActivity.this, listDataHeader1, listHash1);
                        listView1.setAdapter(listAdapter1);


                        listView1.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                            @Override
                            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {

                                if (groupPosition == 2) {

                                    String name = "";
                                    if (childPosition == 0) {
                                        Intent intent = new Intent(HomeActivity.this, CoatsActivity.class);
                                        name = "Coats and Jackets";
                                        intent.putExtra("name", name);
                                        startActivity(intent);
                                    }

                                    if (childPosition == 1) {
                                        Intent intent = new Intent(HomeActivity.this, CoatsActivity.class);
                                        name = "Hoodies & Sweatshirts";
                                        intent.putExtra("name", name);
                                        startActivity(intent);
                                    }

                                    if (childPosition == 2) {
                                        Intent intent = new Intent(HomeActivity.this, CoatsActivity.class);
                                        name = "Shirt";
                                        intent.putExtra("name", name);
                                        startActivity(intent);
                                    }

                                    if (childPosition == 3) {
                                        Intent intent = new Intent(HomeActivity.this, CoatsActivity.class);
                                        name = "T-Shirt";
                                        intent.putExtra("name", name);
                                        startActivity(intent);
                                    }
                                }
                                return false;
                            }
                        });

                        setExpandableListViewHeight(listView1, -1);
                        listView1.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                            @Override
                            public boolean onGroupClick(ExpandableListView parent, View v, int position, long id) {
                                setExpandableListViewHeight(parent, position);
                                return false;
                            }
                        });


                        /*listView1.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                            int previousItem = -1;

                            @Override
                            public void onGroupExpand(int groupPosition) {
                                if (groupPosition != previousItem)
                                    listView1.collapseGroup(previousItem);
                                previousItem = groupPosition;
                            }
                        });*/


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {

            }
        });

    }

    private void showData(final int i, final int j, final String s) {

        listView1.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {

                Log.e("its working ","IT IS WORKING");

                if (groupPosition == i) {

                    String name = "";
                    if (childPosition == j) {
                        Intent intent = new Intent(HomeActivity.this, CoatsActivity.class);
                        //name = "Coats and Jackets";
                        intent.putExtra("name", s);
                        startActivity(intent);
                    }
                }
                return false;
            }
        });
    }

    private void menuExpandableStatic() {

        listDataHeader2.add("Wishlist");
        listDataHeader2.add("Order");
        listDataHeader2.add("Cart");
        listDataHeader2.add("Account");
        listDataHeader2.add("Customer Help");
        listDataHeader2.add("FAQ");
        listDataHeader2.add("Contact Us");
        listDataHeader2.add("Logout");
        List<String> wishList = new ArrayList<>();
        List<String> order = new ArrayList<>();
        List<String> cart = new ArrayList<>();
        List<String> account = new ArrayList<>();
        List<String> help = new ArrayList<>();
        List<String> faq = new ArrayList<>();
        List<String> contact = new ArrayList<>();
        List<String> logout = new ArrayList<>();

        listHash2.put(listDataHeader2.get(0),wishList);
        listHash2.put(listDataHeader2.get(1),order);
        listHash2.put(listDataHeader2.get(2),cart);
        listHash2.put(listDataHeader2.get(3),account);
        listHash2.put(listDataHeader2.get(4),help);
        listHash2.put(listDataHeader2.get(5),faq);
        listHash2.put(listDataHeader2.get(6),contact);
        listHash2.put(listDataHeader2.get(7),logout);

        listAdapter2 = new ExpandableListStaticAdapter(HomeActivity.this, listDataHeader2, listHash2);
        listView2.setAdapter(listAdapter2);

        final int groupCount = listAdapter2.getGroupCount();
        Log.e("groupCount is ", groupCount+"");

       /* listView2.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                Log.e("groupPosition is ", groupPosition+"");
                if (groupPosition == 7) {
                    logoutApi();
                }

                return false;
            }
        });*/

        setExpandableListStaticViewHeight(listView2, -1);
        listView2.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int position, long id) {
                if (position == 7) {
                    logoutApi();
                }
                setExpandableListStaticViewHeight(parent, position);
                return false;
            }
        });


        listView2.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousItem = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousItem)
                    listView2.collapseGroup(previousItem);
                previousItem = groupPosition;
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!session.getFirst_name().isEmpty()) {
            tvUserName.setText(session.getFirst_name() + " " + session.getLast_Name());
        }

        if (!session.getProfile_image().equals("")) {
            String img = session.getProfile_image();
            Picasso.with(this)
                    .load(img).placeholder(R.drawable.dress1)
                    .into(ivUserPic);
        }

        drawerLayout.closeDrawers();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
