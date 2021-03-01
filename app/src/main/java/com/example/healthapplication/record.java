package com.example.healthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class record extends AppCompatActivity {
    private Spinner spinner;
    private TextView tv_result,mtxtDate,tv_id;
    private EditText et_sugar, et_p1, et_p2;
    private Button btn_record;
    private DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        tv_id=findViewById(R.id.textView3);
        spinner=(Spinner)findViewById(R.id.spinner);
        tv_result=(TextView)findViewById(R.id.tv_test);
        datePicker=(DatePicker)findViewById(R.id.dataPicker);
        mtxtDate=(TextView)findViewById(R.id.txtdate);
        et_sugar=findViewById(R.id.et_sugar);
        et_p1=findViewById(R.id.et_p1);
        et_p2=findViewById(R.id.et_p2);
        btn_record=findViewById(R.id.btn_record);
        Intent intent= getIntent();
        String userID = intent.getStringExtra("userID");
        tv_id.setText(userID);
        int month=datePicker.getMonth();

        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mtxtDate.setText(String.format("%d%d%d",year,monthOfYear+1,dayOfMonth));
                    }
                });
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        tv_result.setText(adapterView.getItemAtPosition(i).toString());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EditText에 현재 입력된 값을 가져온다
                final String user_id=tv_id.getText().toString();
                String record_date= mtxtDate.getText().toString();
                String record_time= tv_result.getText().toString();
                int record_sugar = Integer.parseInt(et_sugar.getText().toString());
                int record_p1 = Integer.parseInt(et_p1.getText().toString());
                int record_p2 = Integer.parseInt(et_p2.getText().toString());

                Response.Listener<String> responseListener= new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success){
                                Toast.makeText(getApplicationContext(), "등록 성공", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(record.this, MainActivity.class);
                                intent.putExtra("userID",user_id);
                                startActivity(intent);
                            } else{
                                Toast.makeText(getApplicationContext(),"등록 실패", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                recordRequest recordRe=new recordRequest(user_id,record_date, record_time,record_sugar,record_p1,record_p2,responseListener);
                RequestQueue queue = Volley.newRequestQueue(record.this);
                queue.add(recordRe);
            }
        });






    }
}