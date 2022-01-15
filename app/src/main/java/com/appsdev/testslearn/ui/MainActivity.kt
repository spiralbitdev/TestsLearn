package com.appsdev.testslearn.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.appsdev.testslearn.R
import com.appsdev.testslearn.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpClicks()
    }

    private fun setUpClicks() = with(binding) {

    }
}