package com.rizaki.latdatastore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.rizaki.latdatastore.cobasplash.SplashActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeActivity : AppCompatActivity() {
    private lateinit var userLoginManager: LoginManager
    private lateinit var username : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userLoginManager = LoginManager(this)

        userLoginManager.username.asLiveData().observe(this){
            username = it.toString()
            result_final.text = "Halo, $username"
        }

        button_logout.setOnClickListener {
            GlobalScope.launch {
                userLoginManager.clearDataLogin()
                startActivity(Intent(this@HomeActivity, SplashActivity::class.java))
            }
        }
    }
}