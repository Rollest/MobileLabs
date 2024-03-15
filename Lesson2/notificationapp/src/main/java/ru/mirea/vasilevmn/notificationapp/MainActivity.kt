package ru.mirea.vasilevmn.notificationapp

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    private val PermissionCode = 200
    private val CHANNEL_ID = "com.mirea.asd.notification.ANDROID"
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.d(MainActivity::class.java.simpleName.toString(), "Разрешения получены")
        } else {
            Log.d(MainActivity::class.java.simpleName.toString(), "Нет разрешений!")
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(Manifest.permission.POST_NOTIFICATIONS),
                PermissionCode
            )
        }
    }




    fun onClickNewMessageNotification(view: View) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        val builder: NotificationCompat.Builder  = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentText("Congratulation!")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Much longer text that cannot fit one line..."))
                    .setContentTitle("Mirea")

        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, "Student FIO Notification", importance)
        channel.description = "MIREA Channel"
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(1, builder.build())
    }
}