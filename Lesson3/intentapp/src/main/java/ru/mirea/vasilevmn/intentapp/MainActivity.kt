package ru.mirea.vasilevmn.intentapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateInMillis = System.currentTimeMillis()
        val format = "yyyy-MM-dd HH:mm:ss"
        val sdf = SimpleDateFormat(format)
        val dateString = sdf.format(Date(dateInMillis))


        val myIntent = Intent(
            this@MainActivity,
            SecondActivity::class.java
        )
        myIntent.putExtra("message", dateString)

        this.startActivity(myIntent)

    }
}