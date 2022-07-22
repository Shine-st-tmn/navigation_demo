package com.shint_st.navigation.safe_args

import android.os.Bundle
import androidx.core.os.bundleOf
import com.shint_st.navigation.safe_args.BaseArgs.Companion.REQUIRED_PARAM
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class ThreeFragmentArgs(
    val id: Int,
    val guid: String
) : BaseArgs {
    override fun buildBundle(): Bundle = bundleOf(
        KEY to this,
        REQUIRED_PARAM to Random().nextInt()
    )

    companion object {
        private const val KEY = "ThreeFragmentArgs"
        fun parseFromBundle(data: Bundle): ThreeFragmentArgs? = data.getParcelable(KEY)
    }
}
