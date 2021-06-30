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

public class Menu extends AppCompatActivity {
    TextView or;
    Button del,ins,upd,con;
    CheckBox ch1,ch2,ch3;
    EditText e1,e2,e3;
    ArrayList arytags=new ArrayList();
    private static final String url_del="http://10.0.2.2/delete.php";
    private static final String url_sel="http://10.0.2.2/select.php";
    private static final String url_up="http://10.0.2.2/update.php";
    private static final String url_ins="http://10.0.2.2/insert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Bundle bundle = getIntent().getExtras();
        int store_num = bundle.getInt("store_ID");
        ch1=(CheckBox) findViewById(R.id.check1);
        ch2=(CheckBox) findViewById(R.id.check2);
        ch3=(CheckBox) findViewById(R.id.check3);
        e1=(EditText) findViewById(R.id.ed1);
        e2=(EditText) findViewById(R.id.ed2);
        e3=(EditText) findViewById(R.id.ed3);
        del=(Button)findViewById(R.id.delete);
        ins=(Button)findViewById(R.id.in);
        upd=(Button)findViewById(R.id.update);
        con=(Button)findViewById(R.id.done);
        e1.setVisibility(View.INVISIBLE);
        e2.setVisibility(View.INVISIBLE);
        e3.setVisibility(View.INVISIBLE);
        con.setVisibility(View.INVISIBLE);
        select(store_num);

