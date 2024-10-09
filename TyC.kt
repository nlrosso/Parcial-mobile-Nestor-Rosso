package ar.com.parcial1

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TyC : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.terminos_y_condiciones)
        val cbTyC = findViewById<CheckBox>(R.id.cbTyC)
        val btAtras= findViewById<Button>(R.id.volver)
        val datosAlmacenados = getSharedPreferences("loginPref", Context.MODE_PRIVATE)
        var aceptado = datosAlmacenados.getBoolean("estaAceptado", false)
        cbTyC.setChecked(aceptado)

        btAtras.setOnClickListener {
            if (cbTyC.isChecked){
                Toast.makeText(this, "Condiciones aceptadas", Toast.LENGTH_LONG).show()
                datosAlmacenados.edit().apply {
                    putBoolean("estaAceptado", true)
                    var aceptado = datosAlmacenados.getBoolean("estaAceptado",true)
                    apply()
                }
            }
            else {
                Toast.makeText(this, "Condiciones rechazadas", Toast.LENGTH_LONG).show()
                datosAlmacenados.edit().apply {
                    putBoolean("estaAceptado", false)
                    apply()
                }
            }
            finish()
        }
    }
}