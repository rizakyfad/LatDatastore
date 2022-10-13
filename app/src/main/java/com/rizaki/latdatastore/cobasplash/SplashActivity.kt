package com.rizaki.latdatastore.cobasplash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.rizaki.latdatastore.HomeActivity
import com.rizaki.latdatastore.LoginActivity
import com.rizaki.latdatastore.LoginManager
import com.rizaki.latdatastore.R


class SplashActivity : AppCompatActivity() {
    private lateinit var userLoginManager: LoginManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        userLoginManager = LoginManager(this)
        Handler(Looper.getMainLooper()).postDelayed({
            userLoginManager.boolean.asLiveData().observe(this) {
                if(it == true){
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }else{
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }, 2000)
    }
}