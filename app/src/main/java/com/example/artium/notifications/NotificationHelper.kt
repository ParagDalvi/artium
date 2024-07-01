package com.example.artium.notifications

import android.Manifest
import android.app.Notification
import android.app.PendingIntent
import android.app.Person
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Icon
import android.media.RingtoneManager
import android.os.Build
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.artium.R
import com.example.artium.call.CallActivity


class NotificationHelper(private val context: Context) {
    private val notificationManager = NotificationManagerCompat.from(context)

    private val callChannel = NotificationChannelCompat.Builder(
        INCOMING_CALL_CHANNEL_ID,
        NotificationManagerCompat.IMPORTANCE_HIGH
    )
        .setName(CALL_CHANNEL_NAME)
        .build()

    init {
        notificationManager.createNotificationChannel(callChannel)
    }

    companion object {
        const val CALL_REQUEST_ID = 110
    }

    fun showCallNotification(data: CallNotificationData) {
        val channelId = INCOMING_CALL_CHANNEL_ID
        val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            getCallStyleNotification(data, channelId)
        } else {
            getCompatNotification(data, channelId)
        }
        checkAndShowNotification(notification, CALL_NOTIFICATION_ID)
    }

    fun dismissNotification(id: Int) {
        notificationManager.cancel(id)
    }

    private fun checkAndShowNotification(notification: Notification, id: Int) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            notificationManager.notify(id, notification)
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun getCallStyleNotification(
        data: CallNotificationData,
        channelId: String
    ): Notification {
        val icon = Icon.createWithResource(context, R.drawable.ic_launcher_foreground)
        val caller = Person.Builder()
            .setIcon(icon)
            .setName(data.name)
            .setImportant(true)
            .build()

        val remoteView = RemoteViews(context.packageName, R.layout.call_notification)

        val intent = callAcceptIntent(CALL_NOTIFICATION_ID)

        val callStyle = Notification.CallStyle.forIncomingCall(caller, intent, intent)
        return Notification.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(intent)
            .setStyle(callStyle)
            .setFullScreenIntent(intent, true)
            .setOngoing(true)
            .setColorized(true)
            .setCategory(Notification.CATEGORY_CALL)
            .setCustomContentView(remoteView)
            .build()
    }

    private fun getCompatNotification(data: CallNotificationData, channelId: String): Notification {
        val ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSound(ringtone)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(context.getString(R.string.incoming_call))
            .setContentText(data.name)
            .setOngoing(true)

        val intent = callAcceptIntent(CALL_NOTIFICATION_ID)

        val action = NotificationCompat.Action.Builder(
            IconCompat.createWithResource(context, R.drawable.ic_launcher_background),
            context.getString(R.string.accept_call),
            intent
        ).build()

        notificationBuilder.addAction(action)

        return notificationBuilder.build()
    }

    private fun callAcceptIntent(notificationId: Int): PendingIntent {
        val intent = Intent(context, CallActivity::class.java)
        intent.putExtra(CALL_NOTIFICATION_ID_KEY, notificationId)
        return PendingIntent.getActivity(
            context,
            CALL_REQUEST_ID,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }
}