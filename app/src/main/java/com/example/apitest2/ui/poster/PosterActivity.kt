package com.example.apitest2.ui.poster


import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.apitest2.R
import com.example.apitest2.presentation.poster.PosterViewModel

class PosterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)

        // достаём URL из интента (ключ тот же, что ты уже используешь)
        val imgUrl = intent.getStringExtra("poster") ?: ""

        // добавляем фрагмент только один раз
        if (savedInstanceState == null) {
            val fragment = PosterFragment.newInstance(imgUrl)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()

        }

    }
}
