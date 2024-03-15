package ru.mirea.vasilevmn.data_thread

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.vasilevmn.data_thread.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val runn1 = Runnable { binding.tvInfo.text = "runn1" }
        val runn2 = Runnable { binding.tvInfo.text = "runn2" }
        val runn3 = Runnable { binding.tvInfo.text = "runn3" }
        val t = Thread {
            try {
                TimeUnit.SECONDS.sleep(2)
                runOnUiThread(runn1)
                TimeUnit.SECONDS.sleep(1)
                binding.tvInfo.postDelayed(runn3, 2000)
                binding.tvInfo.post(runn2)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        t.start()
    }
}