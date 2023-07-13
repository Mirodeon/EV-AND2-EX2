package com.mirodeon.eval_and2_exo2.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.mirodeon.eval_and2_exo2.databinding.ArticleCellBinding

class ArticleAdapter(
    private val data: List<Article>,
    private val clickListener: OnItemClickListener,
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(article: Article)
    }

    private lateinit var binding: ArticleCellBinding

    class ArticleViewHolder(var viewBinding: ArticleCellBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(article: Article, clickListener: OnItemClickListener) {
            val label = viewBinding.txtLabelArticle
            val content = viewBinding.txtContentArticle
            val img = viewBinding.imageArticleCell

            label.text = article.label
            label.setTextColor(getColor(itemView.context, article.type.colorId))

            "Quantité: ${article.count}".also { content.text = it }

            img.setImageResource(article.type.imgId)

            itemView.setOnClickListener {
                clickListener.onItemClicked(article)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        binding = ArticleCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(data[position], clickListener)
    }
}