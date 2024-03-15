package ru.mirea.vasilevmn.buttonclicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var text: TextView
    lateinit var buttonWhoAmI: Button
    lateinit var buttonItsNotMe: Button
    lateinit var checkBox: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.tvOut)
        buttonWhoAmI = findViewById(R.id.button_who_am_i)
        buttonItsNotMe = findViewById(R.id.button_its_not_me)
        checkBox = findViewById(R.id.checkBox)

        buttonWhoAmI.setOnClickListener {
            text.setText("Мой номер по списку: 7")
        }

    }

    fun onItsNotMeClick(view: View) {
        Toast.makeText(this, "IT IS NOT ME!!!", Toast.LENGTH_LONG ).show()
        checkBox.setChecked(!checkBox.isChecked)
    }
}