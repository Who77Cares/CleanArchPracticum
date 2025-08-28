package com.example.apitest2.cast.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apitest2.R
//import com.example.apitest2.navigation.RVItem
//import com.example.apitest2.databinding.ActivityCastBinding
//import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
//import org.koin.androidx.viewmodel.ext.android.viewModel
//import org.koin.core.parameter.parametersOf
//
//
class CastActivity: AppCompatActivity(R.layout.fragment_cast) {
//
//    companion object {
//        private const val MOVIES_ID = "movie_id"
//
//        fun newInstance(context: Context, moviesId: String): Intent {
//            return Intent(context, CastActivity::class.java).apply {
//                putExtra(MOVIES_ID, moviesId)
//            }
//        }
//    }
//    private val moviesCastViewModel: CastViewModel by viewModel {
//        parametersOf(intent.getStringExtra(MOVIES_ID))
//    }
//
////    private val adapter = MoviesCastAdapter()
//
//    private lateinit var binding: ActivityCastBinding
//
//    private val adapter = ListDelegationAdapter<List<RVItem>>(
//        movieCastHeaderDelegate(),
//        movieCastPersonDelegate(),
//    )
//
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityCastBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Привязываем адаптер и LayoutManager к RecyclerView
//        binding.moviesCastRecyclerView.adapter = adapter
//        binding.moviesCastRecyclerView.layoutManager = LinearLayoutManager(this)
//
//        // Наблюдаем за UiState из ViewModel
//        moviesCastViewModel.observeState().observe(this) {
//            // В зависимости от UiState экрана показываем
//            // разные состояния экрана
//            when (it) {
//                is MoviesCastState.Content -> showContent(it)
//                is MoviesCastState.Error -> showError(it)
//                is MoviesCastState.Loading -> showLoading()
//            }
//        }
//    }
//
//    private fun showLoading() {
//        binding.contentContainer.isVisible = false
//        binding.errorMessageTextView.isVisible = false
//
//        binding.progressBar.isVisible = true
//    }
//
//    private fun showError(state: MoviesCastState.Error) {
//        binding.contentContainer.isVisible = false
//        binding.progressBar.isVisible = false
//
//        binding.errorMessageTextView.isVisible = true
//        binding.errorMessageTextView.text = state.message
//    }
//
//    private fun showContent(state: MoviesCastState.Content) {
//        binding.progressBar.isVisible = false
//        binding.errorMessageTextView.isVisible = false
//
//        binding.contentContainer.isVisible = true
//
//        // Меняем привязку стейта к UI-элементам
//        binding.movieTitle.text = state.fullTitle
//        adapter.items = state.items
//
//        adapter.notifyDataSetChanged()
//    }
//


}