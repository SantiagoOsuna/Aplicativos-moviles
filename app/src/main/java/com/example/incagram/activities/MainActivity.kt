package com.example.incagram.activities

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment // <-- 1. Importación añadida
import androidx.fragment.app.FragmentTransaction // <-- 2. Importación añadida
import com.example.incagram.R
import com.example.incagram.fragments.MiPerfilFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    // Declaración de variables
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var sharedPreferences: SharedPreferences

    // Constantes
    companion object {
        private const val PREFS_NAME = "UserData"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Métodos principales
        inicializarVistas()
        setupSharedPreferences()
        setupDrawerLayout()
        setupNavigationView()
    }

    private fun setupNavigationView() {
        navigationView.setNavigationItemSelectedListener(this)
    }

    // 3. Este método estaba vacío y no hacía nada, se puede eliminar o dejar así si planeas añadirle funcionalidad después.
    private fun setupDrawerLayout() {
        // ViewCompat.setOnApplyWindowInsetsListener(drawerLayout) { v, insets -> ... }
        // El código original estaba incompleto.
    }

    private fun setupSharedPreferences() {
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE) // <-- 4. Cambiado a MODE_PRIVATE
    }

    // 5. La llave de cierre estaba en el lugar incorrecto, movida al final de la clase.
    // }

    private fun inicializarVistas() {
        drawerLayout = findViewById(R.id.main)
        navigationView = findViewById(R.id.navigation_view)

        // Configuración del menú hamburguesa
        findViewById<android.widget.ImageView>(R.id.ic_menu_hanburguer).setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    override fun onNavigationItemSelected(item: android.view.MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_home -> {
                // Lógica para el menú Home
            }
            R.id.menu_perfil -> {
                loadFragment(MiPerfilFragment())
            }
            R.id.menu_configuracion -> {
                // Lógica para el menú Configuración
            }
            R.id.menu_cerrar_sesion -> {
                // Lógica para cerrar sesión
            }
        } // <-- 6. Llave de cierre faltante para el 'when'
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

        // 7. La línea de abajo fue eliminada porque el drawer ya se cierra en onNavigationItemSelected
        // drawerLayout.closeDrawer(GravityCompat.START)
    }

} // <-- 5. Llave de cierre principal de la clase
