package com.example.coopletesttask

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.coopletesttask.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()
    private lateinit var navController: NavController
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (navController.currentDestination?.id == R.id.searchCityFragment) {
                finish()
            } else {
                navController.navigateUp()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = extractNavController()
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

    }

    private fun extractNavController(): NavController {
        val fragmentId = binding.fragmentContainerView.id
        val navHostFragment = supportFragmentManager.findFragmentById(fragmentId) as NavHostFragment
        Timber.tag(TAG).d("State Updates: $navHostFragment")
        return navHostFragment.navController
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}