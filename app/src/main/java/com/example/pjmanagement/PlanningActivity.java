package com.example.pjmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PlanningActivity extends AppCompatActivity {
    private Button timeTable;
    private Button Chat;
    public String projectName;
    public String yaer;
    public String month;
    public String day;
    public String effect;
    public String scenario;

    public PlanningActivity(String projectName, String yaer,
                            String month, String day, String effect, String scenario) {
        this.projectName = projectName;
        this.yaer = yaer;
        this.month = month;
        this.day = projectName;
        this.effect = effect;
        this.scenario = scenario;
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);

        timeTable = findViewById(R.id.timetable);
        Chat = findViewById(R.id.chat);

        timeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TimeTabeleActivity.class);
            }
        });

        Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
            }
        });

    }
}