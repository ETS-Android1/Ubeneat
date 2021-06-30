package com.example.volleyinsertdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class MainActivity extends AppCompatActivity{
    EditText r1,r2,r3;
    Button sub2,deli,stor;
    ArrayList<Object> arytags=new ArrayList<Object>();
    private static final String url_ins="http://10.0.2.2/insert.php";
    private static final String url_sel="http://140.118.115.8/select.php";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sub2=(Button)findViewById(R.id.sub);
        sub2.setOnClickListener(new View.OnClickListener(){
            @Override
            //click
            public void onClick(View view){
                select();
            }
        });
        deli=(Button)findViewById(R.id.deliver);
        deli.setOnClickListener(new View.OnClickListener(){
            @Override
            //click
            public void onClick(View view){
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,Deliver.class);
                startActivity(intent);
            }
        });
        stor=(Button)findViewById(R.id.store);
        stor.setOnClickListener(new View.OnClickListener(){
            @Override
            //click
            public void onClick(View view){
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,Store.class);
                startActivity(intent);
            }
        });
    }
    //get_customer_number
    private void select() {
        //positioning
        r1=(EditText)findViewById(R.id.r1);
        r2=(EditText)findViewById(R.id.r2);
        r3=(EditText)findViewById(R.id.r3);

        //get value
        final String name=r1.getText().toString().trim();
        final String num=r2.getText().toString().trim();
        final String address=r3.getText().toString().trim();
        //declare request
        StringRequest request_sel=new StringRequest(Request.Method.POST, url_sel, new Response.Listener<String>() {
            @Override
            //success
            public void onResponse(String response) {
                try{
                    //將JSON字串，放到JSONArray中。
                    JSONArray array = new JSONArray(response);
                    if(array.length()>0) {
                        //解出JSON的資料，將所要的資料，再寫入陣列中。
                        JSONObject jsonObject = array.getJSONObject(0);
                        int customer_num = jsonObject.getInt("customer_number");
                        arytags.add(customer_num);  //.get(idx) means customer_num[idx]
                        Toast.makeText(getApplicationContext(), "login success",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent();
                        //實例化一個Bundle物件
                        Bundle bundle = new Bundle();
                        //儲存資料　第一個為參數key，第二個為Value
                        bundle.putInt("ID",customer_num);
                        // 記得put進去，不然資料不會帶過去哦
                        intent.putExtras(bundle);
                        intent.setClass(MainActivity.this,MainActivity3.class);
                        startActivity(intent);
                    }else{
                        insert();
                    }
                }
                catch(JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            //fail
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            //json format
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param=new HashMap<String,String>();
                param.put("t0","get_customer_number");
                param.put("t1",name);
                param.put("t2",num);
                param.put("t3",address);

                return param;
            }
        };
        boolean empty=r1.getText().toString().isEmpty() || r2.getText().toString().isEmpty() || r3.getText().toString().isEmpty();
        if(empty){
            Toast.makeText(getApplicationContext(),"empty input",Toast.LENGTH_LONG).show();
        }else {
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(request_sel);
        }
    }
    //insert customer info
    private void insert() {
        //positioning
        r1=(EditText)findViewById(R.id.r1);
        r2=(EditText)findViewById(R.id.r2);
        r3=(EditText)findViewById(R.id.r3);

        //get value
        final String name=r1.getText().toString().trim();
        final String num=r2.getText().toString().trim();
        final String address=r3.getText().toString().trim();

        //declare request
        StringRequest request_ins=new StringRequest(Request.Method.POST, url_ins, new Response.Listener<String>() {
            @Override
            //success
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            //fail
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            //json format
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param=new HashMap<String,String>();
                param.put("t0","customer");
                param.put("t1",name);
                param.put("t2",num);
                param.put("t3",address);

                return param;
            }
        };

        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request_ins);
        try {
            Thread.sleep(300); //1000為1秒
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        select();
    }
}

