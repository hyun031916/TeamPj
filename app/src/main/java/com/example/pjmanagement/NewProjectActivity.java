package com.example.pjmanagement;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class NewProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

        Button new_project_btn = (Button) findViewById(R.id.new_project_btn);

    }

//    public boolean onTouchEvent(MotionEvent event){
//        switch(event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//
//        }
//    }
}