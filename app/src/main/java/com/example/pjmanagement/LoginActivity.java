package com.example.pjmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends Activity {
    EditText userId, userPwd;
    Button loginBtn;
    TextView registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userId = (EditText) findViewById(R.id.userId);
        userPwd = (EditText) findViewById(R.id.userPwd);
        loginBtn = (Button) findViewById(R.id.loginbtn);
        registerBtn = (TextView) findViewById(R.id.registerbtn);

        loginBtn.setOnClickListener(btnListener);
        registerBtn.setOnClickListener(btnListener);
    }

    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;

        @Override
        protected String doInBackground(String... strings) {
            try{
                String str;
                URL url = new URL("http://172.30.118.123:8000/team_project/data.jsp");    //jsp 주소
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");  //POST 방식으로 데이터 전송

                OutputStreamWriter osw  = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&password="+strings[1]+"&type="+strings[2];
                //jsp에 보낼 정보, 보낼 데이터가 여러개일 경우 &로 구분하여 작성

                osw.write(sendMsg);//OutputStreamWriter에 담아 전송하기
                osw.flush();

                //jsp와 정상적으로 연동되었을 때
                if(conn.getResponseCode()== conn.HTTP_OK){
                    InputStreamReader tmp=new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();

                    while((str=reader.readLine())!=null){
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                }else{
                    Log.i("통신 결과 ", conn.getResponseCode()+"에러");
                    //통신 실패 시 실패 이유 로그 출력
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return receiveMsg;
        }
    }

    View.OnClickListener btnListener = (new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.loginbtn: //로그인 버튼을 눌렀을 경우
                    String loginid = userId.getText().toString();
                    String loginpwd = userPwd.getText().toString();
                    try{
                        String result = new CustomTask().execute(loginid, loginpwd, "login").get();
                        if(result.equals("true")){
                            Toast.makeText(LoginActivity.this, "로그인", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else if(result.equals("false")){
                            Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호가 틀렸음", Toast.LENGTH_SHORT).show();
                            userId.setHint("ID");
                            userId.setHint("Password");
                        }else if(result.equals("noId")){
                            Toast.makeText(LoginActivity.this, "존재하지 않는 아이디", Toast.LENGTH_SHORT).show();
                            userId.setHint("ID");
                            userPwd.setHint("Password");
                        }
                    }catch(Exception e){}
                    break;
                case R.id.registerbtn: //회원가입 버튼을 눌렀을 경우
                    try {
                        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        startActivity(intent);
                    }catch(Exception e){}
                    break;
            }
        }
    });

//    loginbtn.onClickListener btnListener = new View.OnClickListener(){
//        @Override
//        public void onClick(View v) {
//            switch(v.getId()){
//                case R.id.loginbtn: //로그인 버튼 눌렀을 경우
//                    String loginid = idText.getText().toString();
//                    String loginpwd = passwordText.getText().toString();
//                    try{
//                        String result = new CustomTask().execute(loginid, loginpwd, "login", "password");
//                    }
//            }
//        }
//    }
}
