package com.example.healthapplication;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class recordRequest extends StringRequest {
final static private String URL="http://yejin0928.dothome.co.kr/Record.php";
private Map<String,String> map;


    public recordRequest(String user_id, String record_date, String record_time
            , int record_sugar, int record_p1, int record_p2,
                         Response.Listener<String> listener) {
        super(Method.POST,URL, listener,null);
        map=new HashMap<>();
        map.put("user_id",user_id);
        map.put("record_date",record_date);
        map.put("record_time",record_time);
        map.put("record_sugar",record_sugar+"");
        map.put("record_p1",record_p1+"");
        map.put("record_p2",record_p2+"");
    }
    @Override
    protected Map<String,String>getParams() throws AuthFailureError{
        return map;
    }
}
