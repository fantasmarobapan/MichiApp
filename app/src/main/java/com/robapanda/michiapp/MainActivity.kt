package com.robapanda.michiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
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
    private lateinit var miSpinner: Spinner
    private var opciones = emptyArray<String>()
    private var URLAPI = "https://api.thecatapi.com/v1/images/search"
    private var URLAPIRAZA = "https://api.thecatapi.com/v1/images/search?breed_ids="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        opciones = resources.getStringArray(R.array.opciones_array)

        GenerarBoton = findViewById(R.id.button)
        imagen = findViewById(R.id.imageView)
        miSpinner = findViewById(R.id.miSpinner)

        // Configurar el adaptador para el Spinner programáticamente
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        miSpinner.adapter = adapter

        // Configurar el listener del Spinner
        miSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                // Obtener la posición seleccionada
                val posicionSeleccionada = position

                // Hacer algo con la posición seleccionada
                Toast.makeText(applicationContext, "Posición seleccionada: $posicionSeleccionada", Toast.LENGTH_SHORT).show()

                // Llamar a la función para procesar según la posición
                if (posicionSeleccionada == 0) {
                    // Si la posición es 0, usar URLAPI
                    val apiRequestTask = ApiTask(this@MainActivity)
                    apiRequestTask.execute(URLAPI)
                } else {
                    // Si la posición no es 0, construir una nueva URL con URLAPIRAZA y el resultado de procesarSegunPosicion
                    val nuevaURLAPIRAZA = "$URLAPIRAZA${procesarSegunPosicion(posicionSeleccionada)}"
                    val apiRequestTask = ApiTask(this@MainActivity)
                    apiRequestTask.execute(nuevaURLAPIRAZA)
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Se llama cuando no se ha seleccionado nada
            }
        }

        GenerarBoton.setOnClickListener {
            // Obtener la posición seleccionada
            val posicionSeleccionada = miSpinner.selectedItemPosition

            // Hacer algo con la posición seleccionada
            Toast.makeText(applicationContext, "Posición seleccionada: $posicionSeleccionada", Toast.LENGTH_SHORT).show()

            // Llamar a la función para procesar según la posición
            if (posicionSeleccionada == 0) {
                // Si la posición es 0, usar URLAPI
                val apiRequestTask = ApiTask(this@MainActivity)
                apiRequestTask.execute(URLAPI)
            } else {
                // Si la posición no es 0, construir una nueva URL con URLAPIRAZA y el resultado de procesarSegunPosicion
                val nuevaURLAPIRAZA = "$URLAPIRAZA${procesarSegunPosicion(posicionSeleccionada)}"
                val apiRequestTask = ApiTask(this@MainActivity)
                apiRequestTask.execute(nuevaURLAPIRAZA)
            }
        }
    }

    override fun OnRequestComplete(result: String) {
        procesar(result)
    }

    private fun procesar(result: String) {
        try {
            val jsonArray = JSONArray(result)
            val jsonObject = jsonArray.getJSONObject(0)
            val url = jsonObject.getString("url")
            Picasso.get().load(url).into(imagen)
        } catch (e: JSONException) {
            e.printStackTrace()
            Toast.makeText(this, "Error al procesar JSON", Toast.LENGTH_LONG).show()
        }
    }

    private fun procesarSegunPosicion(posicion: Int): String {
        val stringDevolver = when (posicion) {
            1 -> "aege"
            2 -> "awir"
            3 -> "bali"
            4 -> "bamb"
            5 -> "beng"
            6 -> "birm"
            7 -> "bomb"
            8 -> "chee"
            9 -> "jbob"
            10 -> "khao"
            else -> "Posición no válida"
        }

        return stringDevolver
    }
}