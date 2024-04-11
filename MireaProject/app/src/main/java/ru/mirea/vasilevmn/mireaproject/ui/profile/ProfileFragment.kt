package ru.mirea.vasilevmn.mireaproject.ui.profile

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ru.mirea.vasilevmn.mireaproject.R
import java.io.FileOutputStream

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val RegLogin: EditText = view.findViewById(R.id.editTextRegLogin)
        val RegPassword: EditText = view.findViewById(R.id.editTextRegPassword)
        val LoginLogin: EditText = view.findViewById(R.id.editTextLoginLogin)
        val LoginPassword: EditText = view.findViewById(R.id.editTextLoginLogin)
        val buttonReg: Button = view.findViewById(R.id.buttonRegistration)
        val buttonLogin: Button = view.findViewById(R.id.buttonLogin)

        buttonReg.setOnClickListener {
            val login = RegLogin.text.toString()
            val password = RegPassword.text.toString()
            try {
                val outputStream = requireActivity().openFileOutput("password.txt", Context.MODE_PRIVATE)
                outputStream.write(password.toByteArray())
                outputStream.close()
                val outputStream2 = requireActivity().openFileOutput("login.txt", Context.MODE_PRIVATE)
                outputStream2.write(login.toByteArray())
                outputStream2.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        buttonLogin.setOnClickListener {
            val login = LoginLogin.text.toString()
            val password = LoginPassword.text.toString()
            var isCorrect = true
            try {
                val inputStream = requireActivity().openFileInput("password.txt")
                val savedPassword = inputStream.bufferedReader().use { it.readText() }
                inputStream.close()
                println(savedPassword)
                if(savedPassword != password){
                    isCorrect = false
                }
                val inputStream2 = requireActivity().openFileInput("login.txt")
                val savedLogin = inputStream2.bufferedReader().use { it.readText() }
                inputStream2.close()
                println(savedLogin)
                if(savedLogin != login){
                    isCorrect = false
                }
                val text = if(isCorrect) "Вы вошли!" else "Вы НЕ вошли :("
                Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return view
    }
}
