package com.example.volleyinsertdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Store extends AppCompatActivity {
    TextView or;
    Button sear,men;
    EditText r1;
    int store_number;
    ArrayList arytags=new ArrayList();
    ArrayList arytags_name=new ArrayList();
    private static final String url_sel="http://10.0.2.2/select.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        men = (Button) findViewById(R.id.menu);
        men.setVisibility(View.INVISIBLE);
        sear = (Button) findViewById(R.id.store_sear);
        sear.setOnClickListener(new View.OnClickListener() {
            @Override
            //click
            public void onClick(View view) {
                select();
            }
        });


        men.setOnClickListener(new View.OnClickListener(){
            @Override
            //click
            public void onClick(View view){
                Intent intent=new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("store_ID",store_number);
                intent.putExtras(bundle);
                intent.setClass(Store.this,Menu.class);
                startActivity(intent);
            }
        });
    }
    //get_store_number
    private void select() {
        arytags.clear();
        arytags_name.clear();
        r1=(EditText)findViewById(R.id.r);
        final String name=r1.getText().toString().trim();
        StringRequest request_sel_num=new StringRequest(Request.Method.POST, url_sel, new Response.Listener<String>() {
            @Override
            //success
            public void onResponse(String response) {
                try{
                    //將JSON字串，放到JSONArray中。
                    JSONArray array = new JSONArray(response);
                    if(array.length()>0) {
                        //解出JSON的資料，將所要的資料，再寫入陣列中。
                        JSONObject jsonObject = array.getJSONObject(0);
                        store_number = jsonObject.getInt("store_number");
                        men.setVisibility(View.VISIBLE);
                        select_meal_name(store_number);
                        select_meal(store_number);
                    }else{
                        or = (TextView) findViewById(R.id.t1);
                        or.setText("there are no match names!!");
                    }
                }
                catch(JSONException e) {
                    or = (TextView) findViewById(R.id.t1);
                    or.setText("error");
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
                param.put("t0","get_store_num");
                param.put("t1",name);

                return param;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request_sel_num);

    }
    //get_meal_name
    private void select_meal_name(int store_num) {

        //declare request
        StringRequest request_sel_meal_name=new StringRequest(Request.Method.POST, url_sel, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //將JSON字串，放到JSONArray中。
                    JSONArray array = new JSONArray(response);
                    if (array.length() > 0) {
                        int iter = array.length();
                        //解出JSON的資料，將所要的資料，再寫入陣列中。
                        for (int i = 0; i < iter; i++) {
                            JSONObject jsonObject = array.getJSONObject(i);
                            String meal_name = jsonObject.getString("meal_name");
                            arytags_name.add(meal_name);
                        }
                    }
                } catch (JSONException e) {
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
                param.put("t0","get_meal_info");
                param.put("t1",String.valueOf(store_num));

                return param;
            }
        };

        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request_sel_meal_name);
    }
    //get_store_order
    private void select_meal(int store_num) {
        //declare request
        StringRequest request_sel_meal=new StringRequest(Request.Method.POST, url_sel, new Response.Listener<String>() {
            @Override
            //success
            public void onResponse(String response) {
                try {
                    //將JSON字串，放到JSONArray中。
                    int exact=0;
                    JSONArray array = new JSONArray(response);
                    if (array.length() > 0) {
                        int iter = array.length();
                        exact=iter;
                        //解出JSON的資料，將所要的資料，再寫入陣列中。
                        for (int i = 0; i < iter; i++) {
                            JSONObject jsonObject = array.getJSONObject(i);
                            if(jsonObject.getInt("state")>=3){
                                exact--;
                                continue;
                            }
                            int meal1 = jsonObject.getInt("meal1");
                            arytags.add(meal1);
                            int meal2 = jsonObject.getInt("meal2");
                            arytags.add(meal2);
                            int meal3 = jsonObject.getInt("meal3");
                            arytags.add(meal3);
                            String note = jsonObject.getString("note");
                            arytags.add(note);
                            int price = jsonObject.getInt("total_price");
                            arytags.add(price);
                        }
                        if(exact>4){
                            exact=4;
                        }
                        for (int i = 0; i < exact; i++) {
                            if (i == 0) {
                                or = (TextView) findViewById(R.id.t1);
                            } else if (i == 1) {
                                or = (TextView) findViewById(R.id.t2);
                            } else if (i == 2) {
                                or = (TextView) findViewById(R.id.t3);
                            } else if (i == 3) {
                                or = (TextView) findViewById(R.id.t4);
                            }
                            or.setText((i+1)+".");
                            for(int j=0;j<arytags_name.toArray().length;j++){
                                if ((Integer) arytags.get(5*i+j)>0) {
                                    or.append(arytags_name.get(j)+"*"+arytags.get(5*i+j)+"\n");
                                }
                            }
                            or.append("Note:" + arytags.get(5*i+3));
                            or.append("\nTotal:$" + arytags.get(5*i+4));
                        }
                    }
                    if(exact==0){
                        or = (TextView) findViewById(R.id.t1);
                        or.setText("It's empty now.\nPlease wait!");
                        or = (TextView) findViewById(R.id.t2);
                        or.setText("");
                        or=(TextView) findViewById(R.id.t3);
                        or.setText("");
                        or=(TextView) findViewById(R.id.t4);
                        or.setText("");
                    }
                } catch (JSONException e) {
                    or = (TextView) findViewById(R.id.t1);
                    or.setText("error");
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
                param.put("t0","get_store_order");
                param.put("t1",String.valueOf(store_num));

                return param;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request_sel_meal);
    }

}
