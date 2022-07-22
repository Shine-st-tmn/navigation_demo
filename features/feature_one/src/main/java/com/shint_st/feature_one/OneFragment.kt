package com.shint_st.feature_one

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shint_st.feature_one.databinding.FragmentOneBinding
import com.shint_st.navigation.NavigationController
import com.shint_st.navigation.Tab
import com.shint_st.navigation.safe_args.ThreeFragmentArgs

class OneFragment : Fragment() {
    private var _binding: FragmentOneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOneBinding.inflate(inflater, container, false)

        binding.mbNavigateToThree.setOnClickListener {
            val navController = activity as? NavigationController

            navController?.selectTab(Tab.FEATURE_TWO) {
                findNavController().navigate(
                    com.shint_st.navigation.R.id.action_to_fragment_three,
                    ThreeFragmentArgs(42, "is answer").buildBundle()
                )
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}