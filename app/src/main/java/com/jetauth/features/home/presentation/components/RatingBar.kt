package com.jetauth.features.home.presentation.components
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon

@Composable
fun RatingBar(rating: Double, size: Float = 16f) {
    val realNumber = rating.toInt()
    val partNumber = ((rating - realNumber) * 10).toInt()

    Row(
        modifier = Modifier.wrapContentWidth(),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        for (i in 0 until 5) {
            when {
                i < realNumber -> {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = Color(0xFF7455F7),
                        modifier = Modifier.size(size.dp)
                    )
                }
                i == realNumber -> {
                    Box(
                        modifier = Modifier
                            .size(size.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                else -> {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(size.dp)
                    )
                }
            }
        }
    }
}


