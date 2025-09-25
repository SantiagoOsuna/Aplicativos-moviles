package com.example.incagram.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.incagram.R


class MiPerfilFragment : Fragment() {
    private lateinit var tvNombres : TextView
    private lateinit var tvApellidos : TextView
    private lateinit var tvEmail : TextView
    private lateinit var tvTelefono : TextView

    companion object {
        private const val  PREF_NAMES = "user_prefs"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mi_perfil, container, false)
        initViews(view)
        setupSharedPreferences()
        loadUserData()
        return view
    }

    private fun loadUserData() {
        val nombres = sharedPreferences.getString("nombres", "")
        val apellidos = sharedPreferences.getString("apellidos", "")
        val email = sharedPreferences.getString("email", "")
        val telefono = sharedPreferences.getString("telefono", "")
    }

        private fun setupSharedPreferences() {
            sharedPreferences = requireActivity().getSharedPreferences(PREF_NAMES, Context.MODE_PRIVATE)

        }
        private fun initViews(view: View) {
            tvNombres = view.findViewById(R.id.tv_Nombres)
            tvApellidos = view.findViewById(R.id.tv_Apellidos)
            tvEmail = view.findViewById(R.id.tv_Email)
            tvTelefono = view.findViewById(R.id.tv_Telefono)

        }
}