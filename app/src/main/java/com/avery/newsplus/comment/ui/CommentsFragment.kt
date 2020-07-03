package com.avery.newsplus.comment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.avery.newsplus.R
import com.avery.newsplus.api.NewsService
import com.avery.newsplus.api.ServiceFactory
import com.avery.newsplus.base.BaseFragment
import com.avery.newsplus.comment.adapter.CommentAdapter
import com.avery.newsplus.comment.repository.CommentRepository
import com.avery.newsplus.comment.viewmodel.CommentViewModel
import kotlinx.android.synthetic.main.fragment_comments.*
import kotlinx.android.synthetic.main.fragment_comments.loadingView

/**
 * 新闻评论Fragment
 *
 * @author Avery
 */

class CommentsFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_comments
    private lateinit var adapter: CommentAdapter
    private val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val api = ServiceFactory.getService(NewsService::class.java)
                val repository = CommentRepository(api)
                @Suppress("UNCHECKED_CAST")
                return CommentViewModel(repository) as T
            }

        })[CommentViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUIElements()
        subscribe()

    }

    private fun setupUIElements() {
        adapter = CommentAdapter()
        adapter.registerAdapterDataObserver(observer)
        commentsRecyclerView.adapter = adapter
    }

    private fun subscribe() {
        viewModel.newsComments.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun loadData() {
        arguments?.let {
            val newsId = it.getString("newsId") ?: ""
            viewModel.loadNewsComments(newsId)
        }

    }

    private val observer =  object: RecyclerView.AdapterDataObserver() {
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            hideLoadViewIfNeeds()
        }
    }

    private fun hideLoadViewIfNeeds() {
//        loadingView?.let {
//            if (it.isVisible && adapter.itemCount > 0) {
//                it.isVisible = false
//                adapter.unregisterAdapterDataObserver(observer)
//            }
//        }
    }
}