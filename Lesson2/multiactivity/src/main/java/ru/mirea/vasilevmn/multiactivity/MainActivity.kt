package ru.mirea.vasilevmn.multiactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var input: EditText
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.inputText)
        button = findViewById(R.id.button)
    }

    fun onClickNewActivity(view: View) {
        val intent = Intent(
            this@MainActivity,
            SecondActivity::class.java
        )
        intent.putExtra("key", input.text.toString())
        startActivity(intent)
    }
}