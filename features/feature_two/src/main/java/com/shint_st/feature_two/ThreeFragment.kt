package com.shint_st.feature_two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shint_st.feature_two.databinding.FragmentThreeBinding
import com.shint_st.feature_two.navigation.*
import com.shint_st.navigation.api.INavRouter
import com.shint_st.navigation.utils.getNavArgs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ThreeFragment : Fragment() {
    private var _binding: FragmentThreeBinding? = null
    private val binding get() = _binding!!


    private val args by lazy {
        findNavController().getNavArgs<ThreeFragmentGraphUnit.Parameters>(ThreeFragmentGraphUnit.TAG)
            ?: error("No arguments")
    }

    @Inject
    lateinit var router: INavRouter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThreeBinding.inflate(inflater, container, false)
        binding.text.text = args.text
        binding.mbNavigateToFour.setOnClickListener { router.navigate(FourFragmentAction()) }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}