package com.robapanda.michiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.robapanda.michiapp.api.ApiCallBack
import com.robapanda.michiapp.api.ApiTask
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity(), ApiCallBack {
    private lateinit var GenerarBoton: Button
    private lateinit var imagen: ImageView
    //private var URLBase = "https://placekitten.com/"
    private var URLAPI = "https://api.thecatapi.com/v1/images/search?api_key="
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GenerarBoton = findViewById(R.id.button)
        imagen = findViewById(R.id.imageView)

        GenerarBoton.setOnClickListener{
            val apiRequestTask = ApiTask(this)
            apiRequestTask.execute(URLAPI)
        }
    }

    override fun OnRequestComplete(result: String) {
        //Toast.makeText(this, result, Toast.LENGTH_LONG).show()
        procesar(result)
    }

    fun procesar (result: String){

        try {
            // Parse the JSON string into a JSONObject
            val jsonArray = JSONArray(result)

            val jsonObject = jsonArray.getJSONObject(0)

            // Access values from the JSON object
            //val id = jsonObject.getString("id")
            val url = jsonObject.getString("url")
            //val largo = jsonObject.getInt("width")
            //val alto = jsonObject.getInt("height")

            var urlGato = url


            //Glide.with(this).load(urlGato).into(imagen)
            Picasso.get().load(urlGato).into(imagen)


        } catch  (e: JSONException) {
            e.printStackTrace()
            Toast.makeText(this, "urlGato", Toast.LENGTH_LONG).show()
        }
    }
}