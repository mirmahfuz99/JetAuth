package com.jetauth.home.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jetauth.ui.theme.JetAuthTheme

@Composable
fun HomeScreen(){
    Scaffold (
        content = {innerPadding ->
            Box (
                modifier = Modifier.padding(innerPadding).fillMaxSize()
            ){
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {
                    Text(
                        text = """ Home Screen"""
                    )
                }
            }

        }
    )
}


@Preview(name = "HomeScreen light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Preview(name = "Sign in dark theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomePreview() {
    JetAuthTheme {
        HomeScreen()
    }
}
