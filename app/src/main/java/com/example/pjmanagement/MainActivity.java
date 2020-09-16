package com.example.pjmanagement;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static android.icu.number.NumberFormatter.with;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        TextView idText = (TextView) findViewById(R.id.idText);
//        TextView passwordText = (TextView) findViewById(R.id.passwordText);
        TextView WelcomeMessage = (TextView) findViewById(R.id.WelcomeMessage);

        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



//        EditText edit1 = (EditText) findViewById(R.id.edit1);
//        EditText edit2 = (EditText) findViewById(R.id.edit2);
//        Button loginbtn = (Button) findViewById(R.id.loginbtn);
//        TextView registerbtn = (TextView) findViewById(R.id.registerbtn);
    }
//    class CustomTask extends AsyncTask<String, Void, String> {
//        String sendMsg, receiveMsg;
//
//        @Override
//        protected String doInBackground(String... strings) {
//            try{
//                String str;
//                URL url = new URL("JSP 주소");    //jsp 주소
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
//                conn.setRequestMethod("POST");  //POST 방식으로 데이터 전송
//
//                OutputStreamWriter osw  = new OutputStreamWriter(conn.getOutputStream());
//                sendMsg = "id="+strings[0]+"&pwd="+strings[1];
//                //jsp에 보낼 정보
//
//                osw.write(sendMsg);//OutputStreamWriter에 담아 전송하기
//                osw.flush();
//
//                //jsp와 정상적으로 연동되었을 때
//                if(conn.getResponseCode()== conn.HTTP_OK){
//                    InputStreamReader tmp=new InputStreamReader(conn.getInputStream(), "UTF-8");
//                    BufferedReader reader = new BufferedReader(tmp);
//                    StringBuffer buffer = new StringBuffer();
//
//                    while((str=reader.readLine())!=null){
//                        buffer.append(str);
//                    }
//                    receiveMsg = buffer.toString();
//                }else{
//                    Log.i("통신 결과 ", conn.getResponseCode()+"에러");
//                }
//
//
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            } catch (ProtocolException e) {
//                e.printStackTrace();
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return receiveMsg;
//        }
//    }
}