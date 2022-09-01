package com.shint_st.feature_two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shint_st.feature_two.navigation.ThreeFragmentGraphUnit.Companion.ARGUMENTS
import com.shint_st.feature_two.databinding.FragmentThreeBinding
import com.shint_st.feature_two.navigation.*
import com.shint_st.navigation.api.NavActionsMapper
import com.shint_st.navigation.api.NavCommand
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ThreeFragment : Fragment() {
    private var _binding: FragmentThreeBinding? = null
    private val binding get() = _binding!!

    private val args by lazy {
        arguments?.getParcelable<ThreeFragmentGraphUnit.Parameters>(ARGUMENTS)
    }

    @Inject
    lateinit var router: NavActionsMapper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThreeBinding.inflate(inflater, container, false)
        args?.let { binding.text.text = it.text }
        binding.mbNavigateToFour.setOnClickListener { router.navigate(FourFragmentAction()) }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}