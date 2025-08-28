package com.example.apitest2.navigation.navigation_fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Navigator — сущность для работы с FragmentManager.
 */

// Navigator знает о контейнере, с которым он работает, и о FragmentManager. Получается, что, создавая разные Navigator со всякими FragmentManager, мы сможем осуществлять навигацию на разных уровнях иерархии фрагментов: обычных и вложенных.
//Интерфейсы созданы, опишем реализации классов.

interface NavigatorMy {


    val fragmentContainerViewId: Int
    val fragmentManager: FragmentManager

    fun openFragment(fragment: Fragment)
}