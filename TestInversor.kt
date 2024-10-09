package ar.com.parcial1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.wear.compose.material.RadioButton

class TestInversor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_test_inversor)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val rbGrupo1Opcion2=findViewById<Button>(R.id.rbGrupo1Opcion2)
        val rbGrupo1Opcion3=findViewById<Button>(R.id.rbGrupo1Opcion3)
        val rbGrupo2Opcion2=findViewById<Button>(R.id.rbGrupo2Opcion2)
        val rbGrupo2Opcion3=findViewById<Button>(R.id.rbGrupo2Opcion3)
        val rbGrupo3Opcion2=findViewById<Button>(R.id.rbGrupo3Opcion2)
        val rbGrupo3Opcion3=findViewById<Button>(R.id.rbGrupo3Opcion3)
        val tvInstrucciones=findViewById<TextView>(R.id.tvInstrucciones)
        val rgGrupo1 = findViewById<RadioGroup>(R.id.rgGrupo1)
        val rgGrupo2 = findViewById<RadioGroup>(R.id.rgGrupo2)
        val rgGrupo3 = findViewById<RadioGroup>(R.id.rgGrupo3)
        val btCalcular = findViewById<Button>(R.id.btCalcular)
        val btVolver=findViewById<Button>(R.id.btVolver)
        var tipoInversor:String
        val datosAlmacenados = getSharedPreferences("loginPref", Context.MODE_PRIVATE)

        //Boton Calcular
        btCalcular.setOnClickListener {
            var error=""
            var puntajeInversor =0
            val idRbSeleccionadoGrupo1 = rgGrupo1.checkedRadioButtonId
            val idRbSeleccionadoGrupo2 = rgGrupo2.checkedRadioButtonId
            val idRbSeleccionadoGrupo3 = rgGrupo3.checkedRadioButtonId
            val rbSeleccionadoGrupo1 = findViewById<RadioButton>(idRbSeleccionadoGrupo1)
            val rbSeleccionadoGrupo2 = findViewById<RadioButton>(idRbSeleccionadoGrupo2)
            val rbSeleccionadoGrupo3 = findViewById<RadioButton>(idRbSeleccionadoGrupo3)

            if (idRbSeleccionadoGrupo1 ==-1)  {
                Toast.makeText(this, "Debe responder la primera pregunta", Toast.LENGTH_LONG).show()
                error="Debe responder la primera pregunta\n"
                }
            else{
                if (rbSeleccionadoGrupo1==rbGrupo1Opcion2){
                puntajeInversor+=1}
                if (rbSeleccionadoGrupo1 == rbGrupo1Opcion3)
                    puntajeInversor += 2  }
            if (idRbSeleccionadoGrupo2 ==-1)  {
                Toast.makeText(this, "Debe responder la segunda pregunta", Toast.LENGTH_LONG).show()
                error +="Debe responder la segunda pregunta\n"
                }
            else{
                if (rbSeleccionadoGrupo2==rbGrupo2Opcion2){
                    puntajeInversor+=1}
                if (rbSeleccionadoGrupo2 ==rbGrupo2Opcion3)
                    puntajeInversor += 2
                }
            if (idRbSeleccionadoGrupo3 ==-1)  {
                Toast.makeText(this, "Debe responder la tercera pregunta", Toast.LENGTH_LONG).show()
                error+="Debe responder la tercera pregunta"
                }
            else{
                if (rbSeleccionadoGrupo3==rbGrupo3Opcion2){
                    puntajeInversor+=1}
                if (rbSeleccionadoGrupo3 == rbGrupo3Opcion3)
                    puntajeInversor += 2
                }
            if (error=="") {
                if (puntajeInversor < 2) tipoInversor = "Conservador"
                else {
                    if (puntajeInversor < 5) tipoInversor = "Moderado"
                    else tipoInversor = "Agresivo"}

                tvInstrucciones.text ="Puntaje inversor ${puntajeInversor.toString()}\nTipo inversor $tipoInversor "
                datosAlmacenados.edit().apply {
                    putString("tipoInversor", tipoInversor)
                    apply()
                }
            }
            else{
                tvInstrucciones.text ="No se pudo realizar la operacion\n$error"
            }

        }
        //Boton volver
        btVolver.setOnClickListener {
            finish()}
        }
}