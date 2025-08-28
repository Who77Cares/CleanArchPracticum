package com.example.apitest2.navigation.navigation_fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

class NavigatorMyImpl(
    override val fragmentContainerViewId: Int,
    override val fragmentManager: FragmentManager
): NavigatorMy {
    override fun openFragment(fragment: Fragment) {
        fragmentManager.commit {
            replace(fragmentContainerViewId, fragment)
            addToBackStack(null)
        }
    }


}