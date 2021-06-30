package com.example.volleyinsertdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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

public class MainActivity3 extends AppCompatActivity {
    Button sear,back,textsear;
    ImageButton jap,chin,kor,dri;
    EditText r;
    TextView t;
    ArrayList arytags=new ArrayList();
    private static final String url_sel="http://10.0.2.2/select.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Bundle bundle = getIntent().getExtras();
        int customer_num = bundle.getInt("ID");
        t=(TextView) findViewById(R.id.textView);
        textsear= (Button) findViewById(R.id.textsearch);
        textsear.setOnClickListener(new View.OnClickListener(){
            @Override
            //click
            public void onClick(View view){
                select();
            }
        });
        back= (Button) findViewById(R.id.goback);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            //click
            public void onClick(View view){
                Intent intent=new Intent();
                intent.setClass(MainActivity3.this,MainActivity.class);
                startActivity(intent);
            }
        });
        jap= (ImageButton) findViewById(R.id.jap);
        jap.setOnClickListener(new View.OnClickListener(){
            @Override
            //click
            public void onClick(View view){
                Intent intent=new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("ID",customer_num);
                intent.putExtras(bundle);
                intent.setClass(MainActivity3.this,Jap.class);
                startActivity(intent);
            }
        });
        chin= (ImageButton) findViewById(R.id.chin);
        chin.setOnClickListener(new View.OnClickListener(){
            @Override
            //click
            public void onClick(View view){
                Intent intent=new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("ID",customer_num);
                intent.putExtras(bundle);
                intent.setClass(MainActivity3.this,Chin.class);
                startActivity(intent);
            }
        });
        kor= (ImageButton) findViewById(R.id.kor);
        kor.setOnClickListener(new View.OnClickListener(){
            @Override
            //click
            public void onClick(View view){
                Intent intent=new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("ID",customer_num);
                intent.putExtras(bundle);
                intent.setClass(MainActivity3.this,Kor.class);
                startActivity(intent);
            }
        });
        dri= (ImageButton) findViewById(R.id.dri);
        dri.setOnClickListener(new View.OnClickListener(){
            @Override
            //click
            public void onClick(View view){
                Intent intent=new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("ID",customer_num);
                intent.putExtras(bundle);
                intent.setClass(MainActivity3.this, Drink.class);
                startActivity(intent);
            }
        });
        sear= (Button) findViewById(R.id.search);
        sear.setOnClickListener(new View.OnClickListener(){
            @Override
            //click
            public void onClick(View view){
                Intent intent=new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("ID",customer_num);
                intent.putExtras(bundle);
                intent.setClass(MainActivity3.this,Order.class);
                startActivity(intent);
            }
        });
    }
    //get_meal_search
    private void select() {
        arytags.clear();
        t.setText("");
        r=(EditText)findViewById(R.id.r);
        final String name=r.getText().toString().trim();
        StringRequest request_sel=new StringRequest(Request.Method.POST, url_sel, new Response.Listener<String>() {
            @Override
            //success
            public void onResponse(String response) {
                try{
                    //將JSON字串，放到JSONArray中。
                    JSONArray array = new JSONArray(response);
                    if(array.length()>0) {
                        int iter=array.length();
                        for(int i=0;i<iter;i++){
                            JSONObject jsonObject = array.getJSONObject(i);
                            String store_name = jsonObject.getString("store_name");
                            String type= jsonObject.getString("type");
                            String meal_name= jsonObject.getString("meal_name");
                            t.append((i+1)+". "+type+"->"+store_name+"->"+meal_name+"\n");
                        }
                    }else{
                        t.append("sorry! no match meal");
                    }
                }
                catch(JSONException e) {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
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
                param.put("t0","get_meal_search");
                param.put("t1",name);

                return param;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request_sel);
    }



}