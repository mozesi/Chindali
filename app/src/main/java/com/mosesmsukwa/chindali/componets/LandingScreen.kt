package com.mosesmsukwa.chindali.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mosesmsukwa.chindali.R

@Composable
fun LandingScreen(modifier: Modifier = Modifier, proceedToHomeScreen: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color.Blue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null
        )
        ButtonWithIcon(
            iconResource = painterResource(id = R.drawable.ic_launcher_foreground),
            label = "Proceed",
            onClickAction = proceedToHomeScreen,
            modifier = modifier.fillMaxWidth()
        )
    }
}