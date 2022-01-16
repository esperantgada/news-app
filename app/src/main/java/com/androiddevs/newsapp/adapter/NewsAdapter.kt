package com.androiddevs.newsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.newsapp.R
import com.androiddevs.newsapp.model.Article
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder> (){

    class NewsViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
        val inflated = LayoutInflater.from(parent.context).inflate(R.layout.item_article_preview,
        parent, false)

        return NewsViewHolder(inflated)
    }

    override fun onBindViewHolder(holder: NewsAdapter.NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(article_image)
            source_text_view.text = article.source.name
            title.text = article.title
            description_text_view.text = article.description
            publishedAt_text_view.text = article.publishedAt

           //Apply clickListener on an Item
            setOnClickListener {
                onItemClickedListener?.let { it(article) }
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    companion object{
        val DiffCallback = object : DiffUtil.ItemCallback<Article>(){
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }

    val differ = AsyncListDiffer(this, DiffCallback)

    /**
     * Set clickListener on Item
     */
    private var onItemClickedListener : ((Article) -> Unit)? = null

    fun setOnItemClickedListener(listener : (Article) -> Unit){
        onItemClickedListener = listener
    }
}