package com.shint_st.navigation_demo.navigation

import com.shint_st.feature_two.navigation.FeatureTwoNavActionsRouter
import com.shint_st.navigation.api.INavRouter
import com.shint_st.navigation.api.NavAction
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import javax.inject.Inject

@BoundTo(supertype = INavRouter::class, component = ActivityComponent::class)
class TopNavActionRouter @Inject constructor(
    featureTwo: FeatureTwoNavActionsRouter,
    globalActionRouter: GlobalActionRouter
) : INavRouter {

    private val mappers = listOf(
        featureTwo,
        globalActionRouter
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