        ch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //判斷CheckBox是否有勾選，同mCheckBox.isChecked()
                if (isChecked) {
                    ch2.setChecked(false);
                    ch3.setChecked(false);
                    del.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //click
                        public void onClick(View view) {
                            delete((Integer) arytags.get(0));
                            try {
                                Thread.sleep(300); //1000為1秒
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            or = (TextView) findViewById(R.id.t1);
                            or.setText("");
                            or = (TextView) findViewById(R.id.t2);
                            or.setText("");
                            or = (TextView) findViewById(R.id.t3);
                            or.setText("");
                            select(store_num);
                            ch1.setChecked(false);
                        }
                    });
                    upd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //click
                        public void onClick(View view) {
                            e1.setVisibility(View.VISIBLE);
                            e2.setVisibility(View.VISIBLE);
                            e3.setVisibility(View.VISIBLE);
                            e1.setHint((String)arytags.get(1));
                            e2.setHint(String.valueOf(arytags.get(2)));
                            e3.setHint((String)arytags.get(3));
                            con.setVisibility(View.VISIBLE);
                            con.setOnClickListener(new View.OnClickListener() {
                                @Override
                                //click
                                public void onClick(View view) {
                                    update((Integer) arytags.get(0), 0);
                                    try {
                                        Thread.sleep(300); //1000為1秒
                                    } catch (InterruptedException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    or = (TextView) findViewById(R.id.t1);
                                    or.setText("");
                                    or = (TextView) findViewById(R.id.t2);
                                    or.setText("");
                                    or = (TextView) findViewById(R.id.t3);
                                    or.setText("");
                                    select(store_num);
                                    ch1.setChecked(false);
                                    e1.setVisibility(View.INVISIBLE);
                                    e2.setVisibility(View.INVISIBLE);
                                    e3.setVisibility(View.INVISIBLE);
                                    con.setVisibility(View.INVISIBLE);
                                }
                            });
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
                    ch3.setChecked(false);
                    del.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //click
                        public void onClick(View view) {
                            delete((Integer) arytags.get(4));
                            try {
                                Thread.sleep(300); //1000為1秒
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            or = (TextView) findViewById(R.id.t1);
                            or.setText("");
                            or = (TextView) findViewById(R.id.t2);
                            or.setText("");
                            or = (TextView) findViewById(R.id.t3);
                            or.setText("");
                            select(store_num);
                            ch2.setChecked(false);
                        }
                    });
                    upd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //click
                        public void onClick(View view) {
                            e1.setVisibility(View.VISIBLE);
                            e2.setVisibility(View.VISIBLE);
                            e3.setVisibility(View.VISIBLE);
                            e1.setHint((String)arytags.get(5));
                            e2.setHint(String.valueOf(arytags.get(6)));
                            e3.setHint((String)arytags.get(7));
                            con.setVisibility(View.VISIBLE);
                            con.setOnClickListener(new View.OnClickListener() {
                                @Override
                                //click
                                public void onClick(View view) {
                                    update((Integer) arytags.get(4), 1);
                                    try {
                                        Thread.sleep(300); //1000為1秒
                                    } catch (InterruptedException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    or = (TextView) findViewById(R.id.t1);
                                    or.setText("");
                                    or = (TextView) findViewById(R.id.t2);
                                    or.setText("");
                                    or = (TextView) findViewById(R.id.t3);
                                    or.setText("");
                                    select(store_num);
                                    ch2.setChecked(false);
                                    e1.setVisibility(View.INVISIBLE);
                                    e2.setVisibility(View.INVISIBLE);
                                    e3.setVisibility(View.INVISIBLE);
                                    con.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                    });
                }
            }
        });
        ch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //判斷CheckBox是否有勾選，同mCheckBox.isChecked()
                if (isChecked) {
                    ch1.setChecked(false);
                    ch2.setChecked(false);
                    del.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //click
                        public void onClick(View view) {
                            delete((Integer) arytags.get(8));
                            or = (TextView) findViewById(R.id.t1);
                            or.setText("");
                            or = (TextView) findViewById(R.id.t2);
                            or.setText("");
                            or = (TextView) findViewById(R.id.t3);
                            or.setText("");
                            try {
                                Thread.sleep(300); //1000為1秒
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            select(store_num);
                            ch3.setChecked(false);
                        }
                    });
                    upd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //click
                        public void onClick(View view) {
                            e1.setVisibility(View.VISIBLE);
                            e2.setVisibility(View.VISIBLE);
                            e3.setVisibility(View.VISIBLE);
                            e1.setHint((String)arytags.get(9));
                            e2.setHint(String.valueOf(arytags.get(10)));
                            e3.setHint((String)arytags.get(11));
                            con.setVisibility(View.VISIBLE);
                            con.setOnClickListener(new View.OnClickListener() {
                                @Override
                                //click
                                public void onClick(View view) {
                                    update((Integer) arytags.get(8), 2);
                                    try {
                                        Thread.sleep(300); //1000為1秒
                                    } catch (InterruptedException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    or = (TextView) findViewById(R.id.t1);
                                    or.setText("");
                                    or = (TextView) findViewById(R.id.t2);
                                    or.setText("");
                                    or = (TextView) findViewById(R.id.t3);
                                    or.setText("");
                                    select(store_num);
                                    ch3.setChecked(false);
                                    e1.setVisibility(View.INVISIBLE);
                                    e2.setVisibility(View.INVISIBLE);
                                    e3.setVisibility(View.INVISIBLE);
                                    con.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                    });
                }
            }
        });

        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            //click
            public void onClick(View view) {
                e1.setVisibility(View.VISIBLE);
                e2.setVisibility(View.VISIBLE);
                e3.setVisibility(View.VISIBLE);
                e1.setHint("meal's name");
                e2.setHint("meal's price");
                e3.setHint("meal's image link");
                con.setVisibility(View.VISIBLE);
                con.setOnClickListener(new View.OnClickListener() {
                    @Override
                    //click
                    public void onClick(View view) {
                        insert(store_num);
                        or = (TextView) findViewById(R.id.t1);
                        or.setText("");
                        or = (TextView) findViewById(R.id.t2);
                        or.setText("");
                        or = (TextView) findViewById(R.id.t3);
                        or.setText("");
                        try {
                            Thread.sleep(300); //1000為1秒
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        select(store_num);
                        e1.setVisibility(View.INVISIBLE);
                        e2.setVisibility(View.INVISIBLE);
                        e3.setVisibility(View.INVISIBLE);
                        con.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });

    }

    //delete_meal
    private void delete(int meal_num) {
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
                param.put("t0", "delete_meal");
                param.put("t1", String.valueOf(meal_num));

                return param;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request_del);
    }
    //get_meal_info
    private void select(int store_num) {
        //declare request
        arytags.clear();
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
                            int meal_num = jsonObject.getInt("meal_N");
                            arytags.add(meal_num);  //.get(idx) means customer_num[idx]
                            String meal_name = jsonObject.getString("meal_name");
                            arytags.add(meal_name);
                            int meal_price = jsonObject.getInt("meal_price");
                            arytags.add(meal_price);
                            String image = jsonObject.getString("image");
                            arytags.add(image);
                        }
                        if(iter>3){
                            iter=3;
                        }
                        for (int i = 0; i < iter; i++) {
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
                            or.setText("Name:" + arytags.get(4*i+1));
                            or.append("\nPrice:" + arytags.get(4*i+2));
                            or.append("\nImage:" + arytags.get(4*i+3));
                        }
                    }else {
                        ch1.setVisibility(View.INVISIBLE);
                        ch2.setVisibility(View.INVISIBLE);
                        ch3.setVisibility(View.INVISIBLE);
                        or=(TextView) findViewById(R.id.t1);
                        or.setText("It's empty now.\nGo insert some dishes!");
                        or=(TextView) findViewById(R.id.t2);
                        or.setText("");
                        or=(TextView) findViewById(R.id.t3);
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
                param.put("t0","get_meal_info");
                param.put("t1", String.valueOf(store_num));

                return param;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request_sel);
    }
    //insert_meal
    private void insert(int store_num) {

        //get value
        final String name=e1.getText().toString().trim();
        final String price=e2.getText().toString().trim();
        final String url=e3.getText().toString().trim();

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
                param.put("t0","meal");
                param.put("t1",String.valueOf(store_num));
                param.put("t2",name);
                param.put("t3",price);
                param.put("t4",url);

                return param;
            }
        };

        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request_ins);
        e1.setText("");
        e2.setText("");
        e3.setText("");
    }
    //update_meal
    private void update(int meal_num, int idx) {

        //get value
        final String name=e1.getText().toString().trim();
        final String price=e2.getText().toString().trim();
        final String url=e3.getText().toString().trim();

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
                param.put("t0","update_meal");
                param.put("t1",String.valueOf(meal_num));
                if(name.length()>0){
                    param.put("t2",name);
                }else{
                    param.put("t2",(String)arytags.get(idx*4+1));
                }
                if(price.length()>0){
                    param.put("t3",price);
                }else{
                    param.put("t3",String.valueOf(arytags.get(idx*4+2)));
                }
                if(url.length()>0){
                    param.put("t4",url);
                }else{
                    param.put("t4",(String)arytags.get(idx*4+3));
                }

                return param;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request_up);
        e1.setText("");
        e2.setText("");
        e3.setText("");
    }
}