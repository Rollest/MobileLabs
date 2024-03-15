package ru.mirea.vasilevmn.dialog

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar


class MainActivity : AppCompatActivity() {

    lateinit var root: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        root = findViewById(R.id.root)
    }

    fun onClickShowDialog(view: View) {
        val dialogFragment = AlertDialogFragment()
        dialogFragment.show(supportFragmentManager, "mirea")
    }

    fun onOkClicked() {
        Toast.makeText(
            applicationContext, "Вы выбрали кнопку \"Иду дальше\"!",
            Toast.LENGTH_LONG
        ).show()
    }

    fun onCancelClicked() {
        Toast.makeText(
            applicationContext, "Вы выбрали кнопку \"Нет\"!",
            Toast.LENGTH_LONG
        ).show()
    }

    fun onNeutralClicked() {
        Toast.makeText(
            applicationContext, "Вы выбрали кнопку \"На паузе\"!",
            Toast.LENGTH_LONG
        ).show()
    }

    fun onClickShowTimeDialog(view: View) {
        val cal: Calendar = Calendar.getInstance()
        var mHour = cal.get(Calendar.HOUR_OF_DAY)
        var mMinute = cal.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(this,
            { view, hourOfDay, minute ->
                val editTextTimeParam = "$hourOfDay : $minute"
                onSNACK(root, editTextTimeParam)
                println("TIMEDIALOG")
            }, mHour, mMinute, false
        )
        timePickerDialog.show()
    }
    fun onClickShowDateDialog(view: View) {
        val cal = Calendar.getInstance()
        var mYear = cal[Calendar.YEAR]
        var mMonth = cal[Calendar.MONTH]
        var mDay = cal[Calendar.DAY_OF_MONTH]

        // инициализируем диалог выбора даты текущими значениями
        val datePickerDialog = DatePickerDialog(this,
            { view, year, monthOfYear, dayOfMonth ->
                val editTextDateParam = dayOfMonth.toString() + "." + (monthOfYear + 1) + "." + year
                onSNACK(root, editTextDateParam)
            }, mYear, mMonth, mDay
        )
        datePickerDialog.show()
    }
    fun onClickShowProgressDialog(view: View) {
        val pd = ProgressDialog(this@MainActivity)
        pd.setMessage("loading")
        pd.show()

        val onShowProgressDialogText: String = "ProgressDialog is shown"
        onSNACK(root, onShowProgressDialogText)
    }


    private fun onSNACK(view: View, text: String) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()
    }
}