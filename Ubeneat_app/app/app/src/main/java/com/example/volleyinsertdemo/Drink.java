package com.example.volleyinsertdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Drink extends AppCompatActivity {
    Button cart;
    Button plus1,plus2,plus3,red1,red2,red3;
    TextView or,f1,f2,f3,item,value;
    ImageView img1,img2,img3;
    ArrayList<Object> arytags=new ArrayList<Object>();
    private static final String url_sel="http://10.0.2.2/select.php";
    int c1,c2,c3,it,val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        Bundle bundle = getIntent().getExtras();
        int customer_num = bundle.getInt("ID");
        int store_num=4;
        cart= (Button) findViewById(R.id.search);
        plus1= (Button) findViewById(R.id.c4);
        plus2= (Button) findViewById(R.id.c5);
        plus3= (Button) findViewById(R.id.c6);
        red1= (Button) findViewById(R.id.c1);
        red2= (Button) findViewById(R.id.c2);
        red3= (Button) findViewById(R.id.in);
        f1= (TextView) findViewById(R.id.f1);
        f2= (TextView) findViewById(R.id.f2);
        f3= (TextView) findViewById(R.id.f3);
        img1=(ImageView) findViewById(R.id.image1);
        img2=(ImageView) findViewById(R.id.image2);
        img3=(ImageView) findViewById(R.id.image3);
        item= (TextView) findViewById(R.id.item);
        value= (TextView) findViewById(R.id.value);
        select(store_num);

        plus1.setOnClickListener(new View.OnClickListener(){
            @Override
            //click
            public void onClick(View view){
                c1+=1;
                it+=1;
                val+=(Integer)arytags.get(1);
                f1.setText(String.valueOf(c1));
                item.setText(String.valueOf(it));
                value.setText('$'+String.valueOf(val));
            }
        });
        plus2.setOnClickListener(new View.OnClickListener(){
            @Override
            //click
            public void onClick(View view){
                c2+=1;
                it+=1;
                val+=(Integer)arytags.get(4);
                f2.setText(String.valueOf(c2));
                item.setText(String.valueOf(it));
                value.setText('$'+String.valueOf(val));
            }
        });
        plus3.setOnClickListener(new View.OnClickListener(){
            @Override
            //click
            public void onClick(View view){
                c3+=1;
                it+=1;
                val+=(Integer)arytags.get(7);
                f3.setText(String.valueOf(c3));
                item.setText(String.valueOf(it));
                value.setText('$'+String.valueOf(val));
            }
        });
        red1.setOnClickListener(new View.OnClickListener(){
            @Override
            //click
            public void onClick(View view){
                if(c1>0) {
                    c1 -= 1;
                    it -= 1;
                    val-=(Integer)arytags.get(1);
                    f1.setText(String.valueOf(c1));
                    item.setText(String.valueOf(it));
                    value.setText('$' + String.valueOf(val));
                }
            }
        });
        red2.setOnClickListener(new View.OnClickListener(){
            @Override
            //click
            public void onClick(View view){
                if(c2>0) {
                    c2 -= 1;
                    it -= 1;
                    val-=(Integer)arytags.get(4);
                    f2.setText(String.valueOf(c2));
                    item.setText(String.valueOf(it));
                    value.setText('$' + String.valueOf(val));
                }
            }
        });
        red3.setOnClickListener(new View.OnClickListener(){
            @Override
            //click
            public void onClick(View view){
                if(c3>0) {
                    c3 -= 1;
                    it -= 1;
                    val-=(Integer)arytags.get(7);
                    f3.setText(String.valueOf(c3));
                    item.setText(String.valueOf(it));
                    value.setText('$' + String.valueOf(val));
                }
            }
        });
        cart.setOnClickListener(new View.OnClickListener(){
            @Override
            //click
            public void onClick(View view){
                if(it>0) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putInt("ID", customer_num);
                    bundle.putInt("store", 4);
                    bundle.putInt("c1", c1);
                    bundle.putInt("c2", c2);
                    bundle.putInt("c3", c3);
                    bundle.putInt("item", it);
                    bundle.putInt("value", val);
                    intent.putExtras(bundle);
                    intent.setClass(Drink.this, Cart.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"empty order",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    //get_meal_info
    private void select(int store_num) {
        //declare request
        StringRequest request_sel=new StringRequest(Request.Method.POST, url_sel, new Response.Listener<String>() {
            @Override
            //success
            public void onResponse(String response) {
                try{
                    //將JSON字串，放到JSONArray中。
                    JSONArray array = new JSONArray(response);
                    if(array.length()>0) {
                        int iter=array.length();
                        //解出JSON的資料，將所要的資料，再寫入陣列中。
                        for (int i = 0; i < iter; i++) {
                            JSONObject jsonObject = array.getJSONObject(i);
                            String name = jsonObject.getString("meal_name");
                            arytags.add(name);  //.get(idx) means customer_num[idx]
                            int price = jsonObject.getInt("meal_price");
                            arytags.add(price);
                            String url = jsonObject.getString("image");
                            arytags.add(url);
                        }
                        if(iter>3){
                            iter=3;
                        }
                        for (int i = 0; i < iter; i++) {
                            if(i==0) {
                                plus1.setVisibility(View.VISIBLE);
                                red1.setVisibility(View.VISIBLE);
                                f1.setText("0");
                                plus2.setVisibility(View.INVISIBLE);
                                red2.setVisibility(View.INVISIBLE);
                                plus3.setVisibility(View.INVISIBLE);
                                red3.setVisibility(View.INVISIBLE);
                                or=(TextView) findViewById(R.id.text1);
                                Picasso.get().load((String) arytags.get(3 * i + 2)).resize(200, 150).into(img1);
                            }else if(i==1){
                                plus2.setVisibility(View.VISIBLE);
                                red2.setVisibility(View.VISIBLE);
                                f2.setText("0");
                                or=(TextView) findViewById(R.id.text2);
                                Picasso.get().load((String) arytags.get(3 * i + 2)).resize(200, 150).into(img2);
                            }else if(i==2){
                                plus3.setVisibility(View.VISIBLE);
                                red3.setVisibility(View.VISIBLE);
                                f3.setText("0");
                                or=(TextView) findViewById(R.id.text3);
                                Picasso.get().load((String) arytags.get(3 * i + 2)).resize(200, 150).into(img3);
                            }
                            or.setText("" + arytags.get(3*i));
                            or.append("\n$" + arytags.get(3*i+1));
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
                param.put("t0","get_meal_info");
                param.put("t1", String.valueOf(store_num));

                return param;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request_sel);
    }
}