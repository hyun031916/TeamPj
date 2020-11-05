package com.example.pjmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class JoinActivity extends AppCompatActivity {

    private EditText userEmail, userPwd, checkPwd,userId;
    private Button joinBtn;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);  //뒤로가기버튼
//        actionBar.setDisplayShowHomeEnabled(true);  //홈 아이콘

        userEmail = (EditText) findViewById(R.id.userEmail);
        userPwd = (EditText) findViewById(R.id.userPwd);
        checkPwd = (EditText) findViewById(R.id.checkPwd);
        userId = (EditText) findViewById(R.id.userId);
        joinBtn = (Button) findViewById(R.id.joinBtn);

        //파이어베이스 접근 설정
        firebaseAuth = FirebaseAuth.getInstance();

        //가입 버튼 클릭 리스너 -> 파이어베이스에 데이터 저장하기
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //가입 정보 가져오기
                final String email = userEmail.getText().toString().trim();
                final String pwd = userPwd.getText().toString().trim();
                final String check_pwd = checkPwd.getText().toString().trim();
                final String id = userId.getText().toString().trim();
                //공백인 부분을 제거하고 보여주는 trim();


                if (pwd.equals(check_pwd)) {

                    final ProgressDialog mDialog = new ProgressDialog(JoinActivity.this);
                    mDialog.setMessage("가입 중입니다. ");
                    mDialog.show();

                    //파이어베이스에 신규계정 등록하기
                    firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(JoinActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //가입 성공시
                            if (task.isSuccessful()) {
                                mDialog.dismiss();

                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                String email = user.getEmail();
                                String uid = user.getUid();
                                String name = userId.getText().toString().trim();

                                //해쉬맵 테이블을 파이어베이스 데이터베이스에 저장
                                HashMap<Object, String> hashMap = new HashMap<>();

                                hashMap.put("uid", uid);
                                hashMap.put("email", email);
                                hashMap.put("name", name);

                                FirebaseDatabase database = FirebaseDatabase.getInstance();

                                DatabaseReference reference = database.getReference("Users");

                                reference.child(uid).setValue(hashMap);

                                //가입이 이루어졌을 시 가입 화면 빠져나감
                                Intent intent = new Intent(JoinActivity.this, MainActivity2.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(JoinActivity.this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();

                                return; //해당 메소드 진행을 멈추고 빠져나감

                            }
                        }
                    });
                    //비밀번호 오류시
                } else {
                    Toast.makeText(JoinActivity.this, "비밀번호가 틀렸습니다. 다시 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
    
    public boolean onSupportNavigateUp(){
        onBackPressed();    //뒤로 가기 버튼 눌렀을 시
        return super.onSupportNavigateUp();//뒤로가기 버튼
    }
}