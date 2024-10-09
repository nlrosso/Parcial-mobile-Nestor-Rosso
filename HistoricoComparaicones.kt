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
import kotlin.math.round
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class HistoricoComparaicones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historico_comparaicones)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btAtras= findViewById<Button>(R.id.volver)
        var limiteBusqueda : Int
        val tvComparacion1=findViewById<TextView>(R.id.tvComparacion1)
        val datosAlmacenados = getSharedPreferences("loginPref", Context.MODE_PRIVATE)
        val historicoComparaciones: String? = datosAlmacenados.getString("Historico","")
        val arrayHistorico = (historicoComparaciones.toString()).split("/")
        var textoFinal=""
        class Comparacion(cadena:String){
            //val texto=cadena}
            val datos= cadena.split("¿")
            val monto=datos[0].toDouble()
            val plazo=datos[1].toInt()
            val banco1=datos[2].toString()
            val tasaBanco1=datos[3].toDouble()
            val banco2=datos[4].toString()
            val tasaBanco2=datos[5].toDouble()
            val rendimientoBanco1= tasaBanco1 * plazo * monto/36000
            val roiBanco1= rendimientoBanco1 *100 / monto
            val rendimientoBanco2= tasaBanco2 * plazo * monto/36000
            val roiBanco2= rendimientoBanco2 *100 / monto
            var texto=""
            lateinit var ganador: String
            init {
                if (roiBanco1 > roiBanco2) {
                    ganador = "$banco1 ofrece mayor rendimiento que $banco2"
                }
                if (roiBanco1< roiBanco2){
                    ganador="$banco2 ofrece mayor rendimiento que $banco1" }
                if (roiBanco1 == roiBanco2){ganador="$banco2 ofrece el mismo rendimiento que $banco1"}
                texto ="Monto $monto$   plazo $plazo dias  \n$banco1\n          TNA $tasaBanco1%     rendimiento $rendimientoBanco1$\n          roi $roiBanco1%\n$banco2\n          TNA $tasaBanco2%     rendimiento $rendimientoBanco2$\n          roi $roiBanco2%\n$ganador\n\n\n "
            }
        }

        if (arrayHistorico.size<5) {limiteBusqueda= arrayHistorico.size}
        else {limiteBusqueda= 5}
        var comparacioni=""
        for (i in 1..(limiteBusqueda-1)){
            comparacioni=Comparacion(arrayHistorico[i-1]).texto
            textoFinal += comparacioni
        }
        tvComparacion1.setVisibility(View.VISIBLE)
        tvComparacion1.text=textoFinal
        //tvComparacion1.text="$arrayHistorico\n\n\n$textoFinal\narrayHistorico.size ${arrayHistorico.size}\nTipo ${arrayHistorico::class.simpleName}"

        btAtras.setOnClickListener {
            finish()}

    }
}