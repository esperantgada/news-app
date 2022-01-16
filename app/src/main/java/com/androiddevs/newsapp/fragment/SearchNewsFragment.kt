package com.androiddevs.newsapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.androiddevs.newsapp.MainActivity
import com.androiddevs.newsapp.databinding.FragmentSearchNewsBinding
import com.androiddevs.newsapp.viewmodel.NewsViewModel

class SearchNewsFragment : Fragment() {

    private var _binding : FragmentSearchNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viwModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Instantiate viewModel
        viwModel = (activity as MainActivity).viewModel
    }


    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}