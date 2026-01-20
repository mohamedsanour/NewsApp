package com.example.newsapp.ui.newsapp.ui.fragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentBookMarkBinding
import com.example.newsapp.ui.newsapp.domain.model.Article
import com.example.newsapp.ui.newsapp.ui.adapter.BookMarkAdapter
import com.example.newsapp.ui.newsapp.ui.adapter.NewsAdapter
import com.example.newsapp.ui.newsapp.ui.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar
import kotlin.getValue

class BookMarkFragment : Fragment() {

    private lateinit var binding: FragmentBookMarkBinding
    private val mUserViewModel: UserViewModel by viewModels()
    private lateinit var bookmarkAdapter: BookMarkAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookMarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookmarkAdapter = BookMarkAdapter(mutableListOf(), requireContext())
        binding.rvBookMark.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        binding.rvBookMark.adapter = bookmarkAdapter
        onClick()
        mUserViewModel.readAllData.observe(viewLifecycleOwner) { articles ->

            if (articles.isNullOrEmpty()) {
                binding.rvBookMark.visibility = View.GONE
                binding.notFound.visibility = View.VISIBLE
            } else {
                binding.rvBookMark.visibility = View.VISIBLE
                binding.notFound.visibility = View.GONE
                bookmarkAdapter.setData(articles.toMutableList())
            }


        }

        val itemTouchHelper = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(
                viewHolder: RecyclerView.ViewHolder,
                direction: Int
            ) {
                val pos = viewHolder.adapterPosition
                val article = bookmarkAdapter.getItem(pos)
                mUserViewModel.delete(article)
                bookmarkAdapter.removeItem(pos)

                Snackbar.make(binding.root, "Article Removed", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        mUserViewModel.addArticle(article)
                    }
                    show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rvBookMark)
    }

    private fun onClick() {
        bookmarkAdapter.setCommunicator(object : BookMarkAdapter.Communicator {
            override fun onItemClick(article: Article) {
                val action =
                    BookMarkFragmentDirections.actionBookMarkFragmentToDetailsFragment(article)
                findNavController().navigate(action)
            }

        })
    }
}
