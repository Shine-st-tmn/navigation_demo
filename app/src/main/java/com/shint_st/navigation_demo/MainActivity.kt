package com.shint_st.navigation_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.createGraph
import androidx.navigation.findNavController
import com.shint_st.navigation_demo.databinding.ActivityMainBinding
import com.shint_st.navigation_demo.navigation.TopGraph
import com.shint_st.navigation_demo.navigation.setupWithDSLNavController

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val navigationGraph by lazy { TopGraph() }

    private val navController by lazy {
        findNavController(R.id.nav_host_fragment_activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController.graph = navController.createGraph(navigationGraph.startDestination, "MAIN") {
            also(navigationGraph.navigationGraph)
        }

        binding.navView.setupWithDSLNavController(navController)
    }
}