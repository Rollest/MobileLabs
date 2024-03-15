package ru.mirea.vasilevmn.lesson4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.mirea.vasilevmn.lesson4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewSongName.text = "binding text set"
    }
}