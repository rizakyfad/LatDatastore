package com.rizaki.latdatastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var userManager : UserManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userManager = UserManager(this)
        button_save_data.setOnClickListener {
            val nama = inputan_nama.text.toString()
            val umur = inputan_usia.text.toString().toInt()
            GlobalScope.launch {
                userManager.saveData(nama, umur)
            }
        }

        userManager.userName.asLiveData().observe(this) {
            result_nama.text = it.toString()
        }
        userManager.userUmur.asLiveData().observe(this){
            result_umur.text = it.toString()
        }

        button_clear_data.setOnClickListener {
            GlobalScope.launch {
                userManager.clearData()
            }
        }
    }
}