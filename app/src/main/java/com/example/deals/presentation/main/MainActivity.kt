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

    private var currentFragment = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setupDrawerLayoutAndToolbar()
    }

    private fun setupDrawerLayoutAndToolbar() {
        binding.drawerLayoutDeals.setOnClickListener {
            if (currentFragment == 1) {
                currentFragment = 0
                findNavController(R.id.nav_host_fragment).popBackStack()
            }
            binding.drawerLayout.close()
        }
        binding.drawerLayoutHelp.setOnClickListener {
            if (currentFragment == 0) {
                currentFragment = 1
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_dealFragment_to_helpFragment)
            }
            binding.drawerLayout.close()
        }

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

    companion object {
        val drawerToggleLiveData = MutableLiveData<Boolean>()
    }
}