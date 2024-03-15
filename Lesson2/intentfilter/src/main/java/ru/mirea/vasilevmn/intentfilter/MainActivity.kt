package ru.mirea.vasilevmn.intentfilter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var button: Button
    lateinit var buttonSecond: Button
    var uriMIREA = "https://www.mirea.ru/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        buttonSecond = findViewById(R.id.button_second)

        button.setOnClickListener {
            openLink(uriMIREA)
        }

        buttonSecond.setOnClickListener {
            transferFIO()
        }

    }

    fun openLink(uri: String){
        val address = Uri.parse(uri)
        val openLinkIntent = Intent(Intent.ACTION_VIEW, address)
        startActivity(openLinkIntent)
    }

    fun transferFIO(){
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "MIREA")
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Васильев Максим Николаевич")
        startActivity(Intent.createChooser(shareIntent, "МОИ ФИО"))
    }
}