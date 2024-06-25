package com.jetauth.features.profile.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun DottedBorderImage(painter: Painter, contentDescription: String?, padding: Float = 8f) {
    Box(
        modifier = Modifier
            .drawBehind {
                val canvasWidth = size.width
                val canvasHeight = size.height
                val diameter = canvasWidth.coerceAtMost(canvasHeight)
                val radius = diameter / 2

                val paint = Paint().apply {
                    color = Color.Black
                    strokeWidth = 1.dp.toPx()
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    style = PaintingStyle.Stroke
                }

                drawIntoCanvas { canvas ->
                    canvas.drawCircle(
                        center = center,
                        radius = radius.toFloat(),
                        paint = paint,
                    )
                }
            }
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier
                .size(100.dp)
                .padding(padding.dp)
        )
    }
}

