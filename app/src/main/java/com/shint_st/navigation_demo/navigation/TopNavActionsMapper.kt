package com.shint_st.navigation_demo.navigation

import com.shint_st.feature_two.navigation.FeatureTwoNavActionsMapper
import com.shint_st.navigation.api.NavAction
import com.shint_st.navigation.api.NavActionsMapper
import dagger.hilt.android.components.ActivityComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import javax.inject.Inject

@BoundTo(supertype = NavActionsMapper::class, component = ActivityComponent::class)
class TopNavActionsMapper @Inject constructor(
    featureTwo: FeatureTwoNavActionsMapper
) : NavActionsMapper {
    private val mappers = listOf<NavActionsMapper>(
        featureTwo
    )

    override fun navigate(action: NavAction): Boolean {
        for (mapper in mappers) {
            if (mapper.navigate(action)) {
                return true
            } else {
                continue
            }
        }

        return false
    }
}