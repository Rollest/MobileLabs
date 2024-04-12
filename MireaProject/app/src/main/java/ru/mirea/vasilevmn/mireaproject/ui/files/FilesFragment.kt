package ru.mirea.vasilevmn.mireaproject.ui.files

import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import androidx.fragment.app.Fragment
import ru.mirea.vasilevmn.mireaproject.R

class FilesFragment : Fragment() {
    private val REQUEST_CODE_PERMISSION = 200
    private var isWork = false


    companion object {
        fun newInstance() = FilesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_files, container, false)
        requestStoragePermissions() // Запрашиваем разрешения перед выполнением операций с файлами
        return view
    }

    private fun requestStoragePermissions() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                REQUEST_CODE_PERMISSION
            )
        } else {
            // Разрешения уже предоставлены, выполняем доступ к файлам
            showFileProcessingDialog()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                // Все разрешения предоставлены, выполняем доступ к файлам
                showFileProcessingDialog()
            } else {
                // Разрешение не предоставлено, обрабатываем соответствующим образом
                showToast("Permission denied")
            }
        }
    }



    private fun showFileProcessingDialog() {
        val dialogView = layoutInflater.inflate(R.layout.fragment_files, null)
        val radioGroup: RadioGroup = dialogView.findViewById(R.id.radioGroup)

        AlertDialog.Builder(requireContext())
            .setTitle("File Processing")
            .setView(dialogView)
            .setPositiveButton("Convert") { dialog, _ ->
                val selectedRadioButtonId = radioGroup.checkedRadioButtonId
                val selectedRadioButton: RadioButton? = dialogView.findViewById(selectedRadioButtonId)
                val selectedFormat = selectedRadioButton?.text.toString()

                performFileConversion(selectedFormat, "smth.txt")

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun performFileConversion(selectedFormat: String, sourceFilePath: String) {
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)

        if (selectedFormat.lowercase() == "pdf") {
            val sourceFile = File(path, sourceFilePath)
            println(path.toString() + sourceFilePath)
            if (!sourceFile.exists()) {
                showToast("Source file does not exist.")
                return
            }

            val outputDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
            outputDir.mkdirs()
            val outputFile = File(outputDir, "converted_file.pdf")

            try {
                BufferedReader(FileReader(sourceFile)).use { reader ->
                    PdfWriter(outputFile).use { writer ->
                        val pdfDocument = PdfDocument(writer)
                        val document = Document(pdfDocument)
                        var line: String?
                        while (reader.readLine().also { line = it } != null) {
                            document.add(Paragraph(line))
                        }
                        document.close()
                        showToast("File conversion to PDF completed.")
                    }
                }
            } catch (e: Exception) {
                println(e)
                showToast("Error during file conversion: ${e.message}")
            }
        } else {
            showToast("Unsupported conversion format.")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun isExternalStorageAvailable(): Boolean =
        Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
}
