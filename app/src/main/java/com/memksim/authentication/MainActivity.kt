package com.memksim.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.memksim.authentication.databinding.ActivityMainBinding
import com.memksim.authentication.view.fragments.AuthPageFragment

const val APP_TAG = "AuthenticationTestTag"
const val EMAIL_PREFERENCES = "EmailPreferences"
const val PREF_USER_EMAIL = "UserEmail"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding
            .inflate(layoutInflater)
            .also {
                setContentView(it.root)
            }



    }
}