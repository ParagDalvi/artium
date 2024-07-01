package com.example.artium.call

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.artium.notifications.CALL_NOTIFICATION_ID_KEY
import com.example.artium.notifications.NotificationHelper
import com.example.artium.ui.theme.ArtiumTheme

class CallActivity : AppCompatActivity() {

    private lateinit var notificationHelper: NotificationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notificationHelper = NotificationHelper(this)
        dismissNotification()
        setContent {
            ArtiumTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Text("Call activity")
                }
            }
        }
    }

    private fun dismissNotification() {
        val id = intent.getIntExtra(CALL_NOTIFICATION_ID_KEY, -1)
        notificationHelper.dismissNotification(id)
    }
}