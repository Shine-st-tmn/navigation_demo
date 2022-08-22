package com.shint_st.feature_two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shint_st.feature_two.ThreeFragmentGraphUnit.Companion.ARGUMENTS
import com.shint_st.feature_two.databinding.FragmentThreeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ThreeFragment : Fragment() {
    private var _binding: FragmentThreeBinding? = null
    private val binding get() = _binding!!

    private val args by lazy {
        arguments?.getParcelable<ThreeFragmentGraphUnit.Parameters>(ARGUMENTS)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThreeBinding.inflate(inflater, container, false)
        args?.let { binding.text.text = it.text }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}