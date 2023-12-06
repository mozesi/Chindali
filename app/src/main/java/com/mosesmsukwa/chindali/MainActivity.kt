package com.mosesmsukwa.chindali

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mosesmsukwa.chindali.componets.HomeScreen
import com.mosesmsukwa.chindali.componets.LandingScreen
import com.mosesmsukwa.chindali.ui.theme.ChindaliTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChindaliTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var goToLandingScreen by rememberSaveable { mutableStateOf(false) }

                    if (goToLandingScreen) {
                        HomeScreen()
                    } else {
                        LandingScreen(proceedToHomeScreen = { goToLandingScreen = true })
                    }
                }
            }
        }
    }
}