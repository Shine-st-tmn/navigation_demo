package com.shint_st.feature_two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shint_st.feature_two.databinding.FragmentTwoBinding
import com.shint_st.navigation.api.NavAction
import com.shint_st.navigation.api.NavRouter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class TwoFragment : Fragment() {
    private var _binding: FragmentTwoBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var router: NavRouter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTwoBinding.inflate(inflater, container, false)
        binding.mbNavigateToThree.setOnClickListener {
            router.executeAction(
                NavAction.ForwardStack(
                    listOf(
                        ThreeFragmentRoute(
                            ThreeFragmentGraphUnit.Parameters("42 is antwort")
                        ),
                        FourFragmentRoute(
                            FourFragmentGraphUnit.Parameters(
                                "test text"
                            )
                        )
                    )
                )
            )
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}