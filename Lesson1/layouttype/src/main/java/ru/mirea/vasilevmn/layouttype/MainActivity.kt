package ru.mirea.vasilevmn.layouttype

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var text: TextView
    lateinit var button: Button
    lateinit var checkBox: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.main_text_view)
        button = findViewById(R.id.button13)
        checkBox = findViewById(R.id.checkBox2)

        text.setText("EDITED TEXT!!!!")
        button.setText("Set text to  the Button!!!!!!")
        checkBox.setChecked(true)
    }
}