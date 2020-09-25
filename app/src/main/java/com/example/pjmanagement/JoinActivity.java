package com.example.pjmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class JoinActivity extends AppCompatActivity {

    private EditText userId;
    private EditText userPwd;
    private EditText userPwd2,userName;
    private Button joinBtn;
    FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        userId = (EditText) findViewById(R.id.userId);
        userPwd = (EditText) findViewById(R.id.userPwd);
        userPwd2=(EditText)findViewById(R.id.userPwd2);
        userName=(EditText)findViewById(R.id.userName);
        joinBtn = (Button) findViewById(R.id.joinbtn);


        firebaseAuth = FirebaseAuth.getInstance();

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = userId.getText().toString().trim();
                final String pwd = userPwd.getText().toString().trim();
                final String age = userPwd2.getText().toString().trim();
                final String name = userName.getText().toString().trim();
                //공백인 부분을 제거하고 보여주는 trim();


                firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(JoinActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(JoinActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(JoinActivity.this, "등록 에러", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
            }
        });
    }
}