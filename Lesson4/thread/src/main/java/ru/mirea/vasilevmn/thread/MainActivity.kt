package ru.mirea.vasilevmn.thread

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.vasilevmn.thread.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Thread {
            var res: Long = 0
            for (i in 0..10000000000){
                res+=i
            }
            runOnUiThread { binding.textView.text = res.toString() }
        }.start()
    }
}