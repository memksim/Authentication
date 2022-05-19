package com.memksim.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.memksim.authentication.view.fragments.AuthPageFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, AuthPageFragment())
                .commit()
        }
    }



}