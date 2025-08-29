package com.example.apitest2.adout.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.apitest2.R
import com.example.apitest2.databinding.FragmentDetailsBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailsFragment: Fragment() {

    companion object {
        private const val ARGS_MOVIE_ID = "movie_id"
        private const val ARGS_POSTER_URL = "poster_url"

        const val TAG = "DetailsFragment"

        fun createArgs(movieId: String, posterUrl: String): Bundle =
            bundleOf(ARGS_MOVIE_ID to movieId,
                ARGS_POSTER_URL to posterUrl)

    }

    private  lateinit var binding: FragmentDetailsBinding
    private lateinit var tabMediator: TabLayoutMediator // ? тут убрали null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Поменяли использование intent на arguments
        // Здесь мы добавили companion object с методом newInstance. В этом методе примечательно
        // только то, что мы пробросили аргументы в Bundle с помощью функции
        // androidx.core.os.bundleOf, которая позволяет инициализировать Bundle с помощью пар «ключ-значение».
        val posterUrl = requireArguments().getString(ARGS_POSTER_URL) ?: ""
        val movieId = requireArguments().getString(ARGS_MOVIE_ID) ?: ""



        Log.d("DETAILS", "movieId=$movieId poster=$posterUrl")

        binding.viewPager.setCurrentItem(1, false)


        binding.viewPager.adapter = DetailsViewPagerAdapter(
            fragmentManager = childFragmentManager,
            lifecycle = lifecycle,
            posterUrl = posterUrl,
            movieId = movieId,
        )



        tabMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = getString(R.string.poster)
                1 -> tab.text = getString(R.string.details)
            }
        }
        tabMediator.attach()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        tabMediator.detach()

    }

    //По уже знакомой схеме мы перенесли логику создания View в метод жизненного цикла onCreateView, а логику инициализации UI-элементов вынесли в onViewCreated. Затем оставалось только переместить вызов tabsMediator.detach() из onDestroy в onDestroyView.
    //Пока приложение не собирается. Ведь у Fragment нет доступа к Intent, в отличие от Activity. Раньше мы осуществляли навигацию из Fragment в Activity, а сейчас нам нужно перейти из MoviesFragment в другой Fragment.
    //Энди
    //Пришло время FragmentManager!


}