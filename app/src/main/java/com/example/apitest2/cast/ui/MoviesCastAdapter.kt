package com.example.apitest2.cast.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apitest2.cast.domain.MovieCastPerson

class MoviesCastAdapter: RecyclerView.Adapter<MovieCastViewHolder>() {

    var persons = emptyList<MovieCastPerson>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieCastViewHolder  = MovieCastViewHolder(parent)

    override fun onBindViewHolder(holder: MovieCastViewHolder, position: Int) {
        holder.bind(persons[position])
    }

    override fun getItemCount(): Int = persons.size
}