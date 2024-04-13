package ru.mirea.vasilevmn.mireaproject.ui.files

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import ru.mirea.vasilevmn.mireaproject.R
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.io.IOException
import java.io.OutputStreamWriter


class FilesFragment : Fragment() {
    private val REQUEST_CODE_PERMISSION = 200
    private var isWork = false
    private val viewModel: FilesViewModel by activityViewModels()
    lateinit var path: String

    companion object {
        fun newInstance() = FilesFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_files, container, false)

        viewModel.path.observe(viewLifecycleOwner){
            path = "/$it"
        }

        showFileProcessingDialog()
        return view
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

                performFileConversion(selectedFormat, path)

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
            val outputFile = File(outputDir, sourceFilePath.replace("txt","pdf"))

            try {
                BufferedReader(FileReader(sourceFile)).use { reader ->
                    PdfWriter(outputFile).use { writer ->
                        val pdfDocument = PdfDocument(writer)
                        val document = Document(pdfDocument)
                        var line: String?
                        while (reader.readLine().also { line = it } != null) {
                            println(line)
                            println(Paragraph(line))
                            document.add(Paragraph(line))
                        }
                        // Check if the document has pages before closing
                        if (pdfDocument.defaultPageSize != null) {
                            document.close()
                            showToast("File conversion to PDF completed.")
                        } else {
                            showToast("Document is empty. Unable to convert to PDF.")
                        }
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

}