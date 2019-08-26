package com.avery.newsplus.comment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.avery.newsplus.R
import com.avery.newsplus.api.model.Comment
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_comments.*
import java.text.SimpleDateFormat
import java.util.*

class CommentAdapter : PagedListAdapter<Comment, CommentAdapter.ViewHolder>(DIFF_CALLBACK) {
    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        return ViewHolder(
            layoutInflater!!.inflate(R.layout.item_comments, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

        fun onBind(item: Comment?) {
            item?.let {
                tvUser.text = it.replier
                tvCommentTime.text = dateFormat.format(Date(it.replyTime * 1000))
                tvComment.text = it.replyContent
            }
        }

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Comment>() {
            override fun areItemsTheSame(oldItem: Comment, newItem: Comment) = oldItem.replyId == newItem.replyId

            override fun areContentsTheSame(oldItem: Comment, newItem: Comment) = oldItem.replyId == newItem.replyId
        }
    }
}