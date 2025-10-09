package com.example.incagram.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.incagram.R


class MiPerfilFragment : Fragment() {
    // Inicializar las variables
    private lateinit var tvNombre: TextView
    private lateinit var tvApellido: TextView
    private lateinit var tvCorreo: TextView
    private lateinit var tvTelefono: TextView
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        private const val PREFS_NAME = "UserData"
        private const val KEY_NOMBRE = "nombres"
        private const val KEY_APELLIDO = "apellidos"
        private const val KEY_CORREO = "email"
        private const val KEY_TELEFONO = "telefono"

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View?{
        val view = inflater.inflate(R.layout.fragment_mi_perfil, container, false)

        inicializarVistas(view)
        setupSharedPreferences()
        cargarDatosUsuario()

        return view

    }

    private fun cargarDatosUsuario() {
        val nombre = sharedPreferences.getString(KEY_NOMBRE, "") ?: ""
        val apellido = sharedPreferences.getString(KEY_APELLIDO, "") ?: ""
        val correo = sharedPreferences.getString(KEY_CORREO, "") ?: ""
        val telefono = sharedPreferences.getString(KEY_TELEFONO, "") ?: ""

        //Mostrar la información al usuario
        tvNombre.text = nombre
        tvApellido.text = apellido
        tvCorreo.text = correo
        tvTelefono.text = telefono
    }


    private fun setupSharedPreferences() {
        sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, 0)
    }



    private fun inicializarVistas(view: View) {
        tvNombre = view.findViewById(R.id.tv_NameUser)
        tvApellido = view.findViewById(R.id.tv_LastnameUser)
        tvCorreo = view.findViewById(R.id.tv_EmailU)
        tvTelefono = view.findViewById(R.id.tv_PhoneU)
    }
}
