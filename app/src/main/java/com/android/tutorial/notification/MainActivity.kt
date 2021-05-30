package com.android.tutorial.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notificationManager = NotificationManagerCompat.from(this)

        val channel = NotificationChannelCompat
            .Builder("1111", NotificationManagerCompat.IMPORTANCE_HIGH)
            .setDescription("This is for normal notification")
            .setName("Normal Notification")
            .build()

        notificationManager.createNotificationChannel(channel)

        btnSimple.setOnClickListener {
            val notification = NotificationCompat.Builder(this, "1111")
                .setSmallIcon(R.drawable.ic_noti)
                .setContentTitle("Simple Noti")
                .setContentText("This is notification test")
                .build()

            notificationManager.notify(1, notification)
        }

        btnLongText.setOnClickListener {
            val notification = NotificationCompat.Builder(this, "1111")
                .setSmallIcon(R.drawable.ic_noti)
                .setContentTitle("Long Noti")
                .setContentText("This is long noti test")
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText("Gotham City. Crime boss Carl Grissom (Jack Palance) effectively runs the town but there's a new crime fighter in town - Batman (Michael Keaton). Grissom's right-hand man is Jack Napier (Jack Nicholson), a brutal man who is not entirely sane... After falling out between the two Grissom has Napier set up with the Police and Napier falls to his apparent death in a vat of chemicals. However, he soon reappears as The Joker and starts a reign of terror in Gotham City. Meanwhile, reporter Vicki Vale (Kim Basinger) is in the city to do an article on Batman. She soon starts a relationship with Batman's everyday persona, billionaire Bruce Wayne.")
                        .setSummaryText("This is summary text")
                )
                .build()

            notificationManager.notify(2, notification)
        }

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            Log.d("FirebaseToken", "token....." + task.result ?: "Fail")
        }
    }
}