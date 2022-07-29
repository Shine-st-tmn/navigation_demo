package com.shint_st.feature_two

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shint_st.feature_two.databinding.FragmentTwoBinding
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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
            val params = ThreeFragment.Parameters("42 is antwort")
            val argument = Uri.encode(Json.encodeToString(params))
            findNavController().navigate("${ThreeFragment.NAVIGATION_ID}/$argument")
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val NAVIGATION_ID = "TwoFragment_destination"
    }
}