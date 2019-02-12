package com.example.vineetha.sqllogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // Creating EditText.
    EditText username, Password;

    // Creating button;
    Button LoginButton;

    // Creating Volley RequestQueue.
    RequestQueue requestQueue;

    // Create string variable to hold the EditText Value.
    String NameHolder, PasswordHolder;

    // Creating Progress dialog.
    ProgressDialog progressDialog;

    // Storing server url into String variable.
    String HttpUrl = "http://10.0.2.2/test2/loginnew.php";

    Boolean CheckEditText;

    //String ServerResponse = null ;

    String TempServerResponseMatchedValue = "Data Matched" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.email);

        Password = (EditText) findViewById(R.id.password);

        // Assigning ID's to Button.
        LoginButton = (Button) findViewById(R.id.button);

       /* // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(LoginActivity.this);

        // Assigning Activity this to progress dialog.
        progressDialog = new ProgressDialog(LoginActivity.this);*/

        // Adding click listener to button.
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String pass = Password.getText().toString();


                if(username.getText().toString().length()==0)
                {
                    username.setError("Field is not be empty");
                    return;
                }
                if(Password.getText().toString().length()==0)
                {
                    Password.setError("Field is not be empty");
                    return;
                }

                Validating(name,pass);

            }
        });

    }

    private void Validating(final String name, final String pwd){
          /*  Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
          progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Validating...");
        progressDialog.setMax(3000);
         progressDialog.show();*/
        StringRequest stringRequest=new StringRequest(Request.Method.POST, HttpUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String val=jsonObject.getString("message");
                    if(val.equals("LogIn Success")){
                        Intent intent=new Intent(MainActivity.this,SuccessActivity.class);
                        startActivity(intent);
                        username.setText("");
                        Password.setText("");
                        //  progressDialog.hide();
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Enter Valid Details", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String> map=new HashMap<>();
                map.put("email",name);
                map.put("password",pwd);
                return  map;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}