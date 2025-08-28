package com.example.incagram

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnIngresar = findViewById<Button>(R.id.btn_ingresar)
        val txtRecuperar = findViewById<TextView>(R.id.tv_recupera)
        val txtRegistrar = findViewById<TextView>(R.id.tv_regist)

        btnIngresar.setOnClickListener {
            val intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent)
        }

        txtRecuperar.setOnClickListener {
            val intent = Intent(this, RecuperacionActivity::class.java)
            startActivity(intent)
        }

        txtRegistrar.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }
}
