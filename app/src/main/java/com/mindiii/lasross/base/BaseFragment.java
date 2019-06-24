package com.mindiii.lasross.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.inputmethod.InputMethodManager;

import com.mindiii.lasross.Lasross;
import com.mindiii.lasross.session_api.prefs.AppDataManager;

public class BaseFragment extends Fragment {


    private BaseActivity activity;
    private Dialog mProgressBar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof  BaseActivity){
            activity = (BaseActivity) context;
        }
    }

    public BaseActivity getBaseActivity(){
        return activity;
    }

    protected void hideKeyboard() {
        if (getBaseActivity().getCurrentFocus() == null) return;

        InputMethodManager inputMethodManager = (InputMethodManager) getBaseActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        assert inputMethodManager != null;
        inputMethodManager.hideSoftInputFromWindow(getBaseActivity().getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    protected AppDataManager getDataManager() {
        return Lasross.getDataManager();
    }


    /*protected void setLoading(Boolean isLoading) {
        //first cancel progress bar
        if (mProgressBar != null && mProgressBar.isShowing()) {
            mProgressBar.cancel();
        }
        if (isLoading) {
            mProgressBar = CommonUtils.showLoadingDialog(getBaseActivity());
        }
    }*/
}
