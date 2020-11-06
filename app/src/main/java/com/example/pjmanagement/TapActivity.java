package com.example.pjmanagement;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pjmanagement.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TapActivity extends AppCompatActivity {
    Fragment fragment;
    long lastPressed;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch(item.getItemId()){
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    switchFragment(fragment);
                    return true;
                case R.id.navigation_dashboard:
                    fragment = new FriendsFragment();
                    switchFragment(fragment);
                    return true;
                case R.id.navigation_notifications:
                    fragment = new ProfileFragment();
                    switchFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment fragment = new HomeFragment();
        fragmentTransaction.add(R.id.content, fragment);
        fragmentTransaction.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationSelectedListener);
    }

    public void switchFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.content, fragment);

        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis()-lastPressed<1500){
            finish();
        }
        Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        lastPressed = System.currentTimeMillis();
    }
}