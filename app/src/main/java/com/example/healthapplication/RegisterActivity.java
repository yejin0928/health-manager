package com.example.healthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_id, et_pass,et_gender,et_age;
    private Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {//액티비티 시작시 실행되는 것
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //id값 찾아주기
        et_id=findViewById(R.id.et_id);
        et_pass=findViewById(R.id.et_pass);
        et_gender=findViewById(R.id.et_gender);
        et_age=findViewById(R.id.et_age);

        //회원가입 버튼 클릭시 수행
        btn_register=findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view){
                //EditText에 현재 입력된 값을 가져온다.
                String userID= et_id.getText().toString();
                String userPass= et_pass.getText().toString();
                String userGender= et_gender.getText().toString();
                int userAge = Integer.parseInt(et_age.getText().toString());

                Response.Listener<String> responseListener= new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success){
                                Toast.makeText(getApplicationContext(), "회원등록 성공", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                        } else{
                            Toast.makeText(getApplicationContext(),"회원등록 실패", Toast.LENGTH_SHORT).show();
                            return;
                            }
                        }
                            catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest=new RegisterRequest(userID, userPass,userGender,userAge,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });

    }
}