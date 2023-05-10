package com.example.uts_160420115.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.uts_160420115.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        navController = (navHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        val navView = findViewById<NavigationView>(R.id.navView)
        NavigationUI.setupWithNavController(navView, navController)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setupWithNavController(navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        navController = (navHostFragment).navController
//        Log.e("curr", navController.currentDestination?.label.toString())
        return when (navController.currentDestination?.label.toString()) {
            "Home" -> {
                if (drawerLayout.isOpen) drawerLayout.close()
                else drawerLayout.open()
                super.onSupportNavigateUp()
            }
            "History" -> {
                if (drawerLayout.isOpen) drawerLayout.close()
                else drawerLayout.open()
                super.onSupportNavigateUp()
            }
            "Profile" -> {
                if (drawerLayout.isOpen) drawerLayout.close()
                else drawerLayout.open()
                super.onSupportNavigateUp()
            }
            else -> NavigationUI.navigateUp(navController, drawerLayout)
        }

    }

}