package com.example.newsapp.ui.newsapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.ViewholderNewsBinding
import com.example.newsapp.ui.newsapp.domain.model.Article

class NewsAdapter(private var article: List<Article>, val context: Context) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private var communicator: Communicator? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ViewholderNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = article[position]
        holder.binding.apply {
            Glide.with(context)
                .load(item.urlToImage)
                .into(newsImg)
            typeTxt.text = item.source?.name
            descriptionTxt.text = item.description
            timeTxt.text = item.publishedAt
        }
        holder.itemView.setOnClickListener {
            communicator?.onItemClick(item)
        }

    }

    override fun getItemCount(): Int {
        return article.size
    }

    inner class ViewHolder(val binding: ViewholderNewsBinding) :
        RecyclerView.ViewHolder(binding.root)


    interface Communicator {
        fun onItemClick(article: Article)
    }
    fun setCommunicator(communicator: Communicator){
        this.communicator = communicator
    }
}