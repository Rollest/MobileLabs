package ru.mirea.vasilevmn.multiactivity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import ru.mirea.vasilevmn.multiactivity.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySecondBinding
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        textView = findViewById(R.id.textView)
        val text = intent.getSerializableExtra("key") as String?
        textView.text = text
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_second)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}