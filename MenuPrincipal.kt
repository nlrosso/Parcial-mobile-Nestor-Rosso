package ar.com.parcial1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_principal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvBienvenida= findViewById<TextView>(R.id.tvBienvenida)
        val datosAlmacenados = getSharedPreferences("loginPref", Context.MODE_PRIVATE)
        //Toast.makeText(this, "$aceptado", Toast.LENGTH_LONG).show()
        val btTyC = findViewById<Button>(R.id.Aceptar_terminos)
        val btNuevaComparacion = findViewById<Button>(R.id.Nueva_comparacion)
        val btVerHistorico= findViewById<Button>(R.id.btVerHistorico)
        val btTestInversor= findViewById<Button>(R.id.btTestInversor)
        var error =""

        //Boton terminos y condiciones
        btTyC.setOnClickListener {
            val intent = Intent(this, TyC::class.java).apply {
            }
            // Iniciar la segunda actividad
            startActivity(intent)
        }


        //Boton calcular
        btNuevaComparacion.setOnClickListener {
            val aceptado = datosAlmacenados.getBoolean("estaAceptado",false)
            var tipoInversor = datosAlmacenados.getString("tipoInversor","")
            error=""
            if (!aceptado){
                error="Debes aceptar los terminos y condiciones para poder calcular\n"
            }
            if (tipoInversor==""){
                error +="Debes realizar el test de inversor antes de poder calcular"
            }
            if (error=="" ){
                val intent = Intent(this, nuevaComparacion::class.java).apply{}
                startActivity(intent)
            }
            else{
                Toast.makeText(this,error, Toast.LENGTH_LONG).show()
                tvBienvenida.text=error
            }
        }
        //Boton ir a historicos

        btVerHistorico.setOnClickListener {
            val aceptado = datosAlmacenados.getBoolean("estaAceptado",false)
            if (aceptado) {
                val intent = Intent(this, HistoricoComparaicones::class.java).apply {}
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Debes aceptar los terminos y condiciones antes de poder ver el historico de comparaciones", Toast.LENGTH_LONG).show()
            }
        }
        //Boton ir a testInversor
        btTestInversor.setOnClickListener {
            val intent = Intent(this, TestInversor::class.java).apply{}
            startActivity(intent)
        }

    }

}