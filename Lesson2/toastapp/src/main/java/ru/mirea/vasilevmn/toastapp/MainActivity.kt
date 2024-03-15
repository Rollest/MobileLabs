package ru.mirea.vasilevmn.toastapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var input: EditText
    lateinit var inputBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        input = findViewById(R.id.input)
        inputBtn = findViewById(R.id.button)

        inputBtn.setOnClickListener {
            val len = input.text.length
            val toast = Toast.makeText(
                applicationContext,
                "СТУДЕНТ №7??? ГРУППА БСБО-11-21 Количество символов - $len",
                Toast.LENGTH_SHORT
            )
            toast.show()
        }



    }
}