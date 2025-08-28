package com.example.apitest2.navigation

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.apitest2.R
import com.example.apitest2.databinding.ActivityRootBinding
import com.example.apitest2.movies.ui.MoviesFragment
import com.example.apitest2.navigation.navigation_fragment.NavigatorHolder
import com.example.apitest2.navigation.navigation_fragment.NavigatorMyImpl
import org.koin.android.ext.android.inject

class RootActivity: AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

    // Заинжектили NavigatorHolder,
    // чтобы прикрепить к нему Navigator
    private val navigatorHolder: NavigatorHolder by inject()

    // Создали Navigator
    private val navigator = NavigatorMyImpl(
        fragmentContainerViewId = R.id.rootFragmentContainerView,
        fragmentManager = supportFragmentManager
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Главное — добавить этот фрагмент под условием if (savedInstanceState == null),
        // чтобы фрагмент не добавлялся при изменениях конфигурации, например при перевороте экрана.


        if (savedInstanceState == null) {
            // С помощью навигатора открываем первый экран
            navigator.openFragment(
                MoviesFragment()
            )
        }
    }

    // Прикрепляем Navigator к NavigatorHolder
    override fun onResume() {
        super.onResume()
        navigatorHolder.attachNavigator(navigator)
    }

    // Открепляем Navigator от NavigatorHolder
    override fun onPause() {
        super.onPause()
        navigatorHolder.detachNavigator()
    }

    // Сначала мы создали Navigator, проинициализировали его с помощью идентификатора контейнера и supportFragmentManager.
    //Затем заполнили NavigatorHolder и добавили переопределение методов onResume и onPause, в которых мы прикрепляем Navigator к холдеру. Так навигацию безопасно осуществлять, когда пользователю виден экран и он может с ним взаимодействовать.
    //Наконец, переписали код добавления первого экрана. Теперь логика работы с FragmentManager находится в Navigator.
}