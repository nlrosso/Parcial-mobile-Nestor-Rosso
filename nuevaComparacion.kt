package ar.com.parcial1

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class nuevaComparacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nueva_comparacion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val datosAlmacenados = getSharedPreferences("loginPref", Context.MODE_PRIVATE)
        val historicoComparaciones = datosAlmacenados.getString("Historico","")
        val btCalcular=findViewById<Button>(R.id.Calcular)
        val btVolver=findViewById<Button>(R.id.btVolver)
        val etMonto=findViewById<TextView>(R.id.etMonto)
        val etPlazo=findViewById<TextView>(R.id.etPlazo)
        val etBanco1=findViewById<TextView>(R.id.etBanco1)
        val etTasaBanco1=findViewById<TextView>(R.id.etTasaBanco1)
        val etBanco2=findViewById<TextView>(R.id.etBanco2)
        val etTasaBanco2=findViewById<TextView>(R.id.etTasaBanco2)
        val tvIndicaciones=findViewById<TextView>(R.id.tvIndicaciones)
        val tvComparacionLinea1=findViewById<TextView>(R.id.tvComparacionLinea1)
        val tvComparacionLinea2=findViewById<TextView>(R.id.tvComparacionLinea2)
        val tvComparacionLinea3=findViewById<TextView>(R.id.tvComparacionLinea3)
        val tvComparacionLinea4=findViewById<TextView>(R.id.tvComparacionLinea4)
        var error :Boolean =false
        val btGrabarEnHistorico=findViewById<Button>(R.id.btGrabarEnHistorico)


        //Hacer comparacion
        btCalcular.setOnClickListener {
            val monto=etMonto.text.toString()
            val plazo=etPlazo.text.toString()
            val banco1=etBanco1.text.toString()
            val tasaBanco1=etTasaBanco1.text.toString()
            val banco2=etBanco2.text.toString()
            val tasaBanco2=etTasaBanco2.text.toString()

            //Chqueo ingreso de datos correctos
            if (monto == ""){
                Toast.makeText(this,"Debe ingresar un monto", Toast.LENGTH_LONG).show()
                error=true}
            if (monto.toDoubleOrNull() == null){
                Toast.makeText(this,"El monto debe ser un numero", Toast.LENGTH_LONG).show()
                error=true}
            if (plazo == ""){
                Toast.makeText(this,"Debe ingresar un plazo", Toast.LENGTH_LONG).show()
                error=true}
            if (plazo.toIntOrNull() == null){
                Toast.makeText(this,"El plazo debe ser un numero entero", Toast.LENGTH_LONG).show()
                error=true}
            if (banco1 == ""){
                Toast.makeText(this,"Debe ingresar el nombre del banco1", Toast.LENGTH_LONG).show()
                error=true}
            if (tasaBanco1 == ""){
                Toast.makeText(this,"Debe ingresar una tasa para el banco1", Toast.LENGTH_LONG).show()
                error=true}
            if (tasaBanco1.toDoubleOrNull() == null){
                Toast.makeText(this,"La tasa para el banco1 debe ser un numero", Toast.LENGTH_LONG).show()
                error=true}
            if (banco2 == ""){
                Toast.makeText(this,"Debe ingresar el nombre del banco2", Toast.LENGTH_LONG).show()
                error=true}
            if (tasaBanco2 == ""){
                Toast.makeText(this,"Debe ingresar una tasa para el banco2", Toast.LENGTH_LONG).show()
                error=true}
            if (tasaBanco2.toDoubleOrNull() == null){
                Toast.makeText(this,"La tasa para el banco2 debe ser un numero", Toast.LENGTH_LONG).show()
                error=true}

            //Si no hubo error realizo la comparacion
            if(!error){
                var rendimientoBanco1= tasaBanco1.toDouble() * plazo.toInt() * monto.toDouble()/36000
                var roiBanco1= rendimientoBanco1 *100 / monto.toDouble()
                var rendimientoBanco2= tasaBanco2.toDouble() * plazo.toInt() * monto.toDouble()/36000
                var roiBanco2= rendimientoBanco2 *100 / monto.toDouble()
                tvComparacionLinea1.text="Monto $monto      plazo $plazo"
                tvComparacionLinea2.text="$banco1    TNA $tasaBanco1%   rendimiento $rendimientoBanco1    roi $roiBanco1%"
                tvComparacionLinea3.text="$banco2    TNA $tasaBanco2%   rendimiento $rendimientoBanco2    roi $roiBanco2%"
                if (roiBanco1> roiBanco2){
                    tvComparacionLinea4.text="$banco1 ofrece mayor rendimiento que $banco2" }
                if (roiBanco1< roiBanco2){
                    tvComparacionLinea4.text="$banco2 ofrece mayor rendimiento que $banco1" }
                if (roiBanco1 == roiBanco2){tvComparacionLinea4.text="$banco2 ofrece el mismo rendimiento que $banco1"}

                btGrabarEnHistorico.setVisibility(View.VISIBLE)

                //Grabar en historico
                btGrabarEnHistorico.setOnClickListener {
                    datosAlmacenados.edit().apply {
                        var nuevaComparacion =
                            "$monto¿$plazo¿$banco1¿$tasaBanco1¿$banco2¿$tasaBanco2/"
                        var nuevoHistorico = nuevaComparacion + historicoComparaciones
                        putString("Historico",nuevoHistorico)

                        apply()
                        tvIndicaciones.text=nuevoHistorico
                        btGrabarEnHistorico.setVisibility(View.INVISIBLE)
                    }
                }
            }
            else error=false

        }




        //Volver
        btVolver.setOnClickListener {
            finish()
        }

    }
}