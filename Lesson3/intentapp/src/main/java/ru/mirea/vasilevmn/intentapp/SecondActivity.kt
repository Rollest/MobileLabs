package ru.mirea.vasilevmn.intentapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Math.pow


class SecondActivity : AppCompatActivity() {

    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        textView = findViewById(R.id.textView)

        val intent = intent
        val string = intent.getStringExtra("message")

        textView.text = "Квадрат значения моего номера по списку в группе стоставляет число ${pow(7.0,2.0).toInt()}," +
                " а текущее время $string"

    }
}