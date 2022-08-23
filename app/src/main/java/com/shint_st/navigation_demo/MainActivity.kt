package com.shint_st.navigation_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.createGraph
import androidx.navigation.findNavController
import com.shint_st.navigation.NavRouterImpl
import com.shint_st.navigation.api.NavGraphComposer
import com.shint_st.navigation.api.NavScope
import com.shint_st.navigation.utils.setupWithDSLNavController
import com.shint_st.navigation_demo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var navigationGraph: NavGraphComposer

    private val router by lazy {
        NavRouterImpl.getInstance(
            findNavController(R.id.nav_host_fragment_activity_main)
        )
    }

    private lateinit var binding: ActivityMainBinding
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
}