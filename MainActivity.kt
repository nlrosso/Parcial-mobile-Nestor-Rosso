package ar.com.parcial1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val loguearse = findViewById<Button>(R.id.btLogin)
        loguearse.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            if(username == "user" && password == "user") {
                Toast.makeText(this,"Login exitoso, dirigiendo a menu principal", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MenuPrincipal::class.java).apply {
                }
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Fallo al validar, revise datos ingresados", Toast.LENGTH_LONG).show()
            }
        }
    }
}