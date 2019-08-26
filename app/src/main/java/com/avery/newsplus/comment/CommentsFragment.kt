package com.avery.newsplus.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.avery.newsplus.R
import com.avery.newsplus.api.NewsService
import com.avery.newsplus.api.ServiceFactory
import com.avery.newsplus.comment.adapter.CommentAdapter
import com.avery.newsplus.comment.repository.CommentRepository
import com.avery.newsplus.comment.viewmodel.CommentViewModel
import kotlinx.android.synthetic.main.fragment_comments.*

@Suppress("UNCHECKED_CAST")
class CommentsFragment : Fragment() {

    private lateinit var adapter: CommentAdapter

    private val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val api = ServiceFactory.getService(NewsService::class.java)
                val repository = CommentRepository(api)
                return CommentViewModel(repository) as T
            }

        })[CommentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUIElements()
        subscribe()
        loadData()
    }

    private fun setupUIElements() {
        adapter = CommentAdapter()
        commentsRecyclerView.adapter = adapter
    }

    private fun subscribe() {
        viewModel.newsComments.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun loadData() {
        arguments?.let {
            val newsId = it.getString("newsId") ?: ""
            viewModel.loadNewsComments(newsId)
        }

    }
}