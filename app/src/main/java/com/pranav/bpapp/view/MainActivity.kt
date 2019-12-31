package com.pranav.bpapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pranav.bpapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(
                R.id.main_activity,
                MainActivityFragment(),this.localClassName)
            .commit()

    }
}
