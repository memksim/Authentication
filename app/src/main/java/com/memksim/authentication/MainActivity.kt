package com.memksim.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.memksim.authentication.databinding.ActivityMainBinding
import com.memksim.authentication.view.fragments.AuthPageFragment

const val APP_TAG = "AuthenticationTestTag"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}