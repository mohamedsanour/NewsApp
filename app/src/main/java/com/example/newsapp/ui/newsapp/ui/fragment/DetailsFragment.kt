package com.example.newsapp.ui.newsapp.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentDetailsBinding
import com.example.newsapp.ui.newsapp.domain.model.Article
import com.example.newsapp.ui.newsapp.ui.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: DetailsFragmentArgs by navArgs()
    private val articleViewModel: UserViewModel by viewModels()
    private var isSaved = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detail = args.article



        binding.webView.apply {
            webViewClient = WebViewClient()
            val safeUrl = detail.url.toString()
            loadUrl(safeUrl)
        }

        articleViewModel.isArticleSaved(detail) { saved ->
            isSaved = saved
            requireActivity().runOnUiThread { updateFabColor() }
        }

        // 2) فلما نضغط نقلب الحالة على طول
        binding.fab.setOnClickListener {
            toggleBookmark(detail)
        }
    }

    private fun toggleBookmark(article: Article) {

        // قلبنا الحالة على طول
        isSaved = !isSaved

        if (isSaved) {
            articleViewModel.addArticle(article)
            Snackbar.make(binding.root, "Saved to Bookmark", Snackbar.LENGTH_SHORT).show()
        } else {
            articleViewModel.delete(article)
            Snackbar.make(binding.root, "Removed from Bookmark", Snackbar.LENGTH_SHORT).show()
        }

        updateFabColor()
    }

    private fun updateFabColor() {
        if (isSaved) {
            binding.fab.setColorFilter(
                ContextCompat.getColor(
                    requireContext(), R.color.green
                )
            )
        } else {
            binding.fab.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorPrimary
                )
            )
        }
    }
}


