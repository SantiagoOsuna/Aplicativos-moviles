package com.example.incagram

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast

import android.widget.EditText
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {

    private lateinit var SharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        SharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        setupOnClickListeners()
    }

    private fun setupOnClickListeners() {
        val etName = findViewById<EditText>(R.id.et_Name)
        val etLastname = findViewById<EditText>(R.id.et_Lastname)
        val etEmail = findViewById<EditText>(R.id.et_Email)
        val etPhone = findViewById<EditText>(R.id.et_Phone)
        val etPassword = findViewById<EditText>(R.id.et_Password)
        val etRepetirPassword = findViewById<EditText>(R.id.et_RepetirPassword)
        val cbTerminos = findViewById<CheckBox>(R.id.cb_terminos)
        val btnRegistrate = findViewById<Button>(R.id.btn_Registrate)

        btnRegistrate.setOnClickListener {
            val nombres = etName.text.toString().trim()
            val apellidos = etLastname.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val telefono = etPhone.text.toString().trim()
            val password = etPassword.text.toString()
            val repetirPassword = etRepetirPassword.text.toString()
            val cbTerminos = findViewById<CheckBox>(R.id.cb_terminos)

            if (ValidarCampos(nombres, apellidos, email, telefono, password, repetirPassword, cbTerminos)) {
                guardarDatos(nombres, apellidos, email, telefono, password, repetirPassword)
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun ValidarCampos(nombres: String, apellidos: String, email: String, telefono: String, password: String, repetirPassword: String, cbTerminos: CheckBox): Boolean {
        if (nombres.isEmpty()){
            Toast.makeText(this, "Ingrese su nombre", Toast.LENGTH_SHORT).show()
            return false
        }

        if (apellidos.isEmpty()){
            Toast.makeText(this, "Ingrese su apellido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (email.isEmpty()){
            Toast.makeText(this, "Ingrese su email", Toast.LENGTH_SHORT).show()
            return false

        }

        if (telefono.isEmpty()){
            Toast.makeText(this, "Ingrese su telefono", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.isEmpty()){
            Toast.makeText(this, "Ingrese su contraseña", Toast.LENGTH_SHORT).show()
            return false
        }
        if (repetirPassword.isEmpty()){
            Toast.makeText(this, "Repita su contraseña", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!cbTerminos.isChecked) {
            Toast.makeText(this, "Debe aceptar los términos y condiciones", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun guardarDatos(nombres: String, apellidos: String, email: String, telefono: String, password: String, repetirPassword: String) {
        val editor = SharedPreferences.edit()
        editor.putString("nombres", nombres)
        editor.putString("apellidos", apellidos)
        editor.putString("email", email)
        editor.putString("telefono", telefono)
        editor.putString("password", password)
        editor.putString("repetirPassword", repetirPassword)
        editor.apply()
    }
}