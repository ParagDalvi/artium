package com.example.artium

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.artium.call.CallActivity
import com.example.artium.notifications.CallNotificationData
import com.example.artium.notifications.NotificationHelper
import com.example.artium.ui.theme.ArtiumTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ArtiumTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Button(
        onClick = {
            NotificationHelper(context).showCallNotification(
                CallNotificationData(
                    "Incomfing call",
                    "Parag",
                    "Parag"
                )
            )
        }
    ) {
        Text("Show noti")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtiumTheme {
        Greeting("Android")
    }
}