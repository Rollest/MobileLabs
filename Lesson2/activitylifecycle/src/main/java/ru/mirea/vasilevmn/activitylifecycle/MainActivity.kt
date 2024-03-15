package ru.mirea.vasilevmn.activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("onCreate", "Instance Created")
    }

    override fun onStart() {
        super.onStart()

        Log.i("onStart", "Instance Started")
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)

        Log.i("onRestoreInstanceState", "Instance Restored")
    }

    override fun onRestart() {
        super.onRestart()

        Log.i("onRestart", "Instance Restarted")
    }

    override fun onResume() {
        super.onResume()

        Log.i("onResume", "Instance Resumed")
    }

    override fun onPause() {
        super.onPause()

        Log.i("onPause", "Instance Paused")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        Log.i("onSaveInstanceState", "Instance Saved")
    }

    override fun onStop() {
        super.onStop()

        Log.i("onStop", "Instance Stopped")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i("onDestroy", "Instance Destroyed")
    }
}