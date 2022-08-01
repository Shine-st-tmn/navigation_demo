package com.shint_st.navigation_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.createGraph
import androidx.navigation.findNavController
import com.shint_st.navigation.NavigationGraph
import com.shint_st.navigation_demo.databinding.ActivityMainBinding
import com.shint_st.navigation_demo.navigation.setupWithDSLNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var navigationGraph: NavigationGraph
    private lateinit var binding: ActivityMainBinding
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