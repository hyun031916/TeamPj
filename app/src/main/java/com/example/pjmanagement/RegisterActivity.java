package com.example.pjmanagement;

import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {
    EditText userId, userPwd, userName;
    Button register;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userId = (EditText) findViewById(R.id.userId);
//        EditText passwordText = (EditText) findViewById(R.id.passwordText);
//        EditText passwordText = (EditText) findViewById(R.id.passwordText);
        userPwd = (EditText) findViewById(R.id.userPwd);
        userName = (EditText) findViewById(R.id.userName);
        register = (Button) findViewById(R.id.registerbtn);

        register.setOnClickListener(btnListener);
    }
    static class CustomTask extends AsyncTask<String, Void, String>{
        String sendMsg, receiveMsg;

        @Override
        protected String doInBackground(String... strings) {
            try{
                String str;
                URL url = new URL("http://10.0.2.2:8000/team_project/join.jsp");
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");

                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "userId="+strings[0]+"&userPwd="+strings[1]+"&userName="+strings[2];
                osw.write(sendMsg);
                osw.flush();
                if(conn.getResponseCode()== HttpURLConnection.HTTP_OK){
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuilder buffer = new StringBuilder();
                    while((str = reader.readLine())!=null){
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                }else{
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return receiveMsg;
        }
    }
    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String joinid = userId.getText().toString();
            String joinpwd = userPwd.getText().toString();
            String joinname = userName.getText().toString();

            try {
                String result = new CustomTask().execute(joinid, joinpwd, joinname).get();
                if (result.equals("id")) {
                    Toast.makeText(RegisterActivity.this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                    userId.setText("");
                    userPwd.setText("");
                    userName.setText("");
                } else if (result.equals("ok")) {
                    userId.setText("");
                    userPwd.setText("");
                    userName.setText("");
                    Toast.makeText(RegisterActivity.this, "회원가입을 축하합니다.", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ignored) {}
        }
    };
}
