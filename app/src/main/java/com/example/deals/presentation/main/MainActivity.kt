package com.example.deals.presentation.main

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.deals.R
import com.example.deals.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::bind)

    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setupDrawerLayoutAndToolbar()


//        drawerToggle = ActionBarDrawerToggle(
//            this, binding.drawerLayout, binding.toolbar, R.string.edit, R.string.edit
//        )
//        drawerToggle.isDrawerIndicatorEnabled = true
//        drawerToggle.syncState();
//        binding.drawerLayout.addDrawerListener(drawerToggle);
//
//        supportFragmentManager.addOnBackStackChangedListener {
//            if (supportFragmentManager.backStackEntryCount > 0) {
//                supportActionBar?.setDisplayHomeAsUpEnabled(true); // show back button
//                binding.toolbar.setNavigationOnClickListener {
//                    onBackPressed()
//                }
//            } else {
//                //show hamburger
//                supportActionBar?.setDisplayHomeAsUpEnabled(false);
//                drawerToggle.syncState();
//
//                binding.toolbar.setNavigationOnClickListener {
//                    binding.drawerLayout.openDrawer(GravityCompat.START)
//                }
//            }
//        }
    }

    private fun setupDrawerLayoutAndToolbar() {

        drawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.deals,
            R.string.edit
        )
        drawerToggle.setToolbarNavigationClickListener {
            onBackPressed()
        }

        drawerToggle.isDrawerIndicatorEnabled = true
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        drawerToggleLiveData.observe(this) {
            if (it) {
                drawerToggle.isDrawerIndicatorEnabled = false
                binding.drawerLayout.removeDrawerListener(drawerToggle)
                binding.drawerLayout.addDrawerListener(drawerToggle)
                drawerToggle.syncState()
            } else {
                drawerToggle.isDrawerIndicatorEnabled = true
                binding.drawerLayout.removeDrawerListener(drawerToggle)
                binding.drawerLayout.addDrawerListener(drawerToggle)
                drawerToggle.syncState()
            }
        }
    }

    companion object State {
        val drawerToggleLiveData = MutableLiveData<Boolean>()
    }
}