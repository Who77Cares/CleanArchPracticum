package com.example.apitest2.ui.about

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.apitest2.R
import com.example.apitest2.databinding.ActivityDetailsBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailsActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private var tabMediator: TabLayoutMediator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val poster = intent.getStringExtra("poster") ?: ""
        val movieId = intent.getStringExtra("id") ?: ""
        Log.d("DETAILS", "movieId=$movieId poster=$poster")

        binding.viewPager.setCurrentItem(1, false)
        
        binding.viewPager.adapter = DetailsViewPagerAdapter(
            fragmentManager = supportFragmentManager,
            lifecycle = lifecycle,
            posterUrl = poster,
            movieId = movieId
        )

        // Разрешаем свайп
        binding.viewPager.isUserInputEnabled = true

        tabMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = getString(R.string.poster)
                1 -> tab.text = getString(R.string.details)
            }
        }
        tabMediator?.attach()
    }
    override fun onDestroy() {
        tabMediator?.detach()
        tabMediator = null
        super.onDestroy()
    }


}