package com.example.fragmentwithmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        supportFragmentManager.beginTransaction().replace(R.id.fragment, RecyclerFragment()).commit()

        bottomNavigationView.setOnItemSelectedListener {
            var fragment: Fragment? = null
            fragment = when(it.itemId) {
                R.id.calculatorMenuItem -> CalculatorFragment()
                R.id.recyclerMenuItem -> RecyclerFragment()
                R.id.networkMenuItem -> NetworkFragment()
                else -> null
            }
            if (fragment != null) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit()
            }

            return@setOnItemSelectedListener true
        }

    }
}