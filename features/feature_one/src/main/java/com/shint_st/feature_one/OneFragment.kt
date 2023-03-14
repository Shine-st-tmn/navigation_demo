package com.shint_st.feature_one

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shint_st.feature_one.databinding.FragmentOneBinding
import com.shint_st.login.navigation.LoginRoute
import com.shint_st.navigation.api.INavHolder
import com.shint_st.navigation.api.NavCommand
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OneFragment : Fragment() {
    private var _binding: FragmentOneBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navHolder: INavHolder

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
            navHolder.getNavigator()?.executeCommand(NavCommand.NewStack(LoginRoute))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}