package ru.mirea.vasilevmn.lesson6

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.mirea.vasilevmn.lesson6.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var button: Button
    lateinit var group: EditText
    lateinit var number: EditText
    lateinit var favFilm: EditText
    lateinit var sharedPref: SharedPreferences
    lateinit var editor: Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPref = getSharedPreferences("mirea_settings", MODE_PRIVATE)
        editor = sharedPref.edit()

        button = binding.button
        group = binding.editTextGroupNum
        number = binding.editTextListNum
        favFilm = binding.editTextFavMovie

        button.setOnClickListener {
            editor.putString("GROUP", group.text.toString())
            editor.putString("NUMBER", number.text.toString())
            editor.putString("FAV_FILM", favFilm.text.toString())
            editor.apply()
        }
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)

        group.setText(sharedPref.getString("GROUP", "unknown"))
        number.setText(sharedPref.getString("NUMBER", "unknown"))
        favFilm.setText(sharedPref.getString("FAV_FILM", "unknown"))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}