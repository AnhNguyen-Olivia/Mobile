package com.example.brat

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brat.ui.theme.BratTheme
import com.example.brat.R // Fixed R import

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BratTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Green
                ) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    // Fixed: Removed .mp3 extensions
    val mediaPlayer1 = remember {
        MediaPlayer.create(context, R.raw.brat)
    }
    val mediaPlayer2 = remember {
        MediaPlayer.create(context, R.raw.bagina)
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer1?.release()
            mediaPlayer2?.release()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "She copied. Oh, wow. And was a brat",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (mediaPlayer1?.isPlaying == true) {
                    mediaPlayer1.seekTo(0)
                }
                mediaPlayer1?.start()
            }
        ) {
            Text("365", fontSize = 15.sp)
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "You are not my mom, my mom doesn't have a",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (mediaPlayer2?.isPlaying == true) {
                    mediaPlayer2.seekTo(0)
                }
                mediaPlayer2?.start()
            }
        ) {
            Text("b-b-b-b-", fontSize = 15.sp)
        }
    }
}