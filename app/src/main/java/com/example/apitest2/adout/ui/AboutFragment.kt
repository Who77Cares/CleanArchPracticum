package com.example.apitest2.adout.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.apitest2.R
import com.example.apitest2.databinding.FragmentAboutBinding
import com.example.apitest2.adout.domain.MovieDetails
import com.example.apitest2.cast.ui.CastActivity
import com.example.apitest2.cast.ui.CastFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AboutFragment: Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding  get() = _binding!!

    companion object {
        private const val MOVIE_ID = "movie_id"

        fun newInstance(movieId: String) = AboutFragment().apply {
            arguments = Bundle().apply {
                putString(MOVIE_ID, movieId)
            }
        }
    }

    private val aboutViewModel: AboutViewModel by viewModel {
        val id = requireArguments().getString(MOVIE_ID)!!
        Log.d("ABOUT", "arg movieId=$id")
        parametersOf(id)
    }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        aboutViewModel.observeState().observe(viewLifecycleOwner) {

            when(it) {
                is AboutState.Content ->{

                    showDetails(it.movie)
                }
                is AboutState.Error -> {

                    showErrorMessage(it.message)
                }
            }
        }

//

//        Здесь мы сначала обратились к родительскому фрагменту (parentFragment) и только потом получили доступ к parentFragmentManager.
//        Смотрим на схему.
//        Получается, мы получили доступ к FragmentManager корневой Activity.
//        Запускаем приложение.
//        Ура! Навигация работает.
//        Удаляем ненужный код MoviesCastActivity и не забываем про AndroidManifest.xml.
//        Вот и всё! Мы перевели всё приложение на подход Single Activity.

        binding.showCastButton.setOnClickListener {
            // Осуществляем навигацию
            parentFragment?.parentFragmentManager?.commit {
                replace(
                    R.id.rootFragmentContainerView,
                    CastFragment.newInstance(
                        movieId = requireArguments().getString(MOVIE_ID).orEmpty()
                    ),
                    CastFragment.TAG
                )
                addToBackStack(CastFragment.TAG)
            }
        }
    }

    private fun showErrorMessage(message: String) {
        Log.d("TEST_", "showErrorMessage: $message")
        binding.apply {
            details.visibility = View.GONE
            errorMessage.visibility = View.VISIBLE
            errorMessage.text = message
        }
    }

    private fun showDetails(movieDetails: MovieDetails) {
        binding.apply {
            details.visibility = View.VISIBLE
            errorMessage.visibility = View.GONE
            title.text = movieDetails.title
            ratingValue.text = movieDetails.imDbRating
            yearValue.text = movieDetails.year
            countryValue.text = movieDetails.countries
            genreValue.text = movieDetails.genres
            directorValue.text = movieDetails.directors
            writerValue.text = movieDetails.writers
            castValue.text = movieDetails.stars
            plot.text = movieDetails.plot
        }

    }


}

