package com.example.healthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv_id;
    Button intent_btn1,intent_btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_id=findViewById(R.id.tv_id);
       // tv_pass=findViewById(R.id.tv_pass);



        Intent intent = getIntent();
        final String userID = intent.getStringExtra("userID");
        final String userGender = intent.getStringExtra("userGender");
        final String userAge = intent.getStringExtra("userAge");

       // String userPass = intent.getStringExtra("userPass");

        tv_id.setText(userID);
       // tv_pass.setText(userPass);
        intent_btn1=findViewById(R.id.btn_1);
        intent_btn2=findViewById(R.id.btn_2);
        //intent_btn3=findViewById(R.id.btn_3);
        intent_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,record.class);
                intent.putExtra("userID",userID);
                startActivity(intent);
            }
        });
        intent_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(MainActivity.this,ReportActivity.class);
                intent1.putExtra("userID",userID);
                intent1.putExtra("userGender",userGender);
                intent1.putExtra("userAge",userAge);
                startActivity(intent1);
            }
        });


    }
}