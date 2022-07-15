package com.example.youtubeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.youtubeapp.fragment.FragmentBtSheetUser;
import com.example.youtubeapp.fragment.FragmentExplore;
import com.example.youtubeapp.fragment.FragmentHome;
import com.example.youtubeapp.fragment.FragmentLibrary;
import com.example.youtubeapp.fragment.FragmentNotify;
import com.example.youtubeapp.fragment.FragmentSubs;
import com.example.youtubeapp.fragment.FragmentValueSearch;
import com.example.youtubeapp.interfacee.InterfaceDefaultValue;
public class MainActivity extends AppCompatActivity implements InterfaceDefaultValue {

    private ImageView ivEndNavHome, ivEndNavExplore,
            ivEndNavSubscriptions, ivEndNavNotification,
            ivEndNavLibrary, ivSearch, ivUser;
    FragmentManager fragmentManager = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();

        Intent getStringSearch = getIntent();
        String valueSearch = getStringSearch.getStringExtra(VALUE_SEARCH);
        if (valueSearch != null){
            Log.d("AHIHIHIHIHIHIIHIHI", valueSearch+"");
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //ADD FRAGMENT
            FragmentValueSearch fragmentValueSearch = new FragmentValueSearch();
            Bundle bundle = new Bundle();
            bundle.putString(VALUE_SEARCH, valueSearch);
            fragmentValueSearch.setArguments(bundle);
            fragmentTransaction.add(R.id.cl_contains_search,
                    fragmentValueSearch, FRAGMENT_SEARCH);
            fragmentTransaction.addToBackStack(FRAGMENT_SEARCH);
            fragmentTransaction.commit();
        }
        else{
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //ADD FRAGMENT
            FragmentHome fragmentHome = new FragmentHome();
            fragmentTransaction.add(R.id.cl_contains_fragment,
                    fragmentHome, FRAGMENT_HOME);
            fragmentTransaction.addToBackStack(FRAGMENT_HOME);
            fragmentTransaction.commit();
        }

        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOnClickUser();
            }
        });

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToSearch = new Intent(MainActivity.this,
                        ActivitySearchVideo.class);
                startActivity(intentToSearch);
            }
        });
    }

    private void openOnClickUser(){
        FragmentBtSheetUser fragmentBtSheetUser = new FragmentBtSheetUser();
        fragmentBtSheetUser.show(getSupportFragmentManager(), fragmentBtSheetUser.getTag());
    }

    public void onClickHome(@NonNull View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()){
            case R.id.iv_end_bar_home:
                setDisplayEndNavOff();
                ivEndNavHome.setImageResource(R.drawable.ic_home_on);
//                REMOVE IF SEARCH DISPLAY
                manageFragment(FRAGMENT_SEARCH);

                getSupportFragmentManager().popBackStack(FRAGMENT_HOME, 0);
                break;
            case R.id.iv_end_bar_explore:
                setDisplayEndNavOff();
                ivEndNavExplore.setImageResource(R.drawable.ic_explore_on);
                FragmentExplore fragmentExplore = new FragmentExplore();
                fragmentTransaction.replace(R.id.cl_contains_fragment, fragmentExplore, FRAGMENT_EXPLORE);
                fragmentTransaction.addToBackStack(FRAGMENT_EXPLORE);
                Toast.makeText(this, "Fragment Ex", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_end_bar_subscriptions:
                setDisplayEndNavOff();
                ivEndNavSubscriptions.setImageResource(R.drawable.ic_subscrip_on);
                FragmentSubs fragmentSubs = new FragmentSubs();
                fragmentTransaction.replace(R.id.cl_contains_fragment, fragmentSubs, FRAGMENT_SUBSCRIPTION);
                fragmentTransaction.addToBackStack(FRAGMENT_SUBSCRIPTION);
                break;
            case R.id.iv_end_bar_notifications:
                setDisplayEndNavOff();
                ivEndNavNotification.setImageResource(R.drawable.ic_notifitcation_onn);
                FragmentNotify fragmentNotify = new FragmentNotify();
                fragmentTransaction.replace(R.id.cl_contains_fragment, fragmentNotify, FRAGMENT_NOTIFICATION);
                fragmentTransaction.addToBackStack(FRAGMENT_NOTIFICATION);
                break;
            case R.id.iv_end_bar_library:
                setDisplayEndNavOff();
                ivEndNavLibrary.setImageResource(R.drawable.ic_library_on);
                FragmentLibrary fragmentLibrary = new FragmentLibrary();
                fragmentTransaction.replace(R.id.cl_contains_fragment, fragmentLibrary, FRAGMENT_LIBRARY);
                fragmentTransaction.addToBackStack(FRAGMENT_LIBRARY);
                break;
        }
        fragmentTransaction.commit();
    }

    public void manageFragment(String TAG_FRAGMENT){
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
        if(fragment != null)
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount()>0){
            getSupportFragmentManager().popBackStack(FRAGMENT_HOME, 0);
            setDisplayEndNavOff();
            ivEndNavHome.setImageResource(R.drawable.ic_home_on);
        }
        else{
            super.onBackPressed();
        }
    }

    public void setDisplayEndNavOff() {
        ivEndNavExplore.setImageResource(R.drawable.ic_explore_off);
        ivEndNavHome.setImageResource(R.drawable.ic_home_off);
        ivEndNavSubscriptions.setImageResource(R.drawable.ic_subscrip_off);
        ivEndNavLibrary.setImageResource(R.drawable.ic_library_off);
        ivEndNavNotification.setImageResource(R.drawable.ic_notification_off);
    }

    @SuppressLint("WrongViewCast")
    public void mapping() {
        ivUser = findViewById(R.id.iv_top_bar_user);
        ivSearch = findViewById(R.id.iv_top_bar_search);
        ivEndNavExplore = findViewById(R.id.iv_end_bar_explore);
        ivEndNavHome = findViewById(R.id.iv_end_bar_home);
        ivEndNavSubscriptions = findViewById(R.id.iv_end_bar_subscriptions);
        ivEndNavLibrary = findViewById(R.id.iv_end_bar_library);
        ivEndNavNotification = findViewById(R.id.iv_end_bar_notifications);
    }

}