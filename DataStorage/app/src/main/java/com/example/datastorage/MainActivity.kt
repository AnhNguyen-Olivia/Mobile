package com.example.datastorage

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import kotlinx.coroutines.launch
import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers

val Context.dataStore by preferencesDataStore(name = "settings")

private val LANGUAGE_KEY = stringPreferencesKey("language")
private const val LANGUAGE_ENGLISH = "en"
private const val LANGUAGE_SPANISH = "es"
private const val LANGUAGE_VIETNAMESE = "vi"
private const val LANGUAGE_FRENCH = "fr"


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val savedLanguage = runBlocking {
            dataStore.data.first()[LANGUAGE_KEY] ?: LANGUAGE_ENGLISH
        }
        applyLanguage(savedLanguage)

        setContent {
            LanguagePreferenceScreen(initialLanguage = savedLanguage)
        }
    }
}

private suspend fun saveLanguage(context: Context, language: String) {
    context.dataStore.edit { preferences ->
        preferences[LANGUAGE_KEY] = language
    }
}

@Composable
fun LanguagePreferenceScreen(initialLanguage: String) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var selectedLanguage by remember { mutableStateOf(initialLanguage) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.greeting), fontSize = 32.sp)

        Text(
            text = stringResource(id = R.string.language_label),
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 32.dp, bottom = 8.dp)
        )

        Column(modifier = Modifier.selectableGroup()) {
            LanguageOption(
                label = stringResource(id = R.string.language_english),
                selected = selectedLanguage == LANGUAGE_ENGLISH,
                onSelect = {
                    selectedLanguage = LANGUAGE_ENGLISH
                    scope.launch {
                        saveLanguage(context, LANGUAGE_ENGLISH)
                        withContext(Dispatchers.Main) {
                            applyLanguage(LANGUAGE_ENGLISH)
                        }
                    }
                }
            )
            LanguageOption(
                label = stringResource(id = R.string.language_spanish),
                selected = selectedLanguage == LANGUAGE_SPANISH,
                onSelect = {
                    selectedLanguage = LANGUAGE_SPANISH
                    scope.launch {
                        saveLanguage(context, LANGUAGE_SPANISH)
                        withContext(Dispatchers.Main) {
                            applyLanguage(LANGUAGE_SPANISH)
                        }
                    }
                }
            )
            LanguageOption(
                label = stringResource(id = R.string.language_vi),
                selected = selectedLanguage == LANGUAGE_VIETNAMESE,
                onSelect = {
                    selectedLanguage = LANGUAGE_VIETNAMESE
                    scope.launch {
                        saveLanguage(context, LANGUAGE_VIETNAMESE)
                        withContext(Dispatchers.Main) {
                            applyLanguage(LANGUAGE_VIETNAMESE)
                        }
                    }
                }
            )
            LanguageOption(
                label = stringResource(id = R.string.language_fr),
                selected = selectedLanguage == LANGUAGE_FRENCH,
                onSelect = {
                    selectedLanguage = LANGUAGE_FRENCH
                    scope.launch {
                        saveLanguage(context, LANGUAGE_FRENCH)
                        withContext(Dispatchers.Main) {
                            applyLanguage(LANGUAGE_FRENCH)
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun LanguageOption(label: String, selected: Boolean, onSelect: () -> Unit) {
    Row(
        modifier = Modifier.selectable(selected = selected, onClick = onSelect),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = onSelect)
        Text(text = label, fontSize = 18.sp, modifier = Modifier.padding(start = 8.dp))
    }
}

private fun applyLanguage(language: String) {
    val currentLanguage = AppCompatDelegate.getApplicationLocales().toLanguageTags()
    if (currentLanguage != language) {
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(language))
    }
}