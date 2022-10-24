package com.example.dog;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class reserve_page extends AppCompatActivity {
    private static final String DEBUG_TAG = "hi";
    private InputStream is = null;

    private Spinner hour, minute;
    private Button enter;
    private CheckBox reserve_lrc;
    private EditText reserve_roomNumber;

    private Clock clock;
    private String insert_api = "http://sshop.tplinkdns.com:3380/insertBooking.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_page);

        hour = findViewById(R.id.hour);
        minute = findViewById(R.id.minute);
        enter = findViewById(R.id.enter);
        reserve_lrc = findViewById(R.id.reserve_lrc);
        reserve_roomNumber = findViewById(R.id.reserve_roomNumber);

        reserve_lrc.setOnCheckedChangeListener((buttonView, isChecked) -> {
            reserve_roomNumber.setText("418");
            if (!isChecked){
                reserve_roomNumber.setText("");
            }
        });


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.reserve_hour_value, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hour.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.reserve_minute_value, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minute.setAdapter(adapter2);


        clock = new Clock();
    }



    public void reserve(View view){
        int hour_value = Integer.parseInt( hour.getSelectedItem().toString() );
        int minute_value = Integer.parseInt( minute.getSelectedItem().toString() );

        int current_hour = clock.getCurrentHour();
        int current_minute = clock.getCurrentMinute();

        String room_num = reserve_roomNumber.getText().toString();

        if(  (hour_value>current_hour || (hour_value==current_hour && minute_value>current_minute) )
                && !room_num.matches("")  ){
            Toast.makeText(this, "yes you can do it", Toast.LENGTH_SHORT).show();
            UUID id = UUID.randomUUID();

            String time = "";
            if(minute_value == 0){
                time = hour_value + ":" + minute_value + "0:00";
            }
            else{
                time = hour_value + ":" + minute_value + ":00";
            }


            String finalTime = time;
            StringRequest request = new StringRequest(Request.Method.POST, insert_api,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(reserve_page.this, "success! "+ response.trim(), Toast.LENGTH_SHORT).show();
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(reserve_page.this, "not success! "+ error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
            ){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> param = new HashMap<String, String>();
                    param.put("id",  id.toString());
                    param.put("start_time", finalTime);
                    param.put("location",  room_num);
                    param.put("photo_path",  "hehe");
                    Log.v("reserve_page", ""+param);
                    return param;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(reserve_page.this);
            requestQueue.add(request);



        }

        else {
            Toast.makeText(this, current_hour + ", " + current_minute + " no you cannot do it " + hour_value + ", " + minute_value, Toast.LENGTH_SHORT).show();
        }

        //Toast.makeText(this, "on9"+minute.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "on9"+hour.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    public void test(View view) throws IOException {
        String api = "http://sshop.tplinkdns.com:3380/runFall_mediapipe.php";
        try {
            URL url = new URL(api);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();
            Reader reader = null;
            reader = new InputStreamReader(is, "UTF-8");
            char[] buffer = new char[500];
            reader.read(buffer);

            Toast.makeText(this, ""+ new String(buffer), Toast.LENGTH_SHORT).show();
        // Convert the InputStream into a string
        }
            catch (Exception e){
        }
// Makes sure that the InputStream is closed after the app is
// finished using it.
        finally {
            if (is != null) {
                is.close();
            }

        }

    }
}
