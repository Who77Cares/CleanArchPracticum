package com.example.apitest2.ui.about

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.apitest2.databinding.FragmentAboutBinding
import com.example.apitest2.databinding.FragmentPosterBinding
import com.example.apitest2.domain.models.MovieDetails
import com.example.apitest2.presentation.about.AboutViewModel
import com.example.apitest2.ui.models.AboutState
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
        android.util.Log.d("ABOUT", "arg movieId=$id")
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
            Log.d("ABOUT", "state=$it")
            when(it) {
                is AboutState.Content ->{
                    android.util.Log.d("ABOUT", "movie=${it.movie}")
                    showDetails(it.movie)
                }
                is AboutState.Error -> {
                    android.util.Log.d("ABOUT", "error=${it.message}")
                    showErrorMessage(it.message)
                }
            }
        }
    }

    private fun showErrorMessage(message: String) {
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