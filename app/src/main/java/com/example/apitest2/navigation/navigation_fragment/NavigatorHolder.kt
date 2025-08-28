package com.example.apitest2.navigation.navigation_fragment

import androidx.fragment.app.Fragment

/**
 * Сущность для хранения ссылки на Navigator.
 */


interface NavigatorHolder {

    fun attachNavigator(navigatorMy: NavigatorMy)

    fun detachNavigator()

    fun openFragment(fragment: Fragment)

}