package com.androiddevs.newsapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.androiddevs.newsapp.MainActivity
import com.androiddevs.newsapp.databinding.FragmentArticleBinding
import com.androiddevs.newsapp.viewmodel.NewsViewModel

class ArticleFragment : Fragment() {

    private var _binding : FragmentArticleBinding?= null
    private val binding get() = _binding!!
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }


    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}