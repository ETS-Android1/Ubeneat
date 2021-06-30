package com.example.volleyinsertdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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


public class Order extends AppCompatActivity {
    TextView or;
    Button del,up,done,sur;
    CheckBox ch1,ch2;
    EditText e1,e2,e3,e4;
    ArrayList arytags=new ArrayList();
    ArrayList arytags_name1=new ArrayList();
    ArrayList arytags_name2=new ArrayList();
    private static final String url_del="http://10.0.2.2/delete.php";
    private static final String url_sel="http://10.0.2.2/select.php";
    private static final String url_up="http://10.0.2.2/update.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Bundle bundle = getIntent().getExtras();
        int customer_num = bundle.getInt("ID");
        ch1=(CheckBox) findViewById(R.id.checkBox);
        ch2=(CheckBox) findViewById(R.id.checkBox2);
        e1=(EditText) findViewById(R.id.ed1);
        e2=(EditText) findViewById(R.id.ed2);
        e3=(EditText) findViewById(R.id.ed3);
        e4=(EditText) findViewById(R.id.ed4);
        select_order(customer_num);
        del=(Button)findViewById(R.id.delete);
        up=(Button)findViewById(R.id.update);
        done=(Button)findViewById(R.id.done);
        sur=(Button)findViewById(R.id.sure);
        e1.setVisibility(View.INVISIBLE);
        e2.setVisibility(View.INVISIBLE);
        e3.setVisibility(View.INVISIBLE);
        e4.setVisibility(View.INVISIBLE);
        sur.setVisibility(View.INVISIBLE);
        //delete arytags[15] first cause it will be clear.
        ch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ch2.setChecked(false);
                    //click second
                    del.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //click
                        public void onClick(View view) {
                            if((Integer) arytags.get(6)<3) {
                                delete((Integer) arytags.get(7));
                                or = (TextView) findViewById(R.id.order);
                                or.setText("");
                                or = (TextView) findViewById(R.id.order2);
                                or.setText("");
                                try {
                                    Thread.sleep(300); //1000為1秒
                                } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                select_order(customer_num);
                                ch1.setChecked(false);
                            }else{
                                Toast.makeText(getApplicationContext(),"the order was picked up",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    done.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //click
                        public void onClick(View view) {
                            if((Integer) arytags.get(6)==4) {
                                update_state((Integer) arytags.get(7));
                                or=(TextView) findViewById(R.id.order);
                                or.setText("");
                                or=(TextView) findViewById(R.id.order2);
                                or.setText("");
                                try {
                                    Thread.sleep(300); //1000為1秒
                                } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                select_order(customer_num);
                                ch1.setChecked(false);
                            }else{
                                Toast.makeText(getApplicationContext(),"make sure you got the food",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    up.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //click
                        public void onClick(View view) {
                            if((Integer) arytags.get(6)<3) {
                                for(int j=0;j<arytags_name1.toArray().length/2;j++){
                                    if (j==0) {
                                        e1.setVisibility(View.VISIBLE);
                                        e1.setHint((String)arytags_name1.get(0));
                                    }else if(j==1) {
                                        e2.setVisibility(View.VISIBLE);
                                        e2.setHint((String)arytags_name1.get(2));
                                    }else if(j==2) {
                                        e3.setVisibility(View.VISIBLE);
                                        e3.setHint((String)arytags_name1.get(4));
                                    }
                                }
                                e4.setVisibility(View.VISIBLE);
                                e4.setHint(""+arytags.get(5));
                                sur.setVisibility(View.VISIBLE);
                                sur.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    //click
                                    public void onClick(View view) {

                                        update_order((Integer) arytags.get(7), 0);
                                        or = (TextView) findViewById(R.id.order);
                                        or.setText("");
                                        or = (TextView) findViewById(R.id.order2);
                                        or.setText("");
                                        try {
                                            Thread.sleep(300); //1000為1秒
                                        } catch (InterruptedException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                        select_order(customer_num);
                                        ch1.setChecked(false);
                                        e1.setVisibility(View.INVISIBLE);
                                        e2.setVisibility(View.INVISIBLE);
                                        e3.setVisibility(View.INVISIBLE);
                                        e4.setVisibility(View.INVISIBLE);
                                        sur.setVisibility(View.INVISIBLE);
                                    }
                                });
                            }else{
                                Toast.makeText(getApplicationContext(),"the order was picked up",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
        ch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //判斷CheckBox是否有勾選，同mCheckBox.isChecked()
                if (isChecked) {
                    ch1.setChecked(false);
                    del.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //click
                        public void onClick(View view) {
                            if((Integer) arytags.get(14)<3) {
                                delete((Integer) arytags.get(15));
                                //ch2.setChecked(false);
                                or = (TextView) findViewById(R.id.order);
                                or.setText("");
                                or = (TextView) findViewById(R.id.order2);
                                or.setText("");
                                try {
                                    Thread.sleep(300); //1000為1秒
                                } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                select_order(customer_num);
                                ch2.setChecked(false);
                            }else{
                                Toast.makeText(getApplicationContext(),"the order was picked up",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    done.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //click
                        public void onClick(View view) {
                            if((Integer) arytags.get(14)==4) {
                                update_state((Integer) arytags.get(15));
                                or = (TextView) findViewById(R.id.order);
                                or.setText("");
                                or = (TextView) findViewById(R.id.order2);
                                or.setText("");
                                try {
                                    Thread.sleep(300); //1000為1秒
                                } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                select_order(customer_num);
                                ch2.setChecked(false);
                            }else{
                                Toast.makeText(getApplicationContext(),"make sure you got the food",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    up.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //click
                        public void onClick(View view) {
                            if((Integer) arytags.get(14)<3) {
                                for(int j=0;j<arytags_name2.toArray().length/2;j++){
                                    if (j==0) {
                                        e1.setVisibility(View.VISIBLE);
                                        e1.setHint((String)arytags_name2.get(0));
                                    }else if(j==1) {
                                        e2.setVisibility(View.VISIBLE);
                                        e2.setHint((String)arytags_name2.get(2));
                                    }else if(j==2) {
                                        e3.setVisibility(View.VISIBLE);
                                        e3.setHint((String)arytags_name2.get(4));
                                    }
                                }
                                e4.setVisibility(View.VISIBLE);
                                e4.setHint(""+arytags.get(13));
                                sur.setVisibility(View.VISIBLE);
                                sur.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    //click
                                    public void onClick(View view) {

                                        update_order((Integer) arytags.get(15), 1);
                                        or = (TextView) findViewById(R.id.order);
                                        or.setText("");
                                        or = (TextView) findViewById(R.id.order2);
                                        or.setText("");
                                        try {
                                            Thread.sleep(300); //1000為1秒
                                        } catch (InterruptedException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                        select_order(customer_num);
                                        ch2.setChecked(false);
                                        e1.setVisibility(View.INVISIBLE);
                                        e2.setVisibility(View.INVISIBLE);
                                        e3.setVisibility(View.INVISIBLE);
                                        e4.setVisibility(View.INVISIBLE);
                                        sur.setVisibility(View.INVISIBLE);
                                    }
                                });
                            }else{
                                Toast.makeText(getApplicationContext(),"the order was picked up",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
    //get_customer_order
    private void select_order(int customer_num) {
        //declare request
        arytags.clear();
        arytags_name1.clear();
        arytags_name2.clear();
        StringRequest request_sel=new StringRequest(Request.Method.POST, url_sel, new Response.Listener<String>() {
            @Override
            //success
            public void onResponse(String response) {
                try{
                    //將JSON字串，放到JSONArray中。
                    int exact=0;
                    JSONArray array = new JSONArray(response);
                    if(array.length()>0) {
                        int iter=array.length();
                        exact=iter;
                        //解出JSON的資料，將所要的資料，再寫入陣列中。
                        for (int i = 0; i < iter; i++) {
                            JSONObject jsonObject = array.getJSONObject(i);
                            if(jsonObject.getInt("state")==5){
                                exact--;
                                continue;
                            }
                            int store = jsonObject.getInt("store_num");
                            arytags.add(store);  //.get(idx) means customer_num[idx]
                            int m1 = jsonObject.getInt("meal1");
                            arytags.add(m1);
                            int m2 = jsonObject.getInt("meal2");
                            arytags.add(m2);
                            int m3 = jsonObject.getInt("meal3");
                            arytags.add(m3);
                            int price = jsonObject.getInt("total_price");
                            arytags.add(price);
                            String note = jsonObject.getString("note");
                            arytags.add(note);
                            int state = jsonObject.getInt("state");
                            arytags.add(state);
                            int order_num = jsonObject.getInt("order_num");
                            arytags.add(order_num);
                        }
                        if(exact>2){
                            exact=2;
                        }
                        for (int i = 0; i < exact; i++) {
                            if (i == 0) {
                                select_meal((Integer) arytags.get(8*i),0);
                            } else if (i == 1) {
                                select_meal((Integer) arytags.get(8*i),1);
                            }
                            try {
                                Thread.sleep(300); //1000為1秒
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                    if(exact==0){
                        ch1.setVisibility(View.INVISIBLE);
                        ch2.setVisibility(View.INVISIBLE);
                        or=(TextView) findViewById(R.id.order);
                        or.setText("It's empty now.\nGo order some food!");
                        or=(TextView) findViewById(R.id.order2);
                        or.setText("");
                    }
                }
                catch(JSONException e) {
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
                param.put("t0","get_customer_order");
                param.put("t1", String.valueOf(customer_num));

                return param;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request_sel);
    }
    //get_meal_info
    private void select_meal(int store_num,int text) {
        //declare request
        StringRequest request_sel_name=new StringRequest(Request.Method.POST, url_sel, new Response.Listener<String>() {
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
                            String meal_name = jsonObject.getString("meal_name");
                            int price = jsonObject.getInt("meal_price");
                            if(text==0) {
                                arytags_name1.add(meal_name);
                                arytags_name1.add(price);
                            }else if(text==1) {
                                arytags_name2.add(meal_name);
                                arytags_name2.add(price);
                            }
                        }
                        if(text==0) {
                            ch1.setVisibility(View.VISIBLE);
                            ch2.setVisibility(View.INVISIBLE);
                            or = (TextView) findViewById(R.id.order);
                            for (int j = 0; j < iter; j++) {
                                if ((Integer) arytags.get(8 * text + 1 + j) > 0) {
                                    or.append(arytags_name1.get(2*j) + "*" + arytags.get(8 * text + 1 + j) + "\n");
                                }
                            }
                        }else if(text==1) {
                            ch2.setVisibility(View.VISIBLE);
                            or = (TextView) findViewById(R.id.order2);
                            for (int j = 0; j < iter; j++) {
                                if ((Integer) arytags.get(8 * text + 1 + j) > 0) {
                                    or.append(arytags_name2.get(2*j) + "*" + arytags.get(8 * text + 1 + j) + "\n");
                                }
                            }
                        }
                        or.append("Total:$" + arytags.get(8 * text + 4));
                        or.append("\nNote:" + arytags.get(8 * text + 5));
                        switch ((Integer) arytags.get(8 * text + 6)) {
                            case 1:
                                or.append("\nState:processing at store");
                                break;
                            case 2:
                                or.append("\nState:processing at store");
                                break;
                            case 3:
                                or.append("\nState:picked up");
                                break;
                            case 4:
                                or.append("\nState:arrived");
                                break;
                        }
                    }
                }
                catch(JSONException e) {
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
                param.put("t0","get_meal_info");
                param.put("t1", String.valueOf(store_num));

                return param;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request_sel_name);
    }
    //delete_order
    private void delete(int order_num) {
        //declare request
        StringRequest request_del=new StringRequest(Request.Method.POST, url_del, new Response.Listener<String>() {
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
                param.put("t0", "delete_order");
                param.put("t1", String.valueOf(order_num));

                return param;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request_del);
    }
    //update_order
    private void update_order(int order_num,int idx) {
        //get value
        final String meal1=e1.getText().toString().trim();
        final String meal2=e2.getText().toString().trim();
        final String meal3=e3.getText().toString().trim();
        final String note=e4.getText().toString().trim();

        //declare request
        StringRequest request_up=new StringRequest(Request.Method.POST, url_up, new Response.Listener<String>() {
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
                int p=0;
                param.put("t0","update_order");
                param.put("t1",String.valueOf(order_num));
                if(meal1.length()>0){
                    param.put("t2",meal1);
                    if(idx==0) {
                        if(Integer.valueOf(meal1)>0) {
                            p += Integer.valueOf(meal1) * (Integer) arytags_name1.get(1);
                        }
                    }else{
                        if(Integer.valueOf(meal1)>0) {
                            p += Integer.valueOf(meal1) * (Integer) arytags_name2.get(1);
                        }
                    }
                }else{
                    param.put("t2",String.valueOf(arytags.get(idx*8+1)));
                    if(idx==0) {
                        if((Integer)arytags.get(idx*8+1)>0) {
                            p += (Integer) arytags.get(idx * 8 + 1) * (Integer) arytags_name1.get(1);
                        }
                    }else{
                        if((Integer)arytags.get(idx*8+1)>0) {
                            p += (Integer) arytags.get(idx * 8 + 1) * (Integer) arytags_name2.get(1);
                        }
                    }
                }
                if(meal2.length()>0){
                    param.put("t3",meal2);
                    if(idx==0) {
                        if(Integer.valueOf(meal2)>0) {
                            p += Integer.valueOf(meal2) * (Integer) arytags_name1.get(3);
                        }
                    }else{
                        if(Integer.valueOf(meal2)>0) {
                            p += Integer.valueOf(meal2) * (Integer) arytags_name2.get(3);
                        }
                    }
                }else{
                    param.put("t3",String.valueOf(arytags.get(idx*8+2)));
                    if(idx==0) {
                        if((Integer)arytags.get(idx*8+2)>0) {
                            p += (Integer) arytags.get(idx * 8 + 2) * (Integer) arytags_name1.get(3);
                        }
                    }else{
                        if((Integer)arytags.get(idx*8+2)>0) {
                            p += (Integer) arytags.get(idx * 8 + 2) * (Integer) arytags_name2.get(3);
                        }
                    }
                }
                if(meal3.length()>0){
                    param.put("t4",meal3);
                    if(idx==0) {
                        if(Integer.valueOf(meal3)>0) {
                            p += Integer.valueOf(meal3) * (Integer) arytags_name1.get(5);
                        }
                    }else{
                        if(Integer.valueOf(meal3)>0) {
                            p += Integer.valueOf(meal3) * (Integer) arytags_name2.get(5);
                        }
                    }
                }else{
                    param.put("t4",String.valueOf(arytags.get(idx*8+3)));
                    if(idx==0) {
                        if((Integer)arytags.get(idx*8+3)>0) {
                            p += (Integer) arytags.get(idx * 8 + 3) * (Integer) arytags_name1.get(5);
                        }
                    }else{
                        if((Integer)arytags.get(idx*8+3)>0) {
                            p += (Integer) arytags.get(idx * 8 + 3) * (Integer) arytags_name2.get(5);
                        }
                    }
                }
                if(note.length()>0){
                    param.put("t5",note);
                }else{
                    param.put("t5",(String)arytags.get(idx*8+5));
                }
                param.put("t6",String.valueOf(p));

                return param;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request_up);
        e1.setText("");
        e2.setText("");
        e3.setText("");
        e4.setText("");
    }
    //update_order_state
    private void update_state(int order_num) {
        //declare request
        StringRequest request_up_sta=new StringRequest(Request.Method.POST, url_up, new Response.Listener<String>() {
            @Override
            //success
            public void onResponse(String response) {
                if(response.toString()=="update success"){
                    Toast.makeText(getApplicationContext(),"enjoy your meal!!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
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
                param.put("t0","update_order_state");
                param.put("t1",String.valueOf(order_num));
                param.put("t2",String.valueOf(5));
                return param;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request_up_sta);
    }
}