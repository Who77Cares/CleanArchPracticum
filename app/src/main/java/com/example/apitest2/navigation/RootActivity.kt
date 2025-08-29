package com.example.apitest2.navigation

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.apitest2.databinding.ActivityRootBinding

class RootActivity: AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // В каком-то дополнительном коде, связанном с навигацией в классе RootActivity, больше нет необходимости:
    }
}