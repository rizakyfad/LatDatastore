package com.rizaki.latdatastore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.asLiveData
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var userLoginManager: LoginManager
    private lateinit var username : String
    private lateinit var password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        button_login_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        userLoginManager = LoginManager(this)

        userLoginManager.username.asLiveData().observe(this){
            username = it.toString()
        }
        userLoginManager.password.asLiveData().observe(this){
            password = it.toString()
        }

        button_login.setOnClickListener {
            if(login_username.text.isNullOrEmpty() && login_password.text.isNullOrEmpty()){
                Toast.makeText(this, "Username dan password belum diisi", Toast.LENGTH_SHORT).show()
            }else{
                if(login_username.text.toString() == username && login_password.text.toString() == password){
                    Toast.makeText(this, "Berhasil Login!!", Toast.LENGTH_SHORT).show()
                    userLoginManager.boolean.asLiveData().observe(this){
                        GlobalScope.launch {
                            userLoginManager.setBoolean(true)
                        }
                    }
                    startActivity(Intent(this, HomeActivity::class.java))
                }else{
                    Toast.makeText(this, "Akun Tidak Terdaftar / Tidak Tepat", Toast.LENGTH_SHORT).show()
                }
            }


        }
    }
}