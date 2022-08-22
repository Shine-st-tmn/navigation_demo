package com.shint_st.feature_one

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shint_st.feature_one.databinding.FragmentOneBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OneFragment : Fragment() {
    private var _binding: FragmentOneBinding? = null
    private val binding get() = _binding!!

    private val model: OneViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mbSomething.setOnClickListener {
            model.makeSomething()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}