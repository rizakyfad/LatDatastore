package com.rizaki.latdatastore

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserManager(context : Context) {
    private val dataStore : DataStore<Preferences> = context.createDataStore("user-prefs")

    companion object{
        val NAMA = preferencesKey<String>("nama")
        val UMUR = preferencesKey<Int>("umur")
    }

    suspend fun saveData(nama : String, umur : Int){
        dataStore.edit {
            it[NAMA] = nama
            it[UMUR] = umur
        }
    }

    val userName : Flow<String> = dataStore.data.map {
        it[NAMA] ?: ""
    }

    val userUmur : Flow<Int> = dataStore.data.map {
        it[UMUR] ?: 0
    }

    suspend fun clearData(){
        dataStore.edit {
            it.clear()
        }
    }
}