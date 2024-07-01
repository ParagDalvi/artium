package com.example.artium.fcm

import android.util.Log
import com.example.artium.notifications.CallNotificationData
import com.example.artium.notifications.FCM_NOTIFICATION_DESCRIPTION
import com.example.artium.notifications.FCM_NOTIFICATION_NAME
import com.example.artium.notifications.FCM_NOTIFICATION_TITLE
import com.example.artium.notifications.FCM_NOTIFICATION_TYPE_CALL
import com.example.artium.notifications.FCM_NOTIFICATION_TYPE_KEY
import com.example.artium.notifications.NotificationHelper
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMService : FirebaseMessagingService() {
    private lateinit var notificationHelper: NotificationHelper

    companion object {
        private const val TAG = "FCMService"
    }

    override fun onCreate() {
        notificationHelper = NotificationHelper(this)
        super.onCreate()
    }

    override fun onMessageReceived(message: RemoteMessage) {

        val type = message.data[FCM_NOTIFICATION_TYPE_KEY]
        val title = message.data[FCM_NOTIFICATION_TITLE]
        val description = message.data[FCM_NOTIFICATION_DESCRIPTION]
        val name = message.data[FCM_NOTIFICATION_NAME]

        Log.e(TAG, "onMessageReceived: 1")

        if (type == FCM_NOTIFICATION_TYPE_CALL) {
            Log.e(TAG, "onMessageReceived: 2")
            val data = CallNotificationData(title, description, name)
            notificationHelper.showCallNotification(data)
        }
    }

    override fun onNewToken(p0: String) {
        // update token on server
        Log.i(TAG, "onNewToken: Change in FCM token")
        super.onNewToken(p0)
    }
}