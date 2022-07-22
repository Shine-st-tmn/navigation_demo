package com.shint_st.feature_two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shint_st.feature_two.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {
    private var _binding: FragmentTwoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTwoBinding.inflate(inflater, container, false)

        binding.mbNavigateToThree.setOnClickListener {
            findNavController().navigate(
                com.shint_st.navigation.R.id.action_fragment_two_to_fragment_three,
            )
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}