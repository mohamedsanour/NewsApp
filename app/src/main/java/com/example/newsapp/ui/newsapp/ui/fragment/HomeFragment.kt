package com.example.newsapp.ui.newsapp.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.util.query
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.ui.newsapp.domain.model.Article
import com.example.newsapp.ui.newsapp.ui.adapter.NewsAdapter
import com.example.newsapp.ui.newsapp.domain.repository.Repository
import com.example.newsapp.ui.newsapp.ui.viewmodel.HomeViewModel
import com.example.newsapp.ui.newsapp.ui.viewmodel.MainViewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var newsAdapter: NewsAdapter
    val repository = Repository()
    private var searchJob: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        newsAdapter = NewsAdapter(emptyList(), requireContext())
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        //البحث بعد ثانيه
        binding.searchEditTxt.addTextChangedListener {
            searchJob?.cancel()
            searchJob = MainScope().launch {
                delay(1000)
                val query = it.toString().trim()
                if (query.isNotEmpty()) {
                    viewModel.searchForNews(query)
                } else {
                    viewModel.getBreakingNews()
                }
            }
        }
        //زرار الانتر اللي ف الكيبورد وزرار السيرش اللي ف كيبورد الموبايل
        binding.searchEditTxt.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.searchEditTxt.text.toString()
                if (query.isNotEmpty()) {
                    viewModel.searchForNews(query)
                }
                true
            } else false
        }


        binding.global.visibility = View.VISIBLE
        binding.rvNews.visibility = View.GONE
        viewModel.getBreakingNews()
        viewModel.myResponse2.observe(viewLifecycleOwner) { response ->
            response.let {
                binding.global.visibility = View.GONE
                binding.rvNews.visibility = View.VISIBLE
                newsAdapter = NewsAdapter(it.articles, requireContext())
                binding.rvNews.adapter = newsAdapter
                onClick()
            }
        }



        viewModel.searchResponse.observe(viewLifecycleOwner) { searchResponse ->
            searchResponse?.let {
                if (searchResponse.articles.isEmpty()) {
                    Toast.makeText(requireContext(), "No Articles Found", Toast.LENGTH_SHORT).show()
                    binding.rvNews.visibility = View.GONE
                } else {
                    newsAdapter = NewsAdapter(searchResponse.articles, requireContext())
                    binding.rvNews.visibility = View.VISIBLE
                    binding.rvNews.adapter = newsAdapter
                    onClick()
                }
            }
        }

    }

    private fun onClick() {
        newsAdapter.setCommunicator(object : NewsAdapter.Communicator {
            override fun onItemClick(article: Article) {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(article)
                findNavController().navigate(action)
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
