package com.aditprayogo.core.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_data_store")

class UserPreferences @Inject constructor(@ApplicationContext context: Context) {

    private val appContext = context.applicationContext

    val nik: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[NIK]
        }

    val password: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[PASSWORD]
        }

    suspend fun saveUser(nik: String, password: String) {
        appContext.dataStore.edit { preferences ->
            preferences[NIK] = nik
            preferences[PASSWORD] = password
        }
    }

    suspend fun clear() {
        appContext.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val NIK = stringPreferencesKey("key_nik")
        private val PASSWORD = stringPreferencesKey("key_password")
    }

}