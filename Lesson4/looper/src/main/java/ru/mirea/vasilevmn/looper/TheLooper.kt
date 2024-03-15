package ru.mirea.vasilevmn.looper

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log


class TheLooper(mainThreadHandler: Handler) : Thread() {
    var mHandler: Handler? = null
    private var mainHandler: Handler? = null
    fun MyLooper(mainThreadHandler: Handler?) {
        mainHandler = mainThreadHandler
    }

    override fun run(){
        Log.d("MyLooper",	"run");
        Looper.prepare()

        task()
        //example()

    }

    fun example(){
        mHandler = object : Handler(Looper.myLooper()!!) {
            override fun handleMessage(msg: Message) {
                val startTime = msg.data.getLong("START_TIME")
                val age = msg.data.getInt("AGE")
                val position = msg.data.getString("POSITION")!!
                Log.d("MyLooper get message:", "Возраст: $age.\nКем работаете: $position")

                val currentTime = System.currentTimeMillis()
                val delay = currentTime - startTime

                val message = Message()
                val bundle = Bundle()

                bundle.putString("result", "New age: $delay")
                message.data = bundle

                mainHandler?.sendMessage(message)
            }
        }
    }

    fun task(){
        mHandler = object : Handler(Looper.myLooper()!!) {
            override fun handleMessage(msg: Message) {
                val data = msg.data.getString("KEY")!!
                Log.d("MyLooper get message:", data)

                val count = data.length
                val message = Message()
                val bundle = Bundle()
                bundle.putString("result", "The number of letters in the word $data is $count")
                message.data = bundle
                // Send the message back to main thread message queue using main thread message Handler.
                mainHandler?.sendMessage(message)
            }
        }
    }
}