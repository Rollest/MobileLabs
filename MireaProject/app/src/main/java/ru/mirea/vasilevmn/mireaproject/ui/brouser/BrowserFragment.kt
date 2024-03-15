package ru.mirea.vasilevmn.mireaproject.ui.brouser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.mirea.vasilevmn.mireaproject.databinding.FragmentBrowserBinding
import ru.mirea.vasilevmn.mireaproject.ui.home.HomeViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [BrowserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BrowserFragment : Fragment() {
    private var _binding: FragmentBrowserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val brouserViewModel =
            ViewModelProvider(this).get(BrouserViewModel::class.java)
        _binding = FragmentBrowserBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textBrouser
        brouserViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}