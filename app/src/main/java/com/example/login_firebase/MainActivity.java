package com.example.login_firebase;


import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RequestQueue qeqeq;
    List<String> datos = new ArrayList<String>();
    ListView lista;
    String url = "http://10.0.2.2:8082/api/clientes";
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qeqeq = Volley.newRequestQueue(this);
        GetApiData();
        lista = (ListView)findViewById(R.id.listadeclientes);
    }

    private void GetApiData(){
    JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
        if(response.length()>0){
            for (int i=0; i< response.length(); i++){
                try {
                    JSONObject obj = response.getJSONObject(i);
                    Usuario cliente = new Usuario();
                    cliente.setNombre(obj.get("nombre").toString());
                    cliente.setApellido(obj.get("apellido").toString());
                    cliente.setEmail(obj.get("email").toString());
                    cliente.setId(obj.getInt("id"));
                    datos.add(cliente.getNombre().toString());
                    datos.add(cliente.getApellido().toString());
                    datos.add(cliente.getEmail().toString());

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,datos);
                    lista.setAdapter(adapter);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            int x = 0;
        }
    });
    qeqeq.add(request);
    }
}