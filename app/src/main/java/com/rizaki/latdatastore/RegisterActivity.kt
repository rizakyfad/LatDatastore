package com.rizaki.latdatastore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RegisterActivity : AppCompatActivity() {
    private lateinit var userLoginManager : LoginManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        userLoginManager = LoginManager(this)
        button_register.setOnClickListener {
            val username = register_username.text.toString()
            val password = register_password.text.toString()
            GlobalScope.launch {
                userLoginManager.saveDataLogin(username, password)
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            }
        }
    }
}