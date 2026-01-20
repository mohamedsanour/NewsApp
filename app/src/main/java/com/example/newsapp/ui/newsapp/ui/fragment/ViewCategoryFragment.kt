package com.example.newsapp.ui.newsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.FragmentViewCateggoryBinding
import com.example.newsapp.ui.newsapp.domain.model.Article
import com.example.newsapp.ui.newsapp.domain.repository.Repository
import com.example.newsapp.ui.newsapp.ui.adapter.NewsAdapter
import com.example.newsapp.ui.newsapp.ui.adapter.ViewCategoryAdapter
import com.example.newsapp.ui.newsapp.ui.viewmodel.CategoryViewModel
import com.example.newsapp.ui.newsapp.ui.viewmodel.MainViewModelFactory


class ViewCategoryFragment : Fragment() {
    private lateinit var binding: FragmentViewCateggoryBinding
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var viewCategoryAdapter: ViewCategoryAdapter
    private val args: ViewCategoryFragmentArgs by navArgs()
    val repository = Repository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewCateggoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModelFactory = MainViewModelFactory(repository)
        categoryViewModel = ViewModelProvider(this, viewModelFactory)[CategoryViewModel::class.java]

        val action = args.id
        categoryViewModel.getNews(action)

        binding.rvViewCategory.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        categoryViewModel.myResponse.observe(viewLifecycleOwner) { response ->
            response.let {
                viewCategoryAdapter = ViewCategoryAdapter(response.articles, requireContext())
                val adapter = viewCategoryAdapter
                binding.rvViewCategory.adapter = adapter
                onClick()
            }

        }
    }

    private fun onClick() {
        viewCategoryAdapter.setCommunicator(object : ViewCategoryAdapter.Communicator {
            override fun onItemClick(article: Article) {
                val action =
                    ViewCategoryFragmentDirections.actionViewCateggoryFragmentToDetailsFragment(
                        article
                    )
                findNavController().navigate(action)
            }

        })
    }
}
