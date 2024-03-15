package com.example.lesson5

import android.R
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson5.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
        val listSensor = binding.listView

        val arrayList = ArrayList<HashMap<String, Any>>()
        for (sensor in sensors) {
            val sensorTypeList = HashMap<String, Any>()
            sensorTypeList["Name"] = sensor.name
            sensorTypeList["Value"] = sensor.maximumRange
            arrayList.add(sensorTypeList)
        }

        val mHistory = SimpleAdapter(
            this,
            arrayList,
            R.layout.simple_list_item_2,
            arrayOf("Name", "Value"),
            intArrayOf(R.id.text1, R.id.text2)
        )
        listSensor.adapter = mHistory

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        var accelerometerSensor = sensorManager
            .getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(
            this, accelerometerSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.sensor.type === Sensor.TYPE_ACCELEROMETER) {
            val valueAzimuth = event!!.values[0]
            val valuePitch = event!!.values[1]
            val valueRoll = event!!.values[2]
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }
}
