package com.shint_st.feature_two

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shint_st.feature_two.databinding.FragmentThreeBinding
import com.shint_st.navigation.NavigationParameters
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

class ThreeFragment : Fragment() {
    private var _binding: FragmentThreeBinding? = null
    private val binding get() = _binding!!

    private val args by lazy {
        arguments?.getParcelable<Parameters>(ARGUMENTS)
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

    @Serializable
    @Parcelize
    data class Parameters(
        val text: String
    ) : Parcelable

    class ParametersType : NavigationParameters<Parameters>(true) {
        override fun parseValue(value: String): Parameters = parseValueDefault(value)
    }

    companion object {
        const val NAVIGATION_ID = "ThreeFragment_nav_id"
        const val ARGUMENTS = "ThreeFragment_arguments"
    }
}