package com.soopeach.thethethe_android.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "account")

class AccountDataStore(private val context: Context) {
    private val accessTokenKey = stringPreferencesKey("accessToken")
    suspend fun getAccessToken() = context.dataStore.data
        .map { preferences ->
            preferences[accessTokenKey]
        }.first()

    suspend fun updateAccessTokenKey(accessToken: String) {
        context.dataStore.edit { preferences ->
            preferences[accessTokenKey] = accessToken
        }
    }
}