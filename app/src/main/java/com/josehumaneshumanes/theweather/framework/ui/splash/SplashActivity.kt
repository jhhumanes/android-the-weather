package com.josehumaneshumanes.theweather.framework.ui.splash

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.josehumaneshumanes.theweather.framework.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupObserver()

        viewModel.onCreate()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewModel.navigate.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collect {
                if (it) navigateToMain()
            }

            viewModel.showError.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collect {
                if (it) Toast.makeText(this@SplashActivity, "No se pudieron cargar los datos", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}
