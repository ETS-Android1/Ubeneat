package com.example.volleyinsertdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

public class Deliver extends AppCompatActivity {
    TextView or;
    Button selec,picku,arriv;
    CheckBox ch1,ch2,ch3;
    int a,b,c;
    ArrayList arytags=new ArrayList();
    private static final String url_sel="http://10.0.2.2/select.php";
    private static final String url_up="http://10.0.2.2/update.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver);
        ch1=(CheckBox) findViewById(R.id.check1);
        ch2=(CheckBox) findViewById(R.id.check2);
        ch3=(CheckBox) findViewById(R.id.check3);
        select();
        picku=(Button)findViewById(R.id.pick);
        selec=(Button)findViewById(R.id.store);
        arriv=(Button)findViewById(R.id.arri);

        ch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //判斷CheckBox是否有勾選，同mCheckBox.isChecked()
                if (isChecked) {
                    a=1;
                    selec.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //click
                        public void onClick(View view) {
                            update((Integer) arytags.get(4),2);
                            //ch2.setChecked(false);
                            or=(TextView) findViewById(R.id.t1);
                            or.setText("");
                            or=(TextView) findViewById(R.id.t2);
                            or.setText("");
                            or=(TextView) findViewById(R.id.t3);
                            or.setText("");
                            //check 1 then check 2
                            if(b==1){
                                update((Integer) arytags.get(9),2);
                                //ch1.setChecked(false);
                                or=(TextView) findViewById(R.id.t2);
                                or.setText("");
                            }
                            if(c==1){
                                update((Integer) arytags.get(14),2);
                                //ch1.setChecked(false);
                                or=(TextView) findViewById(R.id.t2);
                                or.setText("");
                            }
                            a=0;b=0;c=0;
                            try {
                                Thread.sleep(300); //1000為1秒
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            select();
                            ch1.setChecked(false);
                            ch2.setChecked(false);
                            ch3.setChecked(false);
                        }
                    });
                    picku.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //click
                        public void onClick(View view) {
                            update((Integer) arytags.get(4),3);
                            //ch2.setChecked(false);
                            or=(TextView) findViewById(R.id.t1);
                            or.setText("");
                            or=(TextView) findViewById(R.id.t2);
                            or.setText("");
                            or=(TextView) findViewById(R.id.t3);
                            or.setText("");
                            //check 1 then check 2
                            if(b==1){
                                update((Integer) arytags.get(9),3);
                                //ch1.setChecked(false);
                                or=(TextView) findViewById(R.id.t2);
                                or.setText("");
                            }
                            if(c==1){
                                update((Integer) arytags.get(14),3);
                                //ch1.setChecked(false);
                                or=(TextView) findViewById(R.id.t2);
                                or.setText("");
                            }
                            a=0;b=0;c=0;
                            try {
                                Thread.sleep(300); //1000為1秒
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            select();
                            ch1.setChecked(false);
                            ch2.setChecked(false);
                            ch3.setChecked(false);
                        }
                    });
                    arriv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //click
                        public void onClick(View view) {
                            update((Integer) arytags.get(4),4);
                            //ch2.setChecked(false);
                            or=(TextView) findViewById(R.id.t1);
                            or.setText("");
                            or=(TextView) findViewById(R.id.t2);
                            or.setText("");
                            or=(TextView) findViewById(R.id.t3);
                            or.setText("");
                            //check 1 then check 2
                            if(b==1){
                                update((Integer) arytags.get(9),4);
                                //ch1.setChecked(false);
                                or=(TextView) findViewById(R.id.t2);
                                or.setText("");
                            }
                            if(c==1){
                                update((Integer) arytags.get(14),4);
                                //ch1.setChecked(false);
                                or=(TextView) findViewById(R.id.t2);
                                or.setText("");
                            }
                            a=0;b=0;c=0;
                            try {
                                Thread.sleep(300); //1000為1秒
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            select();
                            ch1.setChecked(false);
                            ch2.setChecked(false);
                            ch3.setChecked(false);
                        }
                    });
                }else{
                    a=0;
                }
            }
        });
        ch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //判斷CheckBox是否有勾選，同mCheckBox.isChecked()
                if (isChecked) {
                    b=1;
                    selec.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //click
                        public void onClick(View view) {
                            update((Integer) arytags.get(9),2);
                            //ch2.setChecked(false);
                            or=(TextView) findViewById(R.id.t1);
                            or.setText("");
                            or=(TextView) findViewById(R.id.t2);
                            or.setText("");
                            or=(TextView) findViewById(R.id.t3);
                            or.setText("");
                            //check 1 then check 2
                            if(a==1){
                                update((Integer) arytags.get(4),2);
                                //ch1.setChecked(false);
                                or=(TextView) findViewById(R.id.t2);
                                or.setText("");
                            }
                            if(c==1){
                                update((Integer) arytags.get(14),2);
                                //ch1.setChecked(false);
                                or=(TextView) findViewById(R.id.t2);
                                or.setText("");
                            }
                            a=0;b=0;c=0;
                            try {
                                Thread.sleep(300); //1000為1秒
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            select();
                            ch1.setChecked(false);
                            ch2.setChecked(false);
                            ch3.setChecked(false);
                        }
                    });
                    picku.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //click
                        public void onClick(View view) {
                            update((Integer) arytags.get(9),3);
                            //ch2.setChecked(false);
                            or=(TextView) findViewById(R.id.t1);
                            or.setText("");
                            or=(TextView) findViewById(R.id.t2);
                            or.setText("");
                            or=(TextView) findViewById(R.id.t3);
                            or.setText("");
                            //check 1 then check 2
                            if(a==1){
                                update((Integer) arytags.get(4),3);
                                //ch1.setChecked(false);
                                or=(TextView) findViewById(R.id.t2);
                                or.setText("");
                            }
                            if(c==1){
                                update((Integer) arytags.get(14),3);
                                //ch1.setChecked(false);
                                or=(TextView) findViewById(R.id.t2);
                                or.setText("");
                            }
                            a=0;b=0;c=0;
                            try {
                                Thread.sleep(300); //1000為1秒
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            select();
                            ch1.setChecked(false);
                            ch2.setChecked(false);
                            ch3.setChecked(false);
                        }
                    });
                    arriv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //click
                        public void onClick(View view) {
                            update((Integer) arytags.get(9),4);
                            //ch2.setChecked(false);
                            or=(TextView) findViewById(R.id.t1);
                            or.setText("");
                            or=(TextView) findViewById(R.id.t2);
                            or.setText("");
                            or=(TextView) findViewById(R.id.t3);
                            or.setText("");
                            //check 1 then check 2
                            if(a==1){
                                update((Integer) arytags.get(4),4);
                                //ch1.setChecked(false);
                                or=(TextView) findViewById(R.id.t2);
                                or.setText("");
                            }
                            if(c==1){
                                update((Integer) arytags.get(14),4);
                                //ch1.setChecked(false);
                                or=(TextView) findViewById(R.id.t2);
                                or.setText("");
                            }
                            a=0;b=0;c=0;
                            try {
                                Thread.sleep(300); //1000為1秒
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            select();
                            ch1.setChecked(false);
                            ch2.setChecked(false);
                            ch3.setChecked(false);
                        }
                    });
                }else{
                    b=0;
                }
            }
        });
        ch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //判斷CheckBox是否有勾選，同mCheckBox.isChecked()
                if (isChecked) {
                    c=1;
                    selec.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //click
                        public void onClick(View view) {
                            update((Integer) arytags.get(14),2);
                            //ch2.setChecked(false);
                            or=(TextView) findViewById(R.id.t1);
                            or.setText("");
                            or=(TextView) findViewById(R.id.t2);
                            or.setText("");
                            or=(TextView) findViewById(R.id.t3);
                            or.setText("");
                            //check 1 then check 2
                            if(b==1){
                                update((Integer) arytags.get(9),2);
                            }
                            if(a==1){
                                update((Integer) arytags.get(4),2);
                            }
                            a=0;b=0;c=0;
                            try {
                                Thread.sleep(300); //1000為1秒
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            select();
                            ch1.setChecked(false);
                            ch2.setChecked(false);
                            ch3.setChecked(false);
                        }
                    });
                    picku.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //click
                        public void onClick(View view) {
                            update((Integer) arytags.get(14),3);
                            //ch2.setChecked(false);
                            or=(TextView) findViewById(R.id.t1);
                            or.setText("");
                            or=(TextView) findViewById(R.id.t2);
                            or.setText("");
                            or=(TextView) findViewById(R.id.t3);
                            or.setText("");
                            //check 1 then check 2
                            if(b==1){
                                update((Integer) arytags.get(9),3);
                            }
                            if(a==1){
                                update((Integer) arytags.get(4),3);
                            }
                            a=0;b=0;c=0;
                            try {
                                Thread.sleep(300); //1000為1秒
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            select();
                            ch1.setChecked(false);
                            ch2.setChecked(false);
                            ch3.setChecked(false);
                        }
                    });
                    arriv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //click
                        public void onClick(View view) {
                            update((Integer) arytags.get(14),4);
                            //ch2.setChecked(false);
                            or=(TextView) findViewById(R.id.t1);
                            or.setText("");
                            or=(TextView) findViewById(R.id.t2);
                            or.setText("");
                            or=(TextView) findViewById(R.id.t3);
                            or.setText("");
                            //check 1 then check 2
                            if(b==1){
                                update((Integer) arytags.get(9),4);
                            }
                            if(a==1){
                                update((Integer) arytags.get(4),4);
                            }
                            a=0;b=0;c=0;
                            try {
                                Thread.sleep(300); //1000為1秒
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            select();
                            ch1.setChecked(false);
                            ch2.setChecked(false);
                            ch3.setChecked(false);
                        }
                    });
                }else{
                    c=0;
                }
            }
        });
    }
    //get_deliver_info
    private void select() {
        //declare request
        arytags.clear();
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
                            //state done:do not add in list
                            if(jsonObject.getInt("state")==5){
                                exact--;
                                continue;
                            }
                            String store_address = jsonObject.getString("store_address");
                            arytags.add(store_address);
                            String customer_address = jsonObject.getString("customer_address");
                            arytags.add(customer_address);
                            int price = jsonObject.getInt("total_price");
                            arytags.add(price);
                            int state = jsonObject.getInt("state");
                            arytags.add(state);
                            int order_num = jsonObject.getInt("order_num");
                            arytags.add(order_num);
                        }
                        if(exact>3){
                            exact=3;
                        }
                        for (int i = 0; i < exact; i++) {
                            if(i==0) {
                                ch1.setVisibility(View.VISIBLE);
                                ch2.setVisibility(View.INVISIBLE);
                                ch3.setVisibility(View.INVISIBLE);
                                or=(TextView) findViewById(R.id.t1);
                            } else if(i==1) {
                                ch2.setVisibility(View.VISIBLE);
                                or=(TextView) findViewById(R.id.t2);
                            } else if(i==2) {
                                ch3.setVisibility(View.VISIBLE);
                                or=(TextView) findViewById(R.id.t3);
                            }
                            or.setText("Store_address:" + arytags.get(5*i));
                            or.append("\nCustomer_address:" + arytags.get(5*i+1));
                            or.append("\nPrice:" + arytags.get(5*i+2));
                            //or.append("" + arytags.get(5*i+4));
                            switch ((Integer)arytags.get(5*i+3)){
                                case 1:
                                    or.append("\nState:without pickup");
                                    break;
                                case 2:
                                    or.append("\nState:selected");
                                    break;
                                case 3:
                                    or.append("\nState:pickup");
                                    break;
                                case 4:
                                    or.append("\nState:arrived");
                                    break;
                            }
                        }
                    }
                    if(exact==0){
                        ch1.setVisibility(View.INVISIBLE);
                        ch2.setVisibility(View.INVISIBLE);
                        or=(TextView) findViewById(R.id.t1);
                        or.setText("It's empty now.\nPlease wait!");
                        or=(TextView) findViewById(R.id.t2);
                        or.setText("");
                        or=(TextView) findViewById(R.id.t3);
                        or.setText("");
                    }
                }
                catch(JSONException e) {
                    or=(TextView) findViewById(R.id.t1);
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
                param.put("t0","get_deliver_info");

                return param;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request_sel);
    }
    //update_order_state
    private void update(int order_num, int st) {
        //declare request
        StringRequest request_sel=new StringRequest(Request.Method.POST, url_up, new Response.Listener<String>() {
            @Override
            //success
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
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
                param.put("t2",String.valueOf(st));

                return param;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request_sel);
    }
}