package com.mindiii.lasross.base;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.mindiii.lasross.Lasross;
import com.mindiii.lasross.session_api.prefs.AppDataManager;

public class BaseActivity extends AppCompatActivity {

    private Activity activity = this;
    private Dialog mProgressBar;


    public static AppDataManager getDataManager() {
        return Lasross.getDataManager();
    }

    public void replaceFragment(@NonNull Fragment fragmentHolder, Integer layoutId) {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();

            //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            String fragmentName = fragmentHolder.getClass().getName();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            fragmentTransaction.replace(layoutId, fragmentHolder, fragmentName);
            fragmentTransaction.addToBackStack(fragmentName);
            fragmentTransaction.commit();
            //hideKeyboard();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void addFragment(@NonNull Fragment fragment, int layoutId, boolean addToBackStack) {
        try {
            String fragmentName = fragment.getClass().getName();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            //fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setEnterTransition(null);
            }
            fragmentTransaction.add(layoutId, fragment, fragmentName);
            if (addToBackStack) fragmentTransaction.addToBackStack(fragmentName);
            fragmentTransaction.commit();

            //hideKeyboard();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
