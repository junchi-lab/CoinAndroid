package com.junchi.coinandroid.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.junchi.coinandroid.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_detail) as NavHostFragment
        val navController = navHostFragment.navController
        navController.setGraph(R.navigation.nav_detail, intent.extras)
    }
}