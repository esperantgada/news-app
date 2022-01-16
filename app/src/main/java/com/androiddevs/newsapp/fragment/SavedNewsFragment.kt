package com.androiddevs.newsapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.androiddevs.newsapp.MainActivity
import com.androiddevs.newsapp.databinding.FragmentSavedNewsBinding
import com.androiddevs.newsapp.viewmodel.NewsViewModel

class SavedNewsFragment : Fragment() {

    private var _binding : FragmentSavedNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSavedNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}