package com.example.administrator.zcbook.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.zcbook.R;
import com.example.administrator.zcbook.util.HttpUtil;
import com.example.administrator.zcbook.util.JsonUtil;

import org.json.JSONException;



/**
 * Created by Administrator on 2016/7/4.
 */
public class LoginActivity extends Activity implements View.OnClickListener{
    public static final String TAG="LoginActivity";
    public static final int LoginCode=0;
    private EditText etUserName,etPassword;
    private Button btnLogin,btnReset;

    Handler handler=new Handler() {
        public void handleMessage(Message msg){
            Log.i(TAG,"handleMessage"+msg.arg1);
            if(msg.arg1==1){
                Log.i(TAG,"跳转之前");
                goToMainActivity();
                Log.i(TAG, "跳转之后");
            }

        }
    };

   public void goToMainActivity(){
       Intent intent=new Intent(this,MainActivity.class);
       startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        etUserName= (EditText) findViewById(R.id.editText_UserName);
        etPassword= (EditText) findViewById(R.id.editText_password);
        btnLogin= (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        btnReset= (Button) findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                Log.i(TAG,"登录点击");
                final String userName=etUserName.getText().toString().trim();
                final String password=etPassword.getText().toString().trim();
                if (userName==null){
                    Toast.makeText(this,"请输入用户名",Toast.LENGTH_SHORT).show();

                }else if(password==null){
                Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
                 }
                else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String params="method=login&userName="+userName+"&password="+password;
                            String resultJson=HttpUtil.getRequest(params);
                            if(resultJson==null){
                                Log.i(TAG,"连接返回失败");
                            }else{
                                try {
                                   String result=new JsonUtil().parseJsonLogin(resultJson);
                                    Message message=new Message();
                                    message.arg1=Integer.parseInt(result);
                                    handler.sendMessage(message);
                                    Log.i(TAG,"sendMessage"+message.arg1);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();

                }
                break;
        }
    }
}
