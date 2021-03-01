package com.example.healthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ReportActivity extends AppCompatActivity {
    private TextView report_id,report_age,report_gender,report_sugar,report_pressure,
            report_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Intent intent= getIntent();
        String userID = intent.getStringExtra("userID");
        final String userGender = intent.getStringExtra("userGender");
        final String userAge = intent.getStringExtra("userAge");
        report_id=findViewById(R.id.et_idcheck);
        report_age=findViewById(R.id.et_age);
        report_gender=findViewById(R.id.et_gender);
        report_sugar=findViewById(R.id.et_reportSugar);
        report_pressure=findViewById(R.id.et_reportPressure);





        report_id.setText(userID);
        report_age.setText(userAge);
        report_gender.setText(userGender);
        Response.Listener<String> responseListener=new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject= new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if(success){//로그인 성공
                        String avg_sugar=jsonObject.getString("avg_sugar");
                        String avg_p1=jsonObject.getString("avg_p1");
                        String avg_p2=jsonObject.getString("avg_p2");
                        Toast.makeText(getApplicationContext(), "분석 성공", Toast.LENGTH_SHORT).show();
                        report_sugar.setText(avg_sugar);
                        double result_sugar=Double.parseDouble(avg_sugar)*0.02-0.05*Double.parseDouble(userGender)+
                                0.007*Double.parseDouble(userAge)-0.85;
                        double result_pressure=0.04*Double.parseDouble(userGender)+0.02*Double.parseDouble(userAge)
                                -0.002*Double.parseDouble(avg_p1)+0.06*Double.parseDouble(avg_p2)-3.5;

                        String r1=String.valueOf(result_sugar);
                        String r2=String.valueOf(result_pressure);

                        if(result_sugar>-10&&result_sugar<1.5){
                            r1="정상";
                        }
                        else if(result_sugar>=1.5&&result_sugar<2.5){
                            r1="공복혈당장애";
                        }
                        else if(result_sugar>=2.5){
                            r1="당뇨병";
                        }

                        if(result_pressure>-10&&result_pressure<1.5){
                            r2="정상";
                        }
                        else if(result_pressure>=1.5&&result_pressure<2.5){
                            r2="고혈압전단계";
                        }
                        else if(result_pressure>=2.5){
                            r2="고혈압";
                        }


                        report_sugar.setText(r1);

                        report_pressure.setText(r2);
                        //고혈압
                        //y(고혈압유병률)=0.04*sex+0.02*age-0.002*수축기+0.06*이완기-3.5
                        //잘나옴

                        //당뇨
                        //y(당뇨유병률)=-0.05*sex+0.007*age+혈당*0.02+-0.85
                        //잘 나옴
                    } else{
                        Toast.makeText(getApplicationContext(),"분석 실패", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        ReportRequest reportRequest=new ReportRequest(userID,responseListener);
        RequestQueue queue= Volley.newRequestQueue(ReportActivity.this);
        queue.add(reportRequest);
    }
}