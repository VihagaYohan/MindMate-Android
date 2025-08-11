package com.codenova.mindmate.ui.view.mood

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import com.codenova.mindmate.BuildConfig
import androidx.compose.ui.unit.dp
import com.codenova.mindmate.ui.theme.MindMateTheme

@Composable
fun MoodPage() {
    val infiniteAnimation = rememberInfiniteTransition(label = "infinite animation")
    infiniteAnimation.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "morph"
    )

    var sliderPosition by remember {
        mutableStateOf(0f)
    }
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            FaceCanvas(mood = sliderPosition)

            Spacer(Modifier.height(20.dp))

            Slider(
                value = sliderPosition,
                onValueChange = {
                    sliderPosition = it
                },
                steps = 1,
                valueRange = 0f..3f
            )

            Text(text = sliderPosition.toString())
        }
    }
}

@Composable
fun FaceCanvas(mood: Float) {
    // Animate mood curve (controls smile or frown)
    val controlYOffset by animateFloatAsState(
        targetValue = when (mood) {
            0f -> 80f
            1.5f -> 0f
            3f -> -80f
            else -> {}
        } as Float,
        label = "MouthAnimation"
    )
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(16.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val eyeRadius = 40f

        // Eyes positions
        val leftEye = Offset(canvasWidth / 3, canvasHeight / 3)
        val rightEye = Offset(2 * canvasWidth / 3, canvasHeight / 3)

        // Draw eyes
        drawCircle(Color.Black, eyeRadius, center = leftEye)
        drawCircle(Color.Black, eyeRadius, center = rightEye)

        // Mouth position
        val mouthStart = Offset(x = canvasWidth / 3, y = 2 * canvasHeight / 3)
        val mouthEnd = Offset(x = 2 * canvasWidth / 3, y = 2 * canvasHeight / 3)


        // Control point for Bezier curve (midpoint horizontally)
        val mouthControl = Offset(
            x = (mouthStart.x + mouthEnd.x) / 2,
            y = mouthStart.y + controlYOffset
        )

        // Draw mouth with a quadratic Bezier curve
        val path = Path().apply {
            moveTo(mouthStart.x, mouthStart.y)
            quadraticBezierTo(
                mouthControl.x,
                mouthControl.y,
                mouthEnd.x,
                mouthEnd.y
            )
        }

        drawPath(
            path = path,
            color = Color.Black,
            style = Stroke(width = 20f, cap = StrokeCap.Round)
        )
    }
}

@Composable
fun drawSmilyFace() {
    val textMeasurer = rememberTextMeasurer()
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val radius = 80f

        val leftEyeCenter = Offset(
            x = canvasWidth / 3,
            y = canvasHeight / 2
        )
        val rightEyeCenter = Offset(
            x = 2 * canvasWidth / 3,
            y = canvasHeight / 2
        )

        val lineStart = ((canvasWidth / 3) + (radius / 2))
        val lineEnd = ((2 * canvasWidth / 3) - (radius / 2))
        val lineYPos = (canvasHeight / 2) + (radius * 3)

        val path = Path()
        path.moveTo(x = lineStart, y = lineYPos)
        path.lineTo(x = lineEnd, y = lineYPos)
        path.close()

        drawCircle(
            color = Color.Black,
            radius = radius,
            center = leftEyeCenter
        )
        drawCircle(
            color = Color.Black,
            radius = radius,
            center = rightEyeCenter
        )

        drawPath(
            path = path,
            color = Color.Black,
            style = Stroke(
                width = 50f,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
        drawText(textMeasurer, "${size.width / 2}")
    }
}

@Composable
@Preview(showBackground = true)
fun JournalPagePreview() {
    MindMateTheme {
        MoodPage()
    }
}