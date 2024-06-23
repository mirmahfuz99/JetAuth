package com.jetauth

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.jetauth.ui.theme.JetAuthTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("CREATE", "onCreate is being executed")
        enableEdgeToEdge()
        setContent {
            JetAuthTheme {
                val navController = rememberNavController()
                JetAuthNavHost()
            }
        }
    }
}

