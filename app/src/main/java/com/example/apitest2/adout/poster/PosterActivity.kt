package com.example.apitest2.adout.poster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apitest2.R

class PosterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)

        // достаём URL из интента (ключ тот же, что ты уже используешь)
        val imgUrl = intent.getStringExtra("poster") ?: ""

        // добавляем фрагмент только один раз
        if (savedInstanceState == null) {
            val fragment = PosterFragment.Companion.newInstance(imgUrl)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()

        }

    }
}