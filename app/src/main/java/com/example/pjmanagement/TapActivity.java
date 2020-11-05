package com.example.pjmanagement;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TapActivity extends AppCompatActivity {
    private TextView mTextMessage;
    long lastPressed;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch(item.getItemId()){
                case R.id.navigation_home:
                    mTextMessage.setText("home");
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText("friends");
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText("profile");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationSelectedListener);
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