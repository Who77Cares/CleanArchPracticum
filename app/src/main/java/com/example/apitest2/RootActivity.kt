package com.example.apitest2

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.apitest2.databinding.ActivityRootBinding
import com.example.apitest2.movies.ui.MoviesFragment

class RootActivity: AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Главное — добавить этот фрагмент под условием if (savedInstanceState == null),
        // чтобы фрагмент не добавлялся при изменениях конфигурации, например при перевороте экрана.

        if (savedInstanceState == null) {
            // Добавляем фрагмент в контейнер
            supportFragmentManager.commit {
                this.add(R.id.rootFragmentContainerView, MoviesFragment())
            }
        }
    }

}