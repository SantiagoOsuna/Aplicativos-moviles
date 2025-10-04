package com.example.incagram.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.incagram.R

class PerfilActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var tvName: TextView
    private lateinit var tvLastname: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvNombres: TextView
    private lateinit var btnEditar: Button

    // Registro para esperar el resultado de EditarPerfilActivity
    private val editarPerfilLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            cargarDatosUsuario()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        // Enlazar vistas
        tvName = findViewById(R.id.tv_NameUser)
        tvLastname = findViewById(R.id.tv_LastnameUser)
        tvEmail = findViewById(R.id.tv_EmailU)
        tvPhone = findViewById(R.id.tv_PhoneU)
        tvNombres = findViewById(R.id.tv_Nombres)
        btnEditar = findViewById(R.id.btn_editar)

        cargarDatosUsuario()

        btnEditar.setOnClickListener {
            val intent = Intent(this, EditarPerfilActivity::class.java)
            editarPerfilLauncher.launch(intent)
        }
    }

    private fun cargarDatosUsuario() {
        val nombre = sharedPreferences.getString("nombres", "") ?: ""
        val apellido = sharedPreferences.getString("apellidos", "") ?: ""

        tvName.text = nombre
        tvLastname.text = apellido
        tvEmail.text = sharedPreferences.getString("email", "")
        tvPhone.text = sharedPreferences.getString("telefono", "")

        // Mostrar el nombre completo en el encabezado
        tvNombres.text = "$nombre $apellido".trim()
    }
}
