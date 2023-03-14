package com.shint_st.feature_two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shint_st.feature_two.databinding.FragmentFourBinding
import com.shint_st.feature_two.navigation.FourFragmentGraphUnit
import com.shint_st.navigation.utils.getNavArgs
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FourFragment : Fragment() {
    private var _binding: FragmentFourBinding? = null
    private val binding get() = _binding!!

    private val args by lazy {
        findNavController().getNavArgs<FourFragmentGraphUnit.Parameters>(FourFragmentGraphUnit.TAG)
            ?: error("No arguments")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFourBinding.inflate(inflater, container, false)
        binding.text.text = args.text

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}