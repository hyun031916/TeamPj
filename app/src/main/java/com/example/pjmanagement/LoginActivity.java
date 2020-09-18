package com.example.pjmanagement;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginbtn = (Button) findViewById(R.id.loginbtn);
        EditText idText = (EditText) findViewById(R.id.edit1);
        EditText passwordText = (EditText) findViewById(R.id.edit2);

        loginbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    String result;
                    CustomTask task = new CustomTask();
                    result = task.execute("rain483", "1234").get();
                    Log.i("리턴 값", result);
                }catch(Exception e){

                }
            }
        });



        TextView registerbtn = (TextView) findViewById(R.id.registerbtn);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
    }
    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;

        @Override
        protected String doInBackground(String... strings) {
            try{
                String str;
                URL url = new URL("http://localhost:8090/teamProject/login.jsp");    //jsp 주소
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");  //POST 방식으로 데이터 전송

                OutputStreamWriter osw  = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&pwd="+strings[1];
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
