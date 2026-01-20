package com.example.newsapp.ui.newsapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.ViewholderBookMarkBinding
import com.example.newsapp.ui.newsapp.domain.model.Article

class BookMarkAdapter(private var article: MutableList<Article>, val context: Context) :
    RecyclerView.Adapter<BookMarkAdapter.ViewHolder>() {
    private var communicator: Communicator? = null



    inner class ViewHolder(val binding: ViewholderBookMarkBinding) :
        RecyclerView.ViewHolder(binding.root)


    interface Communicator {
        fun onItemClick(article: Article)
    }

    fun setCommunicator(communicator: Communicator) {
        this.communicator = communicator
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ViewholderBookMarkBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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

            root.setOnClickListener {
                communicator?.onItemClick(item)
            }
        }

    }

    override fun getItemCount(): Int {
        return article.size
    }
    fun setData(newList: MutableList<Article>){
        article = newList
        notifyDataSetChanged()
    }
    fun removeItem(pos : Int){
        article.removeAt(pos)
        notifyItemRemoved(pos)
    }
    fun getItem(position: Int): Article = article[position]
}