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

public class Cart extends AppCompatActivity {
    TextView order,addr;
    Button sub;
    EditText hint;
    ArrayList<Object> arytags=new ArrayList<Object>();
    private static final String url_sel="http://10.0.2.2/select.php";
    private static final String url_ins="http://10.0.2.2/insert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Bundle bundle = getIntent().getExtras();
        int customer_num = bundle.getInt("ID");
        int store = bundle.getInt("store");
        int c1 = bundle.getInt("c1");
        int c2 = bundle.getInt("c2");
        int c3 = bundle.getInt("c3");
        int it = bundle.getInt("item");
        int val = bundle.getInt("value");
        order=(TextView) findViewById(R.id.order);
        addr=(TextView) findViewById(R.id.addr);
        order.setText("Your oder list：");
        switch (store){
            case 1:
                if(c1>0) {
                    order.append("\nSashimi\n$200 * " + c1);
                }
                if(c2>0) {
                    order.append("\nSalmon Donburi\n$120 * " + c2);
                }
                if(c3>0) {
                    order.append("\nComprehensive Sushi\n$120 * " + c3);
                }
                order.append("\n"+it+" item(s)       Total:$ "+val);
                break;
            case 2:
                if(c1>0) {
                    order.append("\nXiao Long Bao\n$160 * " + c1);
                }
                if(c2>0) {
                    order.append("\nFried rice\n$100 * " + c2);
                }
                if(c3>0) {
                    order.append("\n炸春捲\n$80 * " + c3);
                }
                order.append("\n"+it+" item(s)       Total:$ "+val);
                break;
            case 3:
                if(c1>0) {
                    order.append("\nBibimbap\n$160 * " + c1);
                }
                if(c2>0) {
                    order.append("\nKorean Fried Chicken Rice\n$140 * " + c2);
                }
                if(c3>0) {
                    order.append("\nKimchi\n$50 * " + c3);
                }
                order.append("\n"+it+" item(s)       Total:$ "+val);
                break;
            case 4:
                if(c1>0) {
                    order.append("\nBubble Milk Tea\n$50 * " + c1);
                }
                if(c2>0) {
                    order.append("\nOrange Juice\n$40 * " + c2);
                }
                if(c3>0) {
                    order.append("\nLemon Winter Melon\n$50 * " + c3);
                }
                order.append("\n"+it+" item(s)       Total:$ "+val);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + store);
        }
        select(customer_num);
        sub=(Button)findViewById(R.id.sub);
        sub.setOnClickListener(new View.OnClickListener(){
            @Override
            //click
            public void onClick(View view){

                insert(customer_num,store,c1,c2,c3,val);
                Intent intent=new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("ID",customer_num);
                intent.putExtras(bundle);
                intent.setClass(Cart.this,MainActivity3.class);
                startActivity(intent);
            }
        });

    }
    //get_customer_info
    private void select(int customer_num) {

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
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonObject = array.getJSONObject(i);
                            String name = jsonObject.getString("name");
                            arytags.add(name);  //.get(idx) means customer_num[idx]
                            addr.setText("Your information:");
                            addr.append("\nName:"+name);
                            String phone_num = jsonObject.getString("phone");
                            arytags.add(phone_num);
                            addr.append("\nPhone:"+phone_num);
                            String address = jsonObject.getString("customer_address");
                            arytags.add(address);
                            addr.append("\nAddress:"+address);
                        }
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
                param.put("t0","get_customer_info");
                param.put("t1", String.valueOf(customer_num));

                return param;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request_sel);
    }
    //insert_order
    private void insert(int customer_num, int store, int c1, int c2, int c3, int val) {
        //positioning
        hint=(EditText)findViewById(R.id.r3);
        //get value
        final String note=hint.getText().toString().trim();
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
                param.put("t0","orderlist");
                param.put("t1",String.valueOf(customer_num));
                param.put("t2",String.valueOf(store));
                param.put("t3",String.valueOf(c1));
                param.put("t4",String.valueOf(c2));
                param.put("t5",String.valueOf(c3));
                param.put("t6",note);
                param.put("t7",String.valueOf(val));
                param.put("t8",String.valueOf(1));


                return param;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request_ins);
    }
}