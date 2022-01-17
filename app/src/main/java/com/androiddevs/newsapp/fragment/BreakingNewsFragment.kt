package com.androiddevs.newsapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddevs.newsapp.MainActivity
import com.androiddevs.newsapp.adapter.NewsAdapter
import com.androiddevs.newsapp.databinding.FragmentBreakingNewsBinding
import com.androiddevs.newsapp.util.Resource
import com.androiddevs.newsapp.viewmodel.NewsViewModel

class BreakingNewsFragment : Fragment() {

    private var _binding : FragmentBreakingNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private val TAG = "BreakingNewsFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreakingNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        setUpRecyclerView()

        /**
         * Observe the data (news) changes
         * If it's successful, hide the progressBar and update the UI
         * But if it's still in Loading state, show progress Bar
         * Else , show the progress Bar with an error message
         */
        viewModel.news.observe(viewLifecycleOwner, { response ->
            when(response){
                is Resource.Success ->{
                    hideProgressBar()
                    response.responseData?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.responseMessage?.let { message ->
                        Toast.makeText(activity, "An error occurred $message",
                            Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
            }
        })

    }


    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    /**
     * Set the recycler view
     */
    private fun setUpRecyclerView(){
        newsAdapter = NewsAdapter()
        binding.breakingNewsRecyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}