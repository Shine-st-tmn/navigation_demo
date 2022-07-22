package com.shint_st.navigation_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.shint_st.navigation.NavigationController
import com.shint_st.navigation.Tab
import com.shint_st.navigation_demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationController {

    private lateinit var binding: ActivityMainBinding

    private val navController by lazy {
        findNavController(R.id.nav_host_fragment_activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navView.setupWithNavController(navController)
        binding.navView.setOnItemReselectedListener {
            val direction = when (it.itemId) {
                R.id.feature_one_navigation -> R.id.fragment_one_destination
                R.id.feature_two_navigation -> R.id.fragment_two_destination
                else -> return@setOnItemReselectedListener
            }

            navController.popBackStack(direction, false)
        }
    }

    override fun selectTab(tab: Tab, onSelected: (() -> Unit)?) {
        binding.navView.selectedItemId = when (tab) {
            Tab.FEATURE_ONE -> R.id.feature_one_navigation
            Tab.FEATURE_TWO -> R.id.feature_two_navigation
        }

        onSelected?.invoke()
    }
}