package com.example.incagram.activities

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.incagram.R

class EditarPerfilActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        val etName = findViewById<EditText>(R.id.et_NameUser)
        val etLastname = findViewById<EditText>(R.id.et_LastnameUser)
        val etEmail = findViewById<EditText>(R.id.et_EmailU)
        val etPhone = findViewById<EditText>(R.id.et_PhoneU)
        val etCurrentPass = findViewById<EditText>(R.id.et_CurrentPassword)
        val etNewPass = findViewById<EditText>(R.id.et_NewPassword)
        val btnGuardar = findViewById<Button>(R.id.btn_guardar)
        val btnCancelar = findViewById<Button>(R.id.btn_cancelar)

        // Cargar datos actuales
        etName.setText(sharedPreferences.getString("nombres", ""))
        etLastname.setText(sharedPreferences.getString("apellidos", ""))
        etEmail.setText(sharedPreferences.getString("email", ""))
        etPhone.setText(sharedPreferences.getString("telefono", ""))

        btnGuardar.setOnClickListener {
            val currentPass = etCurrentPass.text.toString().trim()
            val newPass = etNewPass.text.toString().trim()
            val savedPass = sharedPreferences.getString("password", "")

            val editor = sharedPreferences.edit()
            editor.putString("nombres", etName.text.toString().trim())
            editor.putString("apellidos", etLastname.text.toString().trim())
            editor.putString("email", etEmail.text.toString().trim())
            editor.putString("telefono", etPhone.text.toString().trim())

            // Solo actualizar la contraseña si los campos están llenos
            if (currentPass.isNotEmpty() || newPass.isNotEmpty()) {
                if (currentPass == savedPass) {
                    editor.putString("password", newPass)
                    Toast.makeText(this, "Contraseña actualizada correctamente", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Contraseña actual incorrecta", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            editor.apply()

            Toast.makeText(this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show()

            // Devolver resultado exitoso
            val resultIntent = Intent()
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        btnCancelar.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}
