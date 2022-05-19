package com.example.deals.presentation.main

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.deals.R
import com.example.deals.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::bind)

//    private val navController by lazy {
//        Navigation.findNavController(this, R.id.nav_host_fragment)
//    }

    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        drawerToggle =
            ActionBarDrawerToggle(this, binding.drawerLayout, R.string.deals, R.string.edit)

        drawerToggle.isDrawerIndicatorEnabled = true
        binding.drawerLayout.addDrawerListener(drawerToggle)

//        val actionBar = supportActionBar
//        actionBar?.title = "Deals"

//        if (actionBar != null) {
//            actionBar.title = "Deals"
//            actionBar.setDisplayHomeAsUpEnabled(true)
            drawerToggle = object : ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                binding.toolbar,
                R.string.deals,
                R.string.edit
            ) {
                override fun onDrawerClosed(view: View) {
                    invalidateOptionsMenu()
                    //drawerOpened = false;
                }

                override fun onDrawerOpened(drawerView: View) {
                    invalidateOptionsMenu()
                    //drawerOpened = true;
                }
            }
            drawerToggle.isDrawerIndicatorEnabled = true
            binding.drawerLayout.addDrawerListener(drawerToggle)
            drawerToggle.syncState()
        //}
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return NavigationUI.navigateUp(navController, binding.drawerLayout)
//    }
//
//    private fun setupDrawerLayout() {
//        binding.leftDrawer.setupWithNavController(navController)
//        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
//    }
//
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }
}