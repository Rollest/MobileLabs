package ru.mirea.vasilevmn.looper

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.vasilevmn.looper.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //example()
        task()
    }

    private fun example(){
        val mainThreadHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                Log.d(TAG, "Task executed. This is result: ${msg.data.getString("result")}")
            }
        }

        val myLopper = TheLooper(mainThreadHandler)
        myLopper.start()

        binding.TextViewMirea.text = "Мой номер по списку № 7"
        binding.buttonMirea.setOnClickListener {
            val msg = Message.obtain()
            val bundle = Bundle()
            bundle.putString("KEY", "mirea")
            msg.data = bundle
            myLopper.mHandler?.sendMessage(msg)
        }
    }

    private fun task(){
        val mainThreadHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                Log.d(TAG, "Task executed. This is result: ${msg.data.getString("result")}")
            }
        }

        val theLooper = TheLooper(mainThreadHandler)
        theLooper.start()

        binding.buttonMirea.setOnClickListener {
            val startTime = System.currentTimeMillis()
            val ageText = binding.editTextAge.text.toString().toIntOrNull() ?: 0
            val jobText = binding.editTextJob.text.toString()

            val message = Message.obtain()
            val bundle = Bundle()

            bundle.putLong("Start", startTime)
            bundle.putInt("Age", ageText)
            bundle.putString("Job", jobText)

            message.data = bundle

            theLooper.mHandler?.sendMessage(message)
        }
    }
}