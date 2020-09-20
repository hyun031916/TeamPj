package com.example.pjmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CheckMemberActivity extends AppCompatActivity {
    TextView name_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_member);

        TextView idText = (TextView) findViewById(R.id.name_text);
        Bundle intent = getIntent().getExtras();
        idText.setText(intent.getString("ID"));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TextView startText = (TextView) findViewById(R.id.start_text);
            }
        }, 2000);

        TextView startText = (TextView) findViewById(R.id.start_text);

        startText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent NewProjectIntent = new Intent(CheckMemberActivity.this, NewProjectActivity.class);
                CheckMemberActivity.this.startActivity(NewProjectIntent);

            }

        });
    }
}