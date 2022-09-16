package com.shint_st.navigation_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.createGraph
import androidx.navigation.findNavController
import com.shint_st.navigation.NavRouterHolderFactory
import com.shint_st.navigation.api.NavGraphComposer
import com.shint_st.navigation.api.NavScope
import com.shint_st.navigation.utils.setupWithDSLNavController
import com.shint_st.navigation_demo.databinding.ActivityMainBinding
import com.shint_st.navigation_demo.navigation.TopGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val navigationGraph: NavGraphComposer by lazy { TopGraph(true) }

    private lateinit var binding: ActivityMainBinding
    private val navHolder by lazy {
        NavRouterHolderFactory.getInstance()
    }
    private val navController by lazy {
        findNavController(R.id.nav_host_fragment_activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController.graph = navController.createGraph(navigationGraph.startDestination, "MAIN") {
            also(navigationGraph.provideGraph())
        }

        binding.navView.setupWithDSLNavController(
            navController, mapOf(
                R.id.feature_one_navigation to NavScope.HOME,
                R.id.feature_two_navigation to NavScope.HUB
            )
        )
    }

    override fun onResume() {
        super.onResume()
        navHolder.setNavController(navController)
    }

    override fun onPause() {
        super.onPause()
        navHolder.clearNavController()
    }
}