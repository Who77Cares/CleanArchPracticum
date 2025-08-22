package com.example.apitest2.movies.ui

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.apitest2.network.api.MoviesInteractor
import com.example.apitest2.movies.domain.SearchHistoryInteractor
import com.example.apitest2.movies.domain.models.Movie


class MoviesViewModel(context: Context,
                      private val moviesInteractor: MoviesInteractor,
                      private val historyMoviesInteractor: SearchHistoryInteractor
): ViewModel() {

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private val SEARCH_REQUEST_TOKEN = Any()

    }

    private val stateLiveData = MutableLiveData<MoviesState>()
    fun observeState(): LiveData<MoviesState> = stateLiveData

    private val showToast = SingleLiveEvent<String?>()
    fun observeShowToast(): LiveData<String?> = showToast

    private val historyMovies = MutableLiveData<List<Movie>>()
    fun observeHistoryMovies(): LiveData<List<Movie>> = historyMovies



    private var lastSearchText: String = ""

    private val handler = Handler(Looper.getMainLooper())

    fun searchDebounce(changedText: String) {

        if (lastSearchText == changedText) {
            return
        }

        this.lastSearchText = changedText
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)

        val searchRunnable = Runnable { searchRequest(changedText) }

        val postTime = SystemClock.uptimeMillis() + SEARCH_DEBOUNCE_DELAY
        handler.postAtTime(
            searchRunnable,
            SEARCH_REQUEST_TOKEN,
            postTime,
        )

    }

     fun loadHistory() {
        historyMoviesInteractor.getHistory(
            object : SearchHistoryInteractor.HistoryConsumer {
                override fun consume(searchHistory: List<Movie>?) {
                    historyMovies.postValue(searchHistory ?: emptyList())
                }
            }
        )
    }

     fun saveToHistory(movie: Movie){
        historyMoviesInteractor.saveToHistory(movie)

    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            renderState(
                MoviesState.Loading
            )

            moviesInteractor.searchMoviesInt(
                newSearchText, object : MoviesInteractor.MoviesConsumer {
                    override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                        handler.post {
                            // Готовим список найденных фильмов для передачи в конструктор MoviesState

                            val movies = mutableListOf<Movie>()
                            if (foundMovies != null) {
                                movies.addAll(foundMovies)
                            }

                            when {
                                errorMessage != null -> {
                                    renderState(
                                        MoviesState.Error(
                                            errorMessage = "Что-то пошло не так"
                                        )
                                    )

                                    showToast.postValue(errorMessage)
                                }

                                movies.isEmpty() -> {
                                    renderState(
                                        MoviesState.Empty(
                                            message = "Ничего не найдено"
                                        )
                                    )
                                }

                                else -> {
                                    renderState(
                                        MoviesState.Content(
                                            movies = movies
                                        )
                                    )

                                    showToast.postValue("Вот список фильмов, амиго!")
                                }
                            }
                        }
                    }

                }
            )
        }
    }

    private fun renderState(state: MoviesState) {
        stateLiveData.postValue(state)

    }

    override fun onCleared() {
        super.onCleared()
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
    }

}