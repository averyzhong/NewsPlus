package com.avery.newsplus.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.avery.newsplus.R
import com.avery.newsplus.api.model.NewsMetaItem
import com.avery.newsplus.extentions.application
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_news_meta.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * 新闻列表数据适配器
 *
 * @author Avery
 */

class NewsMetaAdapter(
    private val onItemClickListener: ((NewsMetaItem) -> Unit)?
) : PagedListAdapter<NewsMetaItem, NewsMetaAdapter.ViewHolder>(DIFF_CALLBACK) {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        return ViewHolder(
            layoutInflater!!.inflate(
                R.layout.item_news_meta,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.onBind(getItem(position)!!).also {
            holder.containerView.setOnClickListener {
                onItemClickListener?.let {
                    onItemClickListener.invoke(getItem(position)!!)
                }
            }
        }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsMetaItem>() {
            override fun areItemsTheSame(oldItem: NewsMetaItem, newItem: NewsMetaItem) =
                oldItem.aid == newItem.aid

            override fun areContentsTheSame(oldItem: NewsMetaItem, newItem: NewsMetaItem) =
                oldItem.aid == newItem.aid
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
        this.recyclerView.addOnScrollListener(scrollListener)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        recyclerView.removeOnScrollListener(scrollListener)
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                Glide.with(recyclerView).resumeRequests()
            } else {
                Glide.with(recyclerView).pauseRequests()
            }
        }
    }

    inner class ViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

        fun onBind(newsMetaItem: NewsMetaItem) {
            tvTitle.text = newsMetaItem.title
            tvSource.text = newsMetaItem.source
            tvCommentCount.text =
                containerView.resources.getString(R.string.comment_count, newsMetaItem.replyCount)
            tvPublishTime.text = dateFormat.format(Date(newsMetaItem.publishTime * 1000))
            Glide.with(recyclerView)
                .load(newsMetaItem.headPic)
                .transition(withCrossFade())
                .into(ivPicture)
        }

    }


}